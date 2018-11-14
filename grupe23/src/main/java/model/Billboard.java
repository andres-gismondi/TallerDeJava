package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="BILLBOARD")
public class Billboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "billboard",
            orphanRemoval = true
    )
    private List<Publication> publications = new ArrayList<>();


    @ManyToMany
    @JoinTable(name="BILLBOARD_HAS_CATEGORY",
            joinColumns=@JoinColumn(name="billboard_id", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="category_id", referencedColumnName="CATEGORY_ID"))

    private List<Category> categories = new ArrayList<>();
    @Column(name="TITLE")
    private String title;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="DATE")
    private Date date;

    public void removePublication(Publication publication){
        this.getPublications().remove(publication);
    }
    public void removeCategories(Category category){
        this.getCategories().remove(category);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addCategory(Category category){

        this.categories.add(category);
        category.getBillboards().add(this);

    }

    public void addPublication(Publication publication){
        this.publications.add(publication);
        publication.setBillboard(this);
    }

    public Publication getPublication(Publication publication){
        return this.getPublications().stream().filter(p -> p.getId()==publication.getId()).findFirst().orElse(null);
    }
}