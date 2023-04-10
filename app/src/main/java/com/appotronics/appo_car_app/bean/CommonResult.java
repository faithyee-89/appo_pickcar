package com.appotronics.appo_car_app.bean;

import java.util.List;

/**
 * @desc:
 * @author: yewei
 * @data: 2023/3/23 18:14
 */
public class CommonResult {


    private Integer code;
    private List<?> data;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
