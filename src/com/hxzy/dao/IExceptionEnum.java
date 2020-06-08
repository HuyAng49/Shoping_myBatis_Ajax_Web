package com.hxzy.dao;

public interface IExceptionEnum {
    /**
     * 获取状态码
     *
     * @return 状态码
     */
    public String getCode();

    /**
     * 获取提示信息
     *
     * @return 提示信息
     */
    public String getMessage();
}
