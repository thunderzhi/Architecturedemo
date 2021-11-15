package com.cxz.utils;

import java.io.Serializable;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/12/22 11:08
 */
public class RepsonseShell<T> implements Serializable {

    private static final long serialVersionUID = 8584183960015434626L;



    private T Content;

    // 是否成功
    private Boolean IsSuccess;

    //     错误信息
    public String  Error;

    //     异常对象
    public Exception Exception;

    public Boolean getIsSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(Boolean success) {
        IsSuccess = success;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public java.lang.Exception getException() {
        return Exception;
    }

    public void setException(java.lang.Exception exception) {
        Exception = exception;
    }

    public T getContent() {
        return Content;
    }

    public void setContent(T content) {
        Content = content;
    }

    public RepsonseShell() {
        IsSuccess = false;
        Error = "";
        Exception = null;
        Content =null;
    }
}
