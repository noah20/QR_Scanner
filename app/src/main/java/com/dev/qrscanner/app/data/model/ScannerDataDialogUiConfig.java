package com.dev.qrscanner.app.data.model;

public class ScannerDataDialogUiConfig {

    private String message,actionName,title;

    public ScannerDataDialogUiConfig(String title ,String message, String actionName){
        this.message = message;
        this.actionName = actionName;
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
