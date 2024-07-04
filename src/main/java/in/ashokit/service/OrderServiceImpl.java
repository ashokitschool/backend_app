package in.ashokit.service;

import com.razorpay.RazorpayClient;
import in.ashokit.dto.PurchaseRequest;
import in.ashokit.dto.PurchaseResponse;
import in.ashokit.entity.Customer;
import in.ashokit.entity.Order;
import in.ashokit.entity.OrderItem;
import in.ashokit.repository.CustomerRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    //@Value("${razorpay.key.id}")
    private String keyId = "";

    //@Value("${razorpay.key.secret}")
    private String keySecret = "";

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public PurchaseResponse placeOrder(PurchaseRequest purchaseRequest) throws Exception {

        Order order = purchaseRequest.getOrder();

        // setting order tracking number
        String orderTrackingNumber = UUID.randomUUID().toString();
        order.setOrderTrackingNumber(orderTrackingNumber);
        order.setStatus("PENDING");


        //populate orderitems in order
        Set<OrderItem> orderItems = purchaseRequest.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        // populate shipping address
        order.setShippingAddress(purchaseRequest.getShippingAddress());

        // populate customer with order
        Customer customer = purchaseRequest.getCustomer();
        String email = customer.getEmail();

        // check customer presence in db
        Customer customerFromDb = customerRepository.findByEmail(email);

        if (customerFromDb != null) {
            // we found customer
            customer = customerFromDb;
        }

        // adding order to customer and vice versa
        customer.add(order);

        // save data in db
        customerRepository.save(customer);

        String razorpayOrderId = createRazorpaOrder(purchaseRequest);

        return new PurchaseResponse(orderTrackingNumber, razorpayOrderId);

    }

    private String createRazorpaOrder(PurchaseRequest purchaseRequest) throws Exception {

        JSONObject orderReq = new JSONObject();
        orderReq.put("amount", purchaseRequest.getOrder().getTotalPrice() * 100);
        orderReq.put("currency", "INR");
       // orderReq.put("reciept", purchaseRequest.getCustomer().getEmail());

        RazorpayClient client = new RazorpayClient(keyId, keySecret);
        com.razorpay.Order razorPayOrder = client.Orders.create(orderReq);

        return razorPayOrder.get("id");

    }
}
