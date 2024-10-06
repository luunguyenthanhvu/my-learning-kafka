package vuluu.notificationservice.service;

import vuluu.notificationservice.dto.MessageDTO;

public interface EmailService {
  void sendEmail(MessageDTO messageDTO);
}
