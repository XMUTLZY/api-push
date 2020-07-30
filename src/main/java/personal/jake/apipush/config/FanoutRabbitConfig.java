package personal.jake.apipush.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import personal.jake.apipush.constant.RabbitConstants;

/**
 * 扇形交换机配置，三个队列都绑定在一个交换机上
 *
 * @author jake.lin
 * @date 2020/07/30
 */
@Configuration
public class FanoutRabbitConfig {
    @Bean
    public Queue fanoutQueueA() {
        return new Queue(RabbitConstants.FANOUT_QUEUE_A);
    }

    @Bean
    public Queue fanoutQueueB() {
        return new Queue(RabbitConstants.FANOUT_QUEUE_B);
    }

    @Bean
    public Queue fanoutQueueC() {
        return new Queue(RabbitConstants.FANOUT_QUEUE_C);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitConstants.FANOUT_EXCHANGE);
    }

    @Bean
    public Binding bindingExchangeA() {
        return BindingBuilder.bind(fanoutQueueA()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingExchangeB() {
        return BindingBuilder.bind(fanoutQueueB()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingExchangeC() {
        return BindingBuilder.bind(fanoutQueueC()).to(fanoutExchange());
    }

}
