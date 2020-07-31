package personal.jake.apipush.service;

import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import personal.jake.apipush.constant.RabbitConstants;
import personal.jake.apipush.constant.MessageConstants;
import personal.jake.apipush.http.request.MessageRequest;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author jake.lin
 * @date 2020/07/29
 */
@Service
public class MessageService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public ResponseEntity message(MessageRequest request) {
        if (request.getType() == null) {
            return new ResponseEntity("参数错误", HttpStatus.BAD_REQUEST);
        }
        if (MessageConstants.TYPE_SMS.equals(request.getType())) {
            return sendSms(request);
        }
        if (MessageConstants.TYPE_NOTIFY.equals(request.getType())) {
            return sendNotify(request);
        }
        if (MessageConstants.TYPE_TOPIC.equals(request.getType())) {
            sendTopicA(request);
            sendTopicB(request);
        }
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    /**
     * fanout交换机模式，发送短信
     * @param request
     * @return
     */
    private ResponseEntity sendSms(MessageRequest request) {
        request.setCreateTime(LocalDateTime.now());
        String messageStr = new Gson().toJson(request);
        rabbitTemplate.convertAndSend(RabbitConstants.FANOUT_EXCHANGE, null, messageStr);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    /**
     * direct交换机模式，推送通知
     * @param request
     * @return
     */
    private ResponseEntity sendNotify(MessageRequest request) {
        request.setCreateTime(LocalDateTime.now());
        String messageStr = new Gson().toJson(request);
        rabbitTemplate.convertAndSend(RabbitConstants.DIRECT_EXCHANGE, RabbitConstants.DIRECT_ROUTING_KEY, messageStr);
        return new ResponseEntity("推送消息成功", HttpStatus.OK);
    }

    /**
     * topic交换机模式  A
     * @param request
     * @return
     */
    private ResponseEntity sendTopicA(MessageRequest request) {
        request.setCreateTime(LocalDateTime.now());
        String messageStr = new Gson().toJson(request);
        rabbitTemplate.convertAndSend(RabbitConstants.TOPIC_EXCHANGE, RabbitConstants.TOPIC_ROUTING_KEY_A, messageStr);
        return new ResponseEntity("推送消息成功", HttpStatus.OK);
    }

    /**
     * topic交换机模式  B
     * @param request
     * @return
     */
    private ResponseEntity sendTopicB(MessageRequest request) {
        request.setCreateTime(LocalDateTime.now());
        String messageStr = new Gson().toJson(request);
        rabbitTemplate.convertAndSend(RabbitConstants.TOPIC_EXCHANGE, RabbitConstants.TOPIC_ROUTING_KEY_B, messageStr);
        return new ResponseEntity("推送消息成功", HttpStatus.OK);
    }

    @Async
    public void doTaskA() throws InterruptedException {
        System.out.println("TaskA thread name->" + Thread.currentThread().getName());
        Long startTime = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(2);
        Long endTime = System.currentTimeMillis();
        System.out.println("TaskA 耗时：" + (endTime - startTime));
    }

    @Async
    public void doTaskB() throws InterruptedException {
        System.out.println("TaskB thread name->" + Thread.currentThread().getName());
        Long startTime = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(2);
        Long endTime = System.currentTimeMillis();
        System.out.println("TaskB耗时：" + (endTime - startTime));
    }
}
