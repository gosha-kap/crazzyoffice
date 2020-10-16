package ru.crazzyoffice.entity.model;

import ru.crazzyoffice.entity.JobType;

public class Event {
    private String id;
    private String start;
    private String title;
    public String icon;
    public String backgroundColor;
    public String textColor;

    public Event(String id, String start, String title, String icon) {
        this.id = id;
        this.start = start;
        this.title = title;
        this.icon = icon;
    }

    public Event(String id, String start, String title, String icon, String backgroundColor, String textColor) {
        this.id = id;
        this.start = start;
        this.title = title;
        this.icon = icon;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
