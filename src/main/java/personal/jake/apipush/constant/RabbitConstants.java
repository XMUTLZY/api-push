package personal.jake.apipush.constant;

/**
 * @author jake.lin
 * @date 2020/07/30
 */
public class RabbitConstants {
    public static final String DIRECT_ROUTING_KEY = "directRouting";
    public static final String DIRECT_QUEUE = "directQueue";
    public static final String DIRECT_EXCHANGE = "directExchange";

    public static final String FANOUT_QUEUE_A = "fanoutQueueA";
    public static final String FANOUT_QUEUE_B = "fanoutQueueB";
    public static final String FANOUT_QUEUE_C = "fanoutQueueC";
    public static final String FANOUT_EXCHANGE = "fanoutExchange";

    public static final String TOPIC_QUEUE_A = "topicQueueA";
    public static final String TOPIC_QUEUE_B = "topicQueueB";
    public static final String TOPIC_EXCHANGE = "topicExchange";
    public static final String TOPIC_ROUTING_KEY_A = "topic.man";
    public static final String TOPIC_ROUTING_KEY_B = "topic.woman";
}
