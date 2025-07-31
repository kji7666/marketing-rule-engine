package marketing.rule.engine.rule.operator;

import marketing.rule.engine.context.Order;
import marketing.rule.engine.context.User;
import marketing.rule.engine.rule.specification.ISpecification;

public class OrSpecification extends AbstractCompositeSpecification {
    public OrSpecification(ISpecification... specs) {
        super(specs);
    }

    @Override
    public boolean isSatisfiedBy(User user, Order order) {
        for (ISpecification spec : children) {
            if (spec.isSatisfiedBy(user, order)) {
                return true;
            }
        }
        return false;
    }
}