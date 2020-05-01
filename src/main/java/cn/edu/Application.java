package cn.edu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName Application
 * @Description TODO
 * @Author wys5
 * @Date 2020/2/14 17:15
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScan("cn.edu.dao")
@EntityScan("cn.edu.vo")
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class,args);
    }
}
