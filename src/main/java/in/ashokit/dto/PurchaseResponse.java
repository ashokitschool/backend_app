package in.ashokit.dto;

public class PurchaseResponse {

    private String orderTrackingNumber;
    private String razorpayOrderId;

    public PurchaseResponse(String orderTrackingNumber, String razorpayOrderId) {
        this.orderTrackingNumber = orderTrackingNumber;
        this.razorpayOrderId = razorpayOrderId;
    }

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public void setOrderTrackingNumber(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }
}
