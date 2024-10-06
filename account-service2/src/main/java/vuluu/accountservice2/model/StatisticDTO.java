package vuluu.accountservice2.model;

import java.util.Date;

public class StatisticDTO {

  String message;
  Date createdDate;

  public StatisticDTO() {
  }

  public StatisticDTO(String message, Date createdDate) {
    this.message = message;
    this.createdDate = createdDate;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

}
