package personal.jake.apipush.listener;

import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import personal.jake.apipush.constant.RabbitConstants;
import personal.jake.apipush.http.request.MessageRequest;
import personal.jake.apipush.repository.NotifyMapper;

/**
 * 创建Direct模式监听器，监听队列
 *
 * @author jake.lin
 * @date 2020/07/30
 */
@Component
@RabbitListener(queues = RabbitConstants.DIRECT_QUEUE)
public class DirectListener {
    @Autowired
    private NotifyMapper notifyMapper;

    @RabbitHandler
    public void process(String message) {
        int addStatus = notifyMapper.add(new Gson().fromJson(message, MessageRequest.class));
        System.out.println("direct交换机模式监听中..." + this.getClass().getName() + "获取到数据：" + message + "，插入状态：" + addStatus);
    }
}
