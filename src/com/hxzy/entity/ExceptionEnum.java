package com.hxzy.entity;

import com.hxzy.dao.IExceptionEnum;

public enum ExceptionEnum implements IExceptionEnum {

    //自定义的状态码
    DATABASE_EXCEPTION("2000", "数据库连接异常"),
    FAILD("0", "操作失败"),
    SUCCESS("1", "操作成功");

    //错误码
    public String code;
    //提示信息
    public String message;

    //构造函数
    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    //获取状态码
    @Override
    public String getCode() {
        return code;
    }

    //获取提示信息
    @Override
    public String getMessage() {
        return message;
    }


}
