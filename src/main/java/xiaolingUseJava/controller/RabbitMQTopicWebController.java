package xiaolingUseJava.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/xiaolingUseJava-rabbitmq/topic")
public class RabbitMQTopicWebController {
    //Let op, service is deleted here, we just call the send method from the controller only,
    //because by every exchange, the routingkey will be different, therefore would make more sense to write individual controller.


    @Autowired
    private AmqpTemplate amqpTemplate;

   @GetMapping(value = "/producer")
    public String producer(@RequestParam("exchangeName") String exchange, @RequestParam("routingKey") String routingKey, @RequestParam("messageData") String messageData){
        amqpTemplate.convertAndSend(exchange, routingKey, messageData);
        return "Message sent to the RabbitMQ Topic Exchange Successfully";
    }


}
