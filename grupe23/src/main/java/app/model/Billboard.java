package app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.BatchSize;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "BILLBOARD")
public class Billboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BILLBOARD_ID")
    private long id;

    //@JsonManagedReference
    @BatchSize(size = 1000)
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "billboard",
            orphanRemoval = true)

    private List<Publication> publications = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "BILLBOARD_HAS_CATEGORY",
            joinColumns = @JoinColumn(name = "billboard_id", referencedColumnName = "BILLBOARD_ID"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "CATEGORY_ID"))

    private List<Category> categories = new ArrayList<>();
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    @ManyToOne
    private User creator;


    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Transactional
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Transactional
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

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void addPublication(Publication publication) {
        this.publications.add(publication);
    }

    @Transactional
    public Publication getPublication(Publication publication) {
        return this.getPublications().stream().filter(p -> p.getId() == publication.getId()).findFirst().orElse(null);
    }
}