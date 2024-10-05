package vuluu.kafkaproducerexample.service;

import java.util.concurrent.CompletableFuture;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class KafkaMessagePublisher {

  KafkaTemplate<String, Object> template;

  public void sendMessageTopic(String message) {
    CompletableFuture<SendResult<String, Object>> future = template.send("onboard-successful", message);
    future.whenComplete((result, ex) -> {
      if (ex == null) {
        System.out.println(
            "Sent message =[ " + message + " ] with offset = [" + result.getRecordMetadata()
                .offset() + "]");
      } else {
        System.out.println(
            "Unable to send message = [ " + message + " ] due to: " + ex.getMessage());
      }
    });

  }
}
