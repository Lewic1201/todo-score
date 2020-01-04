package com.lewic.todoscore.dto;

import lombok.Data;

@Data
public class Message {

    private String status;
    private String message;
    private Object data;

    public Message() {
        this.status = "success";
        this.message = "";
        this.data = null;
    }

    // 失败时
    public void setMessage(String message) {
        this.status = "error";
        this.message = message;
    }

    // 成功时
    public void setData(Object data) {
        this.status = "success";
        this.data = data;
    }
//    public static void main(String[] args) {
//        Message a = new Message();
//        System.out.println(a.status);
//    }
}
