package id.corp.kafkaproducer.resources;

import id.corp.kafkaproducer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class UserResources {

    @Autowired
    KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "Kafka_Example";

    @PostMapping(value = "/publish/{name}")
    public String post(@PathVariable("name") final String name){

        kafkaTemplate.send(TOPIC, new User(name,"Java developer", 1600000l));

        return "published successfully "+name;
    }
}
