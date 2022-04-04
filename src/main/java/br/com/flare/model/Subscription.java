package br.com.flare.model;

import javax.persistence.*;

@Entity
@Table(name="subscriptions") // A tabela ira se chamar subscriptions, cada linha representa uma subscription
public class Subscription {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String token;

  public Subscription() {
  }

  public Subscription(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public Long getId() {
    return id;
  }
}
