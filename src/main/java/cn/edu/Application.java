package cn.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
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
@EntityScan("cn.edu.entity")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
