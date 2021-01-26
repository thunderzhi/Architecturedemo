package com.cxz.utils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * @author cxz
 * @date 2020/10/15
 */
@Component
public class Logger {

    private Log mLogFactory;
    public Logger()
    {
        mLogFactory = LogFactory.getLog("com.cxz.web");
    }

    /**
     * 信息记录日志
     *
     * @param module 模块
     * @param message 信息
     * @param filter1 过滤1
     * @param filter2 过滤2
     */
    public void info(ModuleEnum module, String message, String filter1, String filter2)
    {
        info(module, null, null, message, filter1, filter2);
    }

    /**
     * 信息记录日志
     *
     * @param module 模块
     * @param category 大类
     * @param subCategory 小类
     * @param message 信息
     * @param filter1 过滤1
     * @param filter2 过滤2
     */
    public void info(ModuleEnum module, String category, String subCategory, String message, String filter1, String filter2)
    {
        mLogFactory.info(getLogString(module, category, subCategory, message, filter1, filter2));
    }

    /**
     * 异常记录日志
     *
     * @param module 模块
     * @param message 信息
     */
    public void error(ModuleEnum module, String message)
    {
        error(module, null, null, message, null, null);
    }

    /**
     * 异常记录日志
     *
     * @param module 模块
     * @param message 信息
     * @param filter1 过滤1
     * @param filter2 过滤2
     */
    public void error(ModuleEnum module, String message, String filter1, String filter2)
    {
        error(module, null, null, message, filter1, filter2);
    }

    /**
     * 异常记录日志
     *
     * @param module 模块
     * @param category 大类
     * @param subCategory 小类
     * @param message 信息
     * @param filter1 过滤1
     * @param filter2 过滤2
     */
    public void error(ModuleEnum module, String category, String subCategory, String message, String filter1, String filter2)
    {
        mLogFactory.error(getLogString(module, category, subCategory, message, filter1, filter2));
    }

    /**
     * 获取记录日志
     *
     * @param module 模块
     * @param category 大类
     * @param subCategory 小类
     * @param message 信息
     * @param filter1 过滤1
     * @param filter2 过滤2
     */
    private String getLogString(ModuleEnum module, String category, String subCategory, String message, String filter1, String filter2)
    {
        String logInfo = MessageFormat.format("<{0}><{1}><{2}><{3}><{4}>{5}",
                module,
                category,
                subCategory,
                filter1,
                filter2,
                message);

        return logInfo;
    }
}
