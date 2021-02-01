package personal.jake.apipush.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息确认机制 生产者
 * @author jake.lin
 * @date 2020/07/30
 */
@Configuration
public class ProducerMessageConfirmConfig {
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                if (b) {
                    System.out.println("message success");
                } else {
                    System.out.println("message failed");
                }
            }
        });

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                System.out.println("消息主体 message : " + message);
                System.out.println("消息主体 replyCode : " + i);
                System.out.println("描述 replyText：" + s);
                System.out.println("消息使用的交换器 exchange : " + s1);
                System.out.println("消息使用的路由键 routing : " + s2);
            }
        });

        return rabbitTemplate;
    }
}
