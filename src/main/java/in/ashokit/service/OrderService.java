package in.ashokit.service;

import in.ashokit.dto.PurchaseRequest;
import in.ashokit.dto.PurchaseResponse;

public interface OrderService {

    public PurchaseResponse placeOrder(PurchaseRequest purchaseRequest) throws Exception;
}
