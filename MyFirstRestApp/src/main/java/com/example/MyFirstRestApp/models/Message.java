package com.example.MyFirstRestApp.models;

public class Message {
    private String senderName;
    private String receiverName;
    private String message;
    private String data;
    private MessageStatus status;

    public Message() {
    }

    public Message(String senderName, String receiverName, String message, String data, MessageStatus status) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    @Override
    public String  toString() {
        return "Message{" +
                "senderName='" + senderName + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                ", status=" + status +
                '}';
    }
}
