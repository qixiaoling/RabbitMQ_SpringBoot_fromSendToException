package xiaolingUseJava.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/xiaolingUseJava-rabbitmq/header")
public class RabbitMQHeaderWebController {
    //Let op, service is deleted here, we just call the send method from the controller only,
    //because by every exchange, the routingkey will be different, therefore would make more sense to write individual controller.


    @Autowired
    private AmqpTemplate amqpTemplate;

   @GetMapping(value = "/producer")
    public String producer(@RequestParam("exchangeName") String exchange, @RequestParam("department") String department, @RequestParam("messageData") String messageData){
       MessageProperties messageProperties = new MessageProperties();
       messageProperties.setHeader("department", department);
       MessageConverter messageConverter = new SimpleMessageConverter();
       Message message = messageConverter.toMessage(messageData, messageProperties);
       amqpTemplate.send(exchange, "", message);
       return "Message sent to the RabbitMQ Header Exchange Successfully";
    }


}
