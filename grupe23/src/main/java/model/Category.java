package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="CATEGORY_ID")
    private long id;
    @Column(name="NAME")
    private String name;
    @Column(name="WRITE_PERMISSON")
    private Boolean writePermisson;
    @OneToMany(mappedBy = "categories")
    private Set<User> users = new HashSet<>();

    public Category() {
    }

    public Category(long id, String name, Boolean writePermisson) {
        this.id = id;
        this.name = name;
        this.writePermisson = writePermisson;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getWritePermisson() {
        return writePermisson;
    }

    public void setWritePermisson(Boolean writePermisson) {
        this.writePermisson = writePermisson;
    }
}
