package xiaolingUseJava.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopicConfig {
    @Bean
    Queue marketingQueue(){
        return new Queue("marketingQueue", false);
    }

    @Bean
    Queue financeQueue(){
        return new Queue("financeQueue", false);
    }

    @Bean
    Queue adminQueue(){
        return new Queue("adminQueue", false);
    }
    @Bean
    Queue allQueue(){
        return new Queue("allQueue", false);
    }
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }
    @Bean
    Binding marketingBinding(Queue marketingQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(marketingQueue).to(topicExchange).with("queue.marketing");
    }

    @Bean
    Binding financeBinding(Queue financeQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(financeQueue).to(topicExchange).with("queue.finance");
    }

    @Bean
    Binding adminBinding(Queue adminQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(adminQueue).to(topicExchange).with("queue.admin");
    }
    @Bean
    Binding allBinding(Queue allQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(allQueue).to(topicExchange).with("queue.*");
    }

}
