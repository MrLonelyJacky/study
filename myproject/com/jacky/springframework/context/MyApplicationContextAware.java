package com.jacky.springframework.context;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.MyAware;

/**
 * Interface to be implemented by any object that wishes to be notified
 * of the {@link } that it runs in.
 *
 * 实现此接口，既能感知到所属的 ApplicationContext
 *
 *
 *
 *
 *
 *
 *
 */
public interface MyApplicationContextAware extends MyAware {

    void setApplicationContext(MyApplicationContext applicationContext) throws MyBeansException;

}
    