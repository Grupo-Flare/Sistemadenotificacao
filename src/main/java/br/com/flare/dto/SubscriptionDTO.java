package br.com.flare.dto;

import br.com.flare.annotations.Unique;
import br.com.flare.model.Subscription;
import br.com.flare.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SubscriptionDTO {

  @NotBlank @NotNull @Unique(domainClass = Subscription.class, fieldName = "token")
  private String token;

  private String user;

  public SubscriptionDTO() {
  }

  public SubscriptionDTO(String token) {
    this.token = token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public Subscription toModel(User user) {
    return new Subscription(getToken(), user);
  }
}
