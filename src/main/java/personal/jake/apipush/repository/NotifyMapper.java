package personal.jake.apipush.repository;

import org.springframework.stereotype.Repository;
import personal.jake.apipush.http.request.MessageRequest;

/**
 * @author jake.lin
 * @date 2020/07/29
 */
@Repository
public interface NotifyMapper {
    int add(MessageRequest request);
}
