package marketing.rule.engine.rule.operator;

import marketing.rule.engine.context.Order;
import marketing.rule.engine.context.User;
import marketing.rule.engine.rule.specification.ISpecification;

public class AndSpecification extends AbstractCompositeSpecification {
    public AndSpecification(ISpecification... specs) {
        super(specs);
    }

    @Override
    public boolean isSatisfiedBy(User user, Order order) {
        for (ISpecification spec : children) {
            if (!spec.isSatisfiedBy(user, order)) {
                return false;
            }
        }
        return true;
    }
}