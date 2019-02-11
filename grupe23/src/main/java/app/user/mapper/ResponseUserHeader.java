package app.user.mapper;

import app.user.controller.http.response.HttpResponseUserLogin;
import org.springframework.http.HttpHeaders;

public class ResponseUserHeader {

    private HttpResponseUserLogin httpResponseUserLogin;
    private HttpHeaders headers;

    public HttpResponseUserLogin getHttpResponseUserLogin() {
        return httpResponseUserLogin;
    }

    public void setHttpResponseUserLogin(HttpResponseUserLogin httpResponseUserLogin) {
        this.httpResponseUserLogin = httpResponseUserLogin;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }
}
