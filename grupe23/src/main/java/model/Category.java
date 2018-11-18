package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "USER_HAS_CATEGORY",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
    private List<User> users = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "BILLBOARD_HAS_CATEGORY",

            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "BILLBOARD_ID")
    )
    private List<Billboard> billboards = new ArrayList<>();

    public Category() {
    }

    public void addBillboard(Billboard billboard){

        this.billboards.add(billboard);
    }


    public List<Billboard> getBillboards() {
        return billboards;
    }

    public void setBillboards(List<Billboard> billboards) {
        this.billboards = billboards;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
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
