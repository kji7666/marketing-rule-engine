package marketing.rule.engine.rule.operator;

import marketing.rule.engine.rule.specification.ISpecification;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractCompositeSpecification implements ISpecification {
    protected final List<ISpecification> children = new ArrayList<>();

    public AbstractCompositeSpecification(ISpecification... specs) {
        if (specs != null) {
            Collections.addAll(children, specs);
        }
    }
}