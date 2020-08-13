package com.smirnov.aviaplant.domains;

public class Message {

    private String name;
    private String dialogID;
    private String senderName;

    public Message() {
    }

    public Message(String name, String dialogID, String senderName) {
        this.name = name;
        this.dialogID = dialogID;
        this.senderName = senderName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDialogID() {
        return dialogID;
    }

    public void setDialogID(String dialogID) {
        this.dialogID = dialogID;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}