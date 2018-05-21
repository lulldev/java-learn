package supermarket.payment;

import java.math.BigDecimal;

public class Bill {

    private PaymentMethod paymentMethod;
    private BigDecimal total;

    public Bill(PaymentMethod paymentMethod, BigDecimal total) {
        this.paymentMethod = paymentMethod;
        this.total = total;
    }

    public BigDecimal getBillTotal() {
        return this.total;
    }

    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }
}
