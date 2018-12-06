package model;

public class UserBillboards {

    private Billboard billboard;
    private User user;

    public UserBillboards() {
    }

    public Billboard getBillboard() {
        return billboard;
    }

    public void setBillboard(Billboard billboard) {
        this.billboard = billboard;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
