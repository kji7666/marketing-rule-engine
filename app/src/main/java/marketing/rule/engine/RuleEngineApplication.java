package marketing.rule.engine; // 放在基础套件下

import marketing.rule.engine.context.Order;
import marketing.rule.engine.context.User;
import marketing.rule.engine.rule.operator.AndSpecification;
import marketing.rule.engine.rule.operator.OrSpecification;
import marketing.rule.engine.rule.specification.ISpecification;
import marketing.rule.engine.rule.specification.OrderAmountSpecification;
import marketing.rule.engine.rule.specification.UserTagSpecification;

import java.math.BigDecimal;
import java.util.Set;

public class RuleEngineApplication {
    public static void main(String[] args) {
        // --- 准备测试资料 ---
        User vipUser = new User("U001", Set.of("VIP", "LOYAL_CUSTOMER"));
        User newUser = new User("U002", Set.of("NEW_USER"));
        Order highValueOrder = new Order("O101", new BigDecimal("3000.00"));
        Order lowValueOrder = new Order("O102", new BigDecimal("800.00"));

        // --- 定义基础规则 (叶子节点) ---
        ISpecification isVip = new UserTagSpecification("VIP");
        ISpecification isNewUser = new UserTagSpecification("NEW_USER");
        ISpecification orderAmountOver2000 = new OrderAmountSpecification(new BigDecimal("2000"));
        ISpecification orderAmountOver1000 = new OrderAmountSpecification(new BigDecimal("1000"));

        // --- 组合复杂规则 (树枝节点) ---
        // 规则 A: 使用者是 VIP **且** 订单金额超过 2000
        ISpecification vipHighValueRule = new AndSpecification(isVip, orderAmountOver2000);

        // 规则 B: (使用者是 VIP **且** 订单金额超过 1000) **或者** 使用者是新会员
        ISpecification specialOfferRule = new OrSpecification(
            new AndSpecification(isVip, orderAmountOver1000),
            isNewUser
        );

        // --- 执行规则引擎 ---
        System.out.println("========= 规则 A 测试 (VIP 且 金额 > 2000) =========");
        System.out.println("VIP 使用者 & 高金额订单 是否满足? " + vipHighValueRule.isSatisfiedBy(vipUser, highValueOrder));
        System.out.println("VIP 使用者 & 低金额订单 是否满足? " + vipHighValueRule.isSatisfiedBy(vipUser, lowValueOrder));
        System.out.println("新使用者 & 高金额订单 是否满足? " + vipHighValueRule.isSatisfiedBy(newUser, highValueOrder));

        System.out.println("\n========= 规则 B 测试 ((VIP 且 金额 > 1000) 或 新使用者) =========");
        System.out.println("VIP 使用者 & 高金额订单 是否满足? " + specialOfferRule.isSatisfiedBy(vipUser, highValueOrder));
        System.out.println("VIP 使用者 & 低金额订单 是否满足? " + specialOfferRule.isSatisfiedBy(vipUser, lowValueOrder));
        System.out.println("新使用者 & 低金额订单 是否满足? " + specialOfferRule.isSatisfiedBy(newUser, lowValueOrder));
    }
}