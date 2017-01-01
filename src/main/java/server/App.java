package server;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bobcowher on 12/27/16.
 */

@SpringBootApplication
public class App {

    @RequestMapping("/")
    String home() {
        return "Synthia Licensing Server V1.0";
    }

    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }

}
