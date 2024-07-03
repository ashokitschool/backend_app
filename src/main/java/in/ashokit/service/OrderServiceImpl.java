package in.ashokit.service;

import in.ashokit.dto.PurchaseRequest;
import in.ashokit.dto.PurchaseResponse;
import in.ashokit.entity.Customer;
import in.ashokit.entity.Order;
import in.ashokit.entity.OrderItem;
import in.ashokit.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public PurchaseResponse placeOrder(PurchaseRequest purchaseRequest) {

        Order order = purchaseRequest.getOder();

        // setting order tracking number
        String orderTrackingNumber = UUID.randomUUID().toString();
        order.setOrderTrackingNumber(orderTrackingNumber);

        Customer customer = purchaseRequest.getCustomer();
        String email = customer.getEmail();

        Customer customerFromDb = customerRepository.findByEmail(email);

        if(customerFromDb==null){

        }

        //TODO:save the order details in db
        //TODO: Save => customer + address + order + order_items

        String razorpayOrderId = createRazorpaOrder(purchaseRequest);

        return new PurchaseResponse(orderTrackingNumber, razorpayOrderId);

    }

    private String createRazorpaOrder(PurchaseRequest purchaseRequest){
        //TODO: Create razorpay order
        return null;
    }
}
