package spring.chapter7.service;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

public class DealService implements EmbeddedValueResolverAware {
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {

    }
}
