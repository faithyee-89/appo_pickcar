package com.appotronics.appo_car_app.bean;

/**
 * @desc:
 * @author: yewei
 * @data: 2023/3/22 14:41
 */
public class ResponseBean {
    private String type;
    private String version;
    private String command;
    private String content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
