package ru.crazzyoffice.error;



public class ErrorResponse {
    private final String url;
    private final ErrorType type;
    private final String detail;

    public ErrorResponse(CharSequence url, ErrorType type, String detail) {
        this.url = url.toString();
        this.type = type;
        this.detail = detail;
    }

    public String getUrl() {
        return url;
    }

    public ErrorType getType() {
        return type;
    }

    public String getDetail() {
        return detail;
    }
}
