package personal.jake.apipush.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import personal.jake.apipush.constant.RabbitConstants;

/**
 * 直连型交换机配置
 *
 * @author jake.lin
 * @date 2020/07/30
 */
@Configuration
public class DirectRabbitConfig {

    /**
     * 初始化队列
     */
    @Bean
    public Queue directQueue() {
        return new Queue(RabbitConstants.DIRECT_QUEUE, true);
    }

    /**
     * 初始化交换机
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(RabbitConstants.DIRECT_EXCHANGE, true, false);
    }

    /**
     * 绑定 将队列和交换机绑定，并配置路由键
     */
    @Bean
    public Binding bindingDirect() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(RabbitConstants.DIRECT_ROUTING_KEY);
    }

    /**
     * 不给交换机绑定队列，用于测试消息丢失
     * @return
     */
    @Bean
    public DirectExchange testDirectExchange() {
        return new DirectExchange(RabbitConstants.TEST_DIRECT_EXCHANGE, true, false);
    }
}
