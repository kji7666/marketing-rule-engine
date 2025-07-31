package marketing.rule.engine.rule.specification;

import marketing.rule.engine.context.Order;
import marketing.rule.engine.context.User;

/**
 * 規格介面 (ISpecification)
 * 這是組合模式中的核心抽象元件 (Component)。
 * 所有規則節點，無論是葉子節點還是樹枝節點，都必須實現此介面。
 */
public interface ISpecification {

    /**
     * 判斷給定的使用者和訂單是否滿足此規格（規則）
     * @param user  使用者上下文
     * @param order 訂單上下文
     * @return true 如果滿足，否則 false
     */
    boolean isSatisfiedBy(User user, Order order);
}