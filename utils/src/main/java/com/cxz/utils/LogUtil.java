package com.cxz.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/12/1 14:40
 */
public class LogUtil {
    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    /**
     * 方法名称：非异常日志
     * 只传递一个参数，就是日志内容
     */
    public static void WriteLog(String Content)
    {
        String content= MessageFormat.format("<{0}><{1}><{2}><{3}><{4}>{5}",
                "",
                "",
                "",
                "",
                "",
                Content);

        logger.info(content);
    }

    /**
     * 方法名称：非异常日志
     * 只传递二个参数，就是日志内容、目录
     */
    public static void WriteLog(String Content, String Sub_category )
    {
        String content= MessageFormat.format("<{0}><{1}><{2}><{3}><{4}>{5}",
                "",
                "",
                Sub_category,
                "",
                "",
                Content);

        logger.info(content);
    }

    /**
     * 方法名称：非异常日志
     * 传递三个参数，子目录、过滤字段
     */
    public static void WriteLog(String Content, String Sub_category , String Filter1)
    {
        String content= MessageFormat.format("<{0}><{1}><{2}><{3}><{4}>{5}",
                "",
                "",
                Sub_category,
                "",
                Filter1,
                Content);

        logger.info(content);
    }

    /**
     * 方法名称：非异常日志
     * 传递四个参数，子目录、过滤字段1、过滤字段2、日志内容
     */
    public static void WriteLog(String Content, String Sub_category , String Filter1 , String Filter2 )
    {
        String content= MessageFormat.format("<{0}><{1}><{2}><{3}><{4}>{5}",
                "",
                "",
                Sub_category,
                Filter2,
                Filter1,
                Content);
        logger.info(content);
    }

    /**
     * 方法名称：非异常日志
     * 传递五个参数，目录、子目录、过滤字段1、过滤字段2、日志内容
     */
    public static void WriteLog(String Content, String category,String Sub_category , String Filter1 , String Filter2 )
    {
        String content= MessageFormat.format("<{0}><{1}><{2}><{3}><{4}>{5}",
                "",
                category,
                Sub_category,
                Filter2,
                Filter1,
                Content);
        logger.info(content);
    }

    /**
     * 方法名称：非异常日志
     * 传递六个参数，模块、目录、子目录、过滤字段1、过滤字段2、日志内容
     */
    public static void WriteLog(String Content, String module,String category,String Sub_category , String Filter1 , String Filter2 )
    {
        String content= MessageFormat.format("<{0}><{1}><{2}><{3}><{4}>{5}",
                module,
                category,
                Sub_category,
                Filter2,
                Filter1,
                Content);
        logger.info(content);
    }

    /**
     * 方法名称：异常日志
     * 传递二个参数，异常、日志内容
     */
    public static void WriteErrorLog(Exception exception, String Content)
    {
        String content= MessageFormat.format("<{0}><{1}><{2}><{3}><{4}>{5}",
                "",
                "",
                "",
                "",
                "",
                Content);
        logger.error(content,exception);
    }

    /**
     * 方法名称：异常日志
     * 传递三个参数、异常、日志内容、子目录
     */
    public static void WriteErrorLog(Exception exception, String Content, String Sub_category)
    {
        String content= MessageFormat.format("<{0}><{1}><{2}><{3}><{4}>{5}",
                "",
                "",
                Sub_category,
                "",
                "",
                Content);
        logger.error(content,exception);
    }

    /**
     * 方法名称：异常日志
     * 传递四个参数，异常、日志内容、子目录、过滤字段1
     */
    public static void WriteErrorLog(Exception exception, String Content, String Sub_category, String Filter1 )
    {
        String content= MessageFormat.format("<{0}><{1}><{2}><{3}><{4}>{5}",
                "",
                "",
                Sub_category,
                "",
                Filter1,
                Content);
        logger.error(content,exception);
    }

    /**
     * 方法名称：异常日志
     * 传递五个参数，异常、日志内容、子目录、过滤字段1、过滤字段2
     */
    public static void WriteErrorLog(Exception exception, String Content, String Sub_category, String Filter1 , String Filter2 )
    {
        String content= MessageFormat.format("<{0}><{1}><{2}><{3}><{4}>{5}",
                "",
                "",
                Sub_category,
                Filter2,
                Filter1,
                Content);
        logger.error(content,exception);
    }

    /**
     * 方法名称：error类别日志
     */
    public static void error(LogContent logContent, Throwable ex) {
        if (logContent == null || ex == null) {
            return;
        }
        logger.error(getLogContentString(logContent), ex);
    }

    /**
     * 方法名称：进行传入值的json处理
     */
    private static String getLogContentString(LogContent logContent) {
        return MessageFormat.format("<{0}><{1}><{2}><{3}><{4}>{5}",
                logContent.getModule(),
                logContent.getCategory(),
                logContent.getSubCategory(),
                logContent.getFilter1(),
                logContent.getFilter2(),
                logContent.getMessage());
    }
}
