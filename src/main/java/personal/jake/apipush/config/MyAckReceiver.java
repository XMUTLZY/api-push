package personal.jake.apipush.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;

/**
 * 消息接收处理类
 * @author jake.lin
 * @date 2020/07/30
 */
@Component
public class MyAckReceiver implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws IOException {
        // 消息编号
        long tag = message.getMessageProperties().getDeliveryTag();
        try {
            channel.basicAck(tag, true);
            // 获取消息
            String msg = message.toString();
            String[] msgArray = msg.split("'");
            System.out.println("收到的消息为:" + msgArray[1] + ",来源队列:" + message.getMessageProperties().getConsumerQueue());
        } catch (IOException e) {
            channel.basicReject(tag, false);
            e.printStackTrace();
        }

    }

    @Override
    public void onMessage(Message message) {

    }

    @Override
    public void containerAckMode(AcknowledgeMode mode) {

    }

    @Override
    public void onMessageBatch(List<Message> messages) {

    }

    @Override
    public void onMessageBatch(List<Message> messages, Channel channel) {

    }
}
