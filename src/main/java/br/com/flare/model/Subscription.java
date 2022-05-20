package br.com.flare.model;

import javax.persistence.*;

@Entity
@Table(name="subscriptions") // A tabela ira se chamar subscriptions, cada linha representa uma subscription
public class Subscription {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true) // Restrição no banco em que o campo não pode ser nulo e deve ser unico
  private String token;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Subscription() {
  }

  public Subscription(String token, User user) {
    this.token = token;
    this.user = user;
  }

  public String getToken() {
    return token;
  }

  public Long getId() {
    return id;
  }
}
