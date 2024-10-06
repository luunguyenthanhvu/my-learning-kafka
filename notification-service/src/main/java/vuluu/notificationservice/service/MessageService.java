package vuluu.notificationservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import vuluu.notificationservice.dto.MessageDTO;

@Service
@Slf4j
public class MessageService {

  @Autowired
  private EmailService emailService;

  @KafkaListener(groupId = "notificationGroup", topics = "notification")
  public void listen(MessageDTO messageDTO) {
    log.info("Received: ", messageDTO.getTo());
    emailService.sendEmail(messageDTO);
  }
}
