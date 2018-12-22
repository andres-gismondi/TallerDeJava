package app.model;

import javax.persistence.*;

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

    public Category(long id, String name, Boolean writePermisson) {
        this.id = id;
        this.name = name;
        this.writePermisson = writePermisson;
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
