package controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo-app")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class DemoController {

  @KafkaListener(topics = "onboard-successful")
  public void listen(String message) {
    System.out.println("MEssage nè");
    log.info("Message get " + message);
  }

  @KafkaListener(topics = "test2")
  public void test2(String message) {
    System.out.println("Messsage test 2");
    log.info("MEssage get" + message);
  }
}
