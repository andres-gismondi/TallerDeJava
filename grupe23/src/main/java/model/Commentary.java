package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="COMMENTARY")
public class Commentary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    @Column(name="TITLE")
    private String title;
    @Column(name="BODY")
    private String body;
    @Column(name="DATE")
    private Date date;
    @Column(name="ENABLED")
    private Boolean enabled;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_CREATOR")
    private User creator;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
