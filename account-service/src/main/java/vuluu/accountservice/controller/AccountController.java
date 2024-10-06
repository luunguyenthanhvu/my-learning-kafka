package vuluu.account_service.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vuluu.account_service.model.AccountDTO;
import vuluu.account_service.model.MessageDTO;
import vuluu.account_service.model.StatisticDTO;

@RestController
@RequestMapping("/account")
public class AccountController {

  @Autowired
  KafkaTemplate<String, Object> kafkaTemplate;

  @PostMapping("/new")
  public AccountDTO create(@RequestBody AccountDTO requestDTO) {
    StatisticDTO statisticDTO = new StatisticDTO("Account " + requestDTO.getEmail() + "is created",
        new Date());

    // send notification
    MessageDTO messageDTO = new MessageDTO(requestDTO.getEmail(),
        requestDTO.getName()
        , "Welcome to microservices",
        "Vu Luu coder");

    kafkaTemplate.send("notification", messageDTO);
    kafkaTemplate.send("statistic", statisticDTO);

    return requestDTO;
  }
}
