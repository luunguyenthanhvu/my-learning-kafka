package vuluu.kafkaproducerexample.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vuluu.kafkaproducerexample.service.KafkaMessagePublisher;

@RestController
@RequestMapping("/producer-app")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventController {

  KafkaMessagePublisher publisher;
  KafkaTemplate<String, String> template;

  @GetMapping("/publish/{message}")
  public ResponseEntity<?> publishMessage(@PathVariable String message) {
    try {
      publisher.sendMessageTopic(message);
      template.send("test2", message +
          "của test 2");
      return ResponseEntity.ok("Message published successfully.....");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
