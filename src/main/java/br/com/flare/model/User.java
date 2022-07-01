package br.com.flare.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    @ManyToMany
    private List<Category> inscribedCategories;

    @ManyToMany
    private List<Category> allowToSendNotification;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addInscription(Category category){
       this.inscribedCategories.add(category);
    }

    public void removeInscription(Category category) {
        this.inscribedCategories.remove(category);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Category> getCategory() {
        return inscribedCategories;
    }

    public void setCategory(List<Category> category) {
        this.inscribedCategories = category;
    }

    public List<Category> getAllowToSendNotification() {
        return allowToSendNotification;
    }

    public void setAllowToSendNotification(List<Category> allowToSendNotification) {
        this.allowToSendNotification = allowToSendNotification;
    }
}
