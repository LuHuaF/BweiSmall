package com.umeng.soexample.bweismall.bean;

/**
 * Created by android_lhf：2019/1/14
 */
public class MoreAddresBean {
    /**
     * message : 设置成功
     * status : 0000
     */

    private String message;
    private String status;
    private boolean ischecked;

    public boolean isIschecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
