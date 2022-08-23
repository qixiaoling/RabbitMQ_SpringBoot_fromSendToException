package xiaolingUseJava.service;

import org.springframework.stereotype.Service;
import xiaolingUseJava.model.Employee;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
@Service
public class RabbitMQSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(Employee employee){
        rabbitTemplate.convertAndSend("xiaolingUseJavaExchange", "xiaolingUseJava", employee);

    }

}
