package com.cxz.utils;
/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/12/1 14:41
 */
public class LogContent {
    private String module;
    private String category;
    private String subCategory;
    private String message;
    private String filter1;
    private String filter2;

    public LogContent(LogModule module, String category, String subCategory) {
        this.module = module.toString();
        this.category = category;
        this.subCategory = subCategory;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFilter1() {
        return filter1;
    }

    public void setFilter1(String filter1) {
        this.filter1 = filter1;
    }

    public String getFilter2() {
        return filter2;
    }

    public void setFilter2(String filter2) {
        this.filter2 = filter2;
    }
}