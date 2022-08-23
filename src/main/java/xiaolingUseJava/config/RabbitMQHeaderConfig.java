package xiaolingUseJava.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQHeaderConfig {
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
    HeadersExchange headersExchange() {
        return new HeadersExchange("header-exchange");
    }
    @Bean
    Binding marketingBinding(Queue marketingQueue, HeadersExchange headersExchange){
        return BindingBuilder.bind(marketingQueue).to(headersExchange).where("department").matches("marketing");
    }

    @Bean
    Binding financeBinding(Queue financeQueue, HeadersExchange headersExchange){
        return BindingBuilder.bind(financeQueue).to(headersExchange).where("department").matches("finance");
    }

    @Bean
    Binding adminBinding(Queue adminQueue, HeadersExchange headersExchange){
        return BindingBuilder.bind(adminQueue).to(headersExchange).where("department").matches("admin");
    }

}
