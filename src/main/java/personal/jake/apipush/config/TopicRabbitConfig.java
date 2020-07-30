package personal.jake.apipush.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import personal.jake.apipush.constant.RabbitConstants;


/**
 * @author jake.lin
 * @date 2020/07/30
 */
@Configuration
public class TopicRabbitConfig {

    @Bean
    public Queue topicQueueA() {
        return new Queue(RabbitConstants.TOPIC_QUEUE_A);
    }

    @Bean
    public Queue topicQueueB() {
        return new Queue(RabbitConstants.TOPIC_QUEUE_B);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitConstants.TOPIC_EXCHANGE);
    }

    /**
     * 队列和交换机绑定，路由键为topic.man
     * @return
     */
    @Bean(name = "bindingTopicExchangeA")
    public Binding bindingExchangeA() {
        return BindingBuilder.bind(topicQueueA()).to(topicExchange()).with(RabbitConstants.TOPIC_ROUTING_KEY_A);
    }

    /**
     * 队列和交换机绑定，路由键为topic.#
     * @return
     */
    @Bean(name = "bindingTopicExchangeB")
    public Binding bindingExchangeB() {
        return BindingBuilder.bind(topicQueueB()).to(topicExchange()).with("topic.#");
    }
}
