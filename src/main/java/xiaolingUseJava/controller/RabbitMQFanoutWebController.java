package xiaolingUseJava.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/xiaolingUseJava-rabbitmq/fanout")
public class RabbitMQFanoutWebController {


    @Autowired
    AmqpTemplate amqpTemplate;

   @GetMapping(value = "/producer")
    public String producer(@RequestParam("exchangeName") String exchange, @RequestParam("messageData") String messageData){
       amqpTemplate.convertAndSend(exchange, "", messageData);
        return "Message sent to the RabbitMQ Fanout Exchange Successfully";
    }


}
