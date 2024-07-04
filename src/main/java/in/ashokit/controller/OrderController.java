package in.ashokit.controller;

import in.ashokit.dto.PurchaseRequest;
import in.ashokit.dto.PurchaseResponse;
import in.ashokit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/purchase")
    public PurchaseResponse createOrder(@RequestBody PurchaseRequest purchaseRequest) throws Exception {
        return orderService.placeOrder(purchaseRequest);
    }
}
