package personal.jake.apipush.listener;

import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import personal.jake.apipush.constant.RabbitConstants;
import personal.jake.apipush.http.request.MessageRequest;
import personal.jake.apipush.repository.NotifyMapper;

/**
 * fanout模式 监听器C
 * @author jake.lin
 * @date 2020/07/30
 */
@Component
@RabbitListener(queues = RabbitConstants.FANOUT_QUEUE_C)
public class FanoutListenerC {
    @Autowired
    private NotifyMapper notifyMapper;

    @RabbitHandler
    public void process(String message) {
        MessageRequest request = new Gson().fromJson(message, MessageRequest.class);
        if (StringUtils.hasText(request.getMessage())) {
            request.setMessage(this.getClass().getName() + request.getMessage());
        }
        int addStatus = notifyMapper.add(request);
        System.out.println("fanout交换机模式监听中..." + this.getClass().getName() +  "获取到数据：" + message + "，插入状态：" + addStatus);
    }
}
