package com.smirnov.aviaplant.domains;

import javax.persistence.*;

@Entity
@Table(name = "Messages")
public class DialogMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dialogMessageID;

    private String text;

    private String sender;

    private boolean newMessageMarker = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dialogMessage_dialogID")
    private Dialog dialog;


    public DialogMessage() {
    }

    public DialogMessage(String sender, String text) {
        this.text = text;
        this.sender = sender;
    }

    public DialogMessage(String sender, String text, Dialog dialog) {
        this.text = text;
        this.sender = sender;
        this.dialog = dialog;
    }

    public Long getDialogMessageID() {
        return dialogMessageID;
    }

    public void setDialogMessageID(Long dialogMessageID) {
        this.dialogMessageID = dialogMessageID;
    }

    public boolean isNewMessageMarker() {
        return newMessageMarker;
    }

    public void setNewMessageMarker(boolean newMessageMarker) {
        this.newMessageMarker = newMessageMarker;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
