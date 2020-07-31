package personal.jake.apipush.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
//@PropertySource("classpath:application.yml")
public class JdbcConfig {
    @Value("${db.user-name}")
    private String dbUserName;

    @Bean
    public void test() {
        System.out.println(this.dbUserName);
    }
}
