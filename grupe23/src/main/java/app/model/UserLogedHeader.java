package app.model;

import org.springframework.http.HttpHeaders;

public class UserLogedHeader {

    private UserLogin userLogin;
    private HttpHeaders headers;

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }
}
