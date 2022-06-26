package spring.chapter7.service;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UsageTracking {

    @DeclareParents(value = "spring.chapter7.service.*+",defaultImpl = DefaultUsageTracked.class)
    public static UsageTracked mixin;
}
