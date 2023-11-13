package ru.ttttinkoffhackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.ttttinkoffhackathon.configuration.BotConfig;

@SpringBootApplication
@EnableConfigurationProperties({BotConfig.class})
public class TttTinkoffHackathonApplication {

    public static void main(String[] args) {
        SpringApplication.run(TttTinkoffHackathonApplication.class, args);
    }

}
