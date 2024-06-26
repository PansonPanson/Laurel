package top.panson.framework.pay.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author linhao
 * @Date created in 1:23 下午 2022/3/19
 */
@SpringBootApplication
public class PayApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(PayApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }
}
