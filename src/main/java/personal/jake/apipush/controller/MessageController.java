package personal.jake.apipush.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import personal.jake.apipush.http.request.MessageRequest;
import personal.jake.apipush.service.MessageService;

/**
 * @author jake.lin
 * @date 2020/07/29
 */
@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * 发送短信或者推送消息（DB模拟）
     */
    @PostMapping("/message")
    public ResponseEntity<String> message(@RequestBody MessageRequest request) {
        return messageService.message(request);
    }
}
