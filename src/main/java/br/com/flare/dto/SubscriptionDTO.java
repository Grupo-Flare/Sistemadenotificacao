package br.com.flare.dto;

import br.com.flare.model.Subscription;

import javax.validation.constraints.NotEmpty;

public class SubscriptionDTO {

  @NotEmpty
  private String token;

  public SubscriptionDTO() {
  }

  public SubscriptionDTO(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public Subscription toModel() {
    return new Subscription(getToken());
  }
}
