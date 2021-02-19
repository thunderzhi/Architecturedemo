package com.cxz;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.*;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.Map;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/19 15:32
 */
public abstract class AbstractApplicationContext  extends DefaultResourceLoader
        implements IAppContext {
    private final Object startupShutdownMonitor = new Object();



    @Override
    public void refresh() throws BeansException, IllegalStateException {
        System.out.println("cxz AbstractApplicationContext refresh");
        synchronized (this.startupShutdownMonitor) {
            // Prepare this context for refreshing.
            prepareRefresh();

            // Tell the subclass to refresh the internal bean factory.
            ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

            // Prepare the bean factory for use in this context.
            prepareBeanFactory(beanFactory);

            try {
                // Allows post-processing of the bean factory in context subclasses.
                postProcessBeanFactory(beanFactory);

                // Invoke factory processors registered as beans in the context.
                invokeBeanFactoryPostProcessors(beanFactory);

                // Register bean processors that intercept bean creation.
                registerBeanPostProcessors(beanFactory);

                // Initialize message source for this context.
                initMessageSource();

                // Initialize event multicaster for this context.
                initApplicationEventMulticaster();

                // Initialize other special beans in specific context subclasses.
                onRefresh();

                // Check for listener beans and register them.
                registerListeners();

                // Instantiate all remaining (non-lazy-init) singletons.
                finishBeanFactoryInitialization(beanFactory);

                // Last step: publish corresponding event.
                finishRefresh();
            }

            catch (BeansException ex) {
//                if (logger.isWarnEnabled()) {
//                    logger.warn("Exception encountered during context initialization - " +
//                            "cancelling refresh attempt: " + ex);
//                }

                // Destroy already created singletons to avoid dangling resources.
                destroyBeans();

                // Reset 'active' flag.
                cancelRefresh(ex);

                // Propagate exception to caller.
                throw ex;
            }

            finally {
                // Reset common introspection caches in Spring's core, since we
                // might not ever need metadata for singleton beans anymore...
                resetCommonCaches();
            }
        }
    }

    private void destroyBeans() {
    }

    private ConfigurableListableBeanFactory obtainFreshBeanFactory() {
        return getBeanFactory();
    }


    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){

    };
    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory){

    };


    public abstract ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;

    protected void initMessageSource(){ };
    protected void initApplicationEventMulticaster(){ };
    protected void onRefresh(){System.out.println("cxz AbstractApplicationContext onRefresh"); };
    protected void registerListeners(){ };
    protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory){ };
    protected void finishRefresh(){ };
    protected void cancelRefresh(BeansException ex){ };
    protected void resetCommonCaches(){ };

    private void prepareBeanFactory(ConfigurableListableBeanFactory beanFactory) {
    }

    protected void prepareRefresh(){

    };
    protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory){

    };





}
