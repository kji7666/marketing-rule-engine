package marketing.rule.engine.context;

import java.math.BigDecimal;

/**
 * 訂單上下文物件 (Context)
 * 一個簡單的 POJO，用於存放訂單相關的資料。
 */
public class Order {

    private String orderId;
    private BigDecimal totalAmount; // 訂單總金額

    /**
     * Order 的建構函式
     * @param orderId     訂單 ID
     * @param totalAmount 訂單總金額
     */
    public Order(String orderId, BigDecimal totalAmount) {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
    }

    // --- Getters ---

    public String getOrderId() {
        return orderId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    // --- (可選) Setters ---

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}