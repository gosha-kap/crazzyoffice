package ru.crazzyoffice.bots.commands;

public enum ResponseMessage {

    REQUEST_SENDED("Requuest sended"),
    ACCESS_FORBITTEN("Access forbitten"),
    ACCESS_GRANTED("Pleace, come in "),
    ERROR_TIMEOUT("Time out error...Contact your admin "),
    ERROR_UNEXCPTED("Try again..."),
    UNRECOGNIZE("Unrecognize ...");

    private String response;

    ResponseMessage(String response) {
        this.response = response;
    }

    public String getCommand() {
        return response;
    }
}
