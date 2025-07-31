package marketing.rule.engine.rule.specification;

import marketing.rule.engine.context.Order;
import marketing.rule.engine.context.User;

public class UserTagSpecification implements ISpecification {
    private final String requiredTag;

    public UserTagSpecification(String requiredTag) {
        this.requiredTag = requiredTag;
    }

    @Override
    public boolean isSatisfiedBy(User user, Order order) {
        if (user == null || user.getTags() == null) {
            return false;
        }
        return user.getTags().contains(requiredTag);
    }
}