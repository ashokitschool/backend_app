package in.ashokit.dto;

import in.ashokit.entity.Address;
import in.ashokit.entity.Customer;
import in.ashokit.entity.Order;
import in.ashokit.entity.OrderItem;

import java.util.Set;

public class PurchaseRequest {

    private Customer customer;
    private Address shippingAddress;
    private Order oder;
    private Set<OrderItem> orderItems;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getOder() {
        return oder;
    }

    public void setOder(Order oder) {
        this.oder = oder;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}