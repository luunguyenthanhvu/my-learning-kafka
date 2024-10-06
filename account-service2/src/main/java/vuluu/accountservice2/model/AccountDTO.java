package vuluu.accountservice2.model;

public class AccountDTO {

  String name;
  String email;

  public AccountDTO(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public AccountDTO() {
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }
}
