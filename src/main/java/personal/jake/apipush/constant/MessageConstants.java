package personal.jake.apipush.constant;

/**
 * @author jake.lin
 * @date 2020/07/29
 */
public class MessageConstants {
    /**
     * 发送短信 模拟Fanout交换机模式
     */
    public static final Integer TYPE_SMS = 1;
    /**
     * 推送消息，这里用DB增加操作模拟 模拟Direct交换机模式
     */
    public static final Integer TYPE_NOTIFY = 2;
    /**
     * 模拟Topic交换机模式
     */
    public static final Integer TYPE_TOPIC = 3;
}
