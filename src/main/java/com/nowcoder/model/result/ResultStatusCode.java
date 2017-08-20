package com.nowcoder.model.result;

/**
 * Created by CycloneBoy on 2017/7/14.
 */
public enum ResultStatusCode {
    OK(0,"OK"),
    SYSTEM_ERR(30001,"System error");

    private int errcode;
    private String errmsg;

    ResultStatusCode(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
