package xiaolingUseJava.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQFanOutConfig {

    //this is different from the master because it needs different exhcnage name and routing key for both deadLetterqueue and normal queue,
    //therefore we dont use property to define the queue name and exchange name and routing key, but we hard code for each queue and exchange and binding.
    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange("fanout-exchange");
    }
    @Bean
   Queue marketingQueue(){
        return new Queue("marketingQueue",false);
   }
    @Bean
   Queue financeQueue(){
        return new Queue("financeQueue", false);
   }
   @Bean
   Queue adminQueue(){
        return new Queue("admin", false);
   }
   @Bean
   Binding marketingBinding(Queue marketingQueue, FanoutExchange exchange){
        return BindingBuilder.bind(marketingQueue).to(exchange);
   }
    @Bean
    Binding financeBinding(Queue financeQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(financeQueue).to(exchange);
    }

    @Bean
    Binding adminBinding(Queue adminQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(adminQueue).to(exchange);
    }

 //We don't need to convert json to class here, therefore we don't need below beans, we just use the defaul AmqpTemplate in the controller.
    /*@Bean
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
    }*/

}
