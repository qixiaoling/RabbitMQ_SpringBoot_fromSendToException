package xiaolingUseJava.service;

import xiaolingUseJava.model.Employee;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class RabbitMQSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Value("{xiaolingUseJava.rabbitmq.exchange}")
    private String exchange;
    @Value("{xiaolingUseJava.rabbitmq.routingKey}")
    private String routingKey;

    public void send(Employee employee){
        rabbitTemplate.convertAndSend(exchange, routingKey, employee);
        System.out.println("Sen msg = " + employee);
    }

}
