package com.jacky.springframework.aop;

/**
 * Superinterface for all Advisors that are driven by a pointcut.
 * This covers nearly all advisors except introduction advisors,
 * for which method-level matching doesn't apply.
 *
 *
 *
 *
 *
 *
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public interface MyPointcutAdvisor extends MyAdvisor {

    /**
     * Get the Pointcut that drives this advisor.
     */
    MyPointcut getPointcut();

}
