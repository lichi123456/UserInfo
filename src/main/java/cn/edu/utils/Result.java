package cn.edu.utils;

import java.io.Serializable;

/**
 * @ClassName Result
 * @Description TODO 返回值规范
 * @Author wys5
 * @Date 2020/2/18 10:03
 * @Version 1.0
 **/
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    private String message;
    private boolean success;
    private Object object;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Result() {
    }

    public Object getObject() {
        return this.object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
