package thi.nguyen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableBinding(Sink.class)
public class NotificationApplication {

	@StreamListener(Sink.INPUT)
	public void consumeMessage(String a){
		System.out.println("Message" + a);
	}

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}

}
