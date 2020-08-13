package com.smirnov.aviaplant.domains;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Dialogs")
public class Dialog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dialogID;

    @NotNull
    private String dialogName;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "dialogs")
    private List<UserData> userData;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dialog")
    private List<DialogMessage> messages = new ArrayList<>();


    public Dialog() {
    }

    public Dialog(String dialogName) {
        this.dialogName = dialogName;
    }

    public Long getDialogID() {
        return dialogID;
    }

    public void setDialogID(Long dialogID) {
        this.dialogID = dialogID;
    }

    public String getDialogName() {
        return dialogName;
    }

    public void setDialogName(String dialogName) {
        this.dialogName = dialogName;
    }

    public List<UserData> getUserData() {
        return userData;
    }

    public void setUserData(List<UserData> userData) {
        this.userData = userData;
    }

    public List<DialogMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<DialogMessage> messages) {
        this.messages = messages;
    }
    public void addMessage(DialogMessage dialogMessage) {
        this.messages.add(dialogMessage);
    }
}
