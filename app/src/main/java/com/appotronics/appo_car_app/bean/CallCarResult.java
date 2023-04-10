package com.appotronics.appo_car_app.bean;

/**
 * @desc:
 * @author: yewei
 * @data: 2023/3/23 17:13
 */
public class CallCarResult {
    private Integer code;
    private DataBean data;
    private String msg;

    public static class DataBean {
        private String orderId;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
