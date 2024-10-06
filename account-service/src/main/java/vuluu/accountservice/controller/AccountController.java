package vuluu.accountservice.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vuluu.accountservice.model.AccountDTO;
import vuluu.accountservice.model.MessageDTO;
import vuluu.accountservice.model.StatisticDTO;


@RestController
@RequestMapping("/account")
public class AccountController {

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
