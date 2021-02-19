package com.cxz;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/19 15:55
 */
public interface IAppContext extends ApplicationContext {
    void refresh() throws BeansException, IllegalStateException;
}
