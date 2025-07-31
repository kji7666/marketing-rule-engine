package marketing.rule.engine.rule.specification;

import marketing.rule.engine.context.Order;
import marketing.rule.engine.context.User;
import java.math.BigDecimal;

public class OrderAmountSpecification implements ISpecification {
    private final BigDecimal requiredAmount;

    public OrderAmountSpecification(BigDecimal requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    @Override
    public boolean isSatisfiedBy(User user, Order order) {
        if (order == null || order.getTotalAmount() == null) {
            return false;
        }
        return order.getTotalAmount().compareTo(requiredAmount) >= 0;
    }
}