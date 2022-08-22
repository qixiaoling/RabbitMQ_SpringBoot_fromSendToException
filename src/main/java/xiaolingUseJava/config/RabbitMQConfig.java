package xiaolingUseJava.config;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xiaolingUseJava.service.RabbitMQSender;

@Configuration
public class RabbitMQConfig {

    @Value("${xiaolingUseJava.rabbitmq.queue}")
    String queueName;
    @Value("${xiaolingUseJava.rabbitmq.exchange}")
    String exchange;
    @Value("${xiaolingUseJava.rabbitmq.routingkey}")
    private String routingKey;

    @Bean
    Queue queue(){
        return new Queue(queueName, false);
    }

   @Bean
    DirectExchange exchange(){
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }


    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitMQSender rabbitMQSender(){
        return new RabbitMQSender();
    }

    @Bean
    public AmqpTemplate rabbitTemplateMethod( ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplateXL = new RabbitTemplate(connectionFactory);
        rabbitTemplateXL.setMessageConverter(jsonMessageConverter());
        return rabbitTemplateXL;
    }

}
