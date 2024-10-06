package vuluu.notificationservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import vuluu.notificationservice.dto.MessageDTO;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

  @Autowired
  private JavaMailSender javaMailSender;

  @Autowired
  private SpringTemplateEngine templateEngine;

  @Value("${spring.mail.username}")
  private String from;

  @Override
  @Async
  public void sendEmail(MessageDTO messageDTO) {
    try {
      log.info("START... Sending email");

      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

      // load template email with content
      Context context = new Context();
      context.setVariable("name", messageDTO.getToName());
      context.setVariable("content", messageDTO.getContent());
      String html = templateEngine.process("welcome-email", context);

      // send email
      helper.setTo(messageDTO.getTo());
      helper.setText(html, true);
      helper.setSubject(messageDTO.getSubject());
      helper.setFrom(from);
      javaMailSender.send(message);

      log.info("END... Email sent successfully");
    } catch (MessagingException e) {
      log.error("Email sent with error: {}", e.getMessage());
    }
  }
}
