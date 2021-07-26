package ru.crazzyoffice.bots.commands;

public enum InputMessage {

    OPEN("Open Barrier"),
    AUTORIZE("Autorize request"),
    UNKNOWN("Unrecognize command");


    private String command;

    InputMessage(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static InputMessage getMessage(String input) {
        for (InputMessage message : values()) {
            if (message.getCommand().equals(input)) {
                return message;
            }
        }
        return InputMessage.UNKNOWN;
    }
}
