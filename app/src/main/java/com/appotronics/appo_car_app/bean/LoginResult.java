package com.appotronics.appo_car_app.bean;

/**
 * @desc:
 * @author: yewei
 * @data: 2023/3/23 16:46
 */
public class LoginResult {

    private Integer code;
    private DataBean data;

    public static class DataBean {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
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
}
