package ru.crazzyoffice.error;
/*
Just an example of error object what can be sended  to error page
 */


public class ErrorResponse {
    private  String url;
    private  ErrorType type;
    private  String detail;

    public ErrorResponse(CharSequence url, ErrorType type, String detail) {
        this.url = url.toString();
        this.type = type;
        this.detail = detail;
    }

    public ErrorResponse() {
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(ErrorType type) {
        this.type = type;
    }

    public void setDetail(String detail) {
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
