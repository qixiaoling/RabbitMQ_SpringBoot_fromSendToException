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

    //this is different from the master because it needs different exhcnage name and routing key for both deadLetterqueue and normal queue,
    //therefore we dont use property to define the queue name and exchange name and routing key, but we hard code for each queue and exchange and binding.
    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange("deadLetterExchange");
    }
    @Bean
    DirectExchange normalExchange(){
        return new DirectExchange("xiaolingUseJavaExchange");
    }

    @Bean
    Queue deadLetterQueue(){
        return QueueBuilder.durable("deadLetter.queue").build();
    }
    @Bean
    Queue normalQueue(){
        return QueueBuilder.durable("normal.queue").withArgument("x-dead-letter-exchange", "deadLetterExchange")
                .withArgument("x-dead-letter-routing-key", "deadLetter").build();
    }


    @Bean
    Binding DLQbinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with("deadLetter");
    }
    @Bean
    Binding normalBinding(){
        return BindingBuilder.bind(normalQueue()).to(normalExchange()).with("normal");
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
