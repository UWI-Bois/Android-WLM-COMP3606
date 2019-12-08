package sta.uwi.edu.comp3606_practical5;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Response implements Serializable {

    private String phoneNo, msg, status;

    public Response(String phoneNo, String msg){
        this.phoneNo = phoneNo;
        this.msg = msg;
        this.status = "null";
    }

    public Response(){
        this.phoneNo = "null";
        this.msg = "null";
        this.status = "null";
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getMsg() {
        return msg;
    }

    public void setPhoneNo(String address) {
        this.phoneNo = address;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NonNull
    @Override
    public String toString() {
        String s = "PhoneNo: " + this.phoneNo
                + "\nMsg: " + this.msg
                + "\nStatus: " + this.status;
        return s;
    }
}
