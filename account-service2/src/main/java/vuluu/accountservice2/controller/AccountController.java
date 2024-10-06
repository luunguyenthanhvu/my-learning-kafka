import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vuluu.accountservice2.model.AccountDTO;
import vuluu.accountservice2.model.MessageDTO;
import vuluu.accountservice2.model.StatisticDTO;

import java.util.Date;

@RestController
@RequestMapping("/account")
public class AccountController {

  private final KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  public AccountController(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @PostMapping("/new")
  public AccountDTO create(@RequestBody AccountDTO requestDTO) {
    StatisticDTO statisticDTO = new StatisticDTO("Account " + requestDTO.getEmail() + " is created", new Date());

    // send notification
    MessageDTO messageDTO = new MessageDTO(requestDTO.getEmail(), requestDTO.getName(), "Welcome to microservices", "Vu Luu coder");

    kafkaTemplate.send("notification", messageDTO.toString());
    kafkaTemplate.send("statistic", statisticDTO.toString());

    return requestDTO;
  }
}
