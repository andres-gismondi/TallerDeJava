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

    public Category() {
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
