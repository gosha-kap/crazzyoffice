package ru.crazzyoffice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import ru.crazzyoffice.bots.CrazyBot;
import ru.crazzyoffice.bots.commands.ArduinoSendRequest;
import ru.crazzyoffice.bots.commands.SendRequest;

@Configuration
@ComponentScan(basePackages = "ru.crazzyoffice.bots")
@PropertySource("classpath:telegram.properties")
public class BotConfig {

    @Autowired
    private Environment environment;

    @Bean
    public SendRequest arduinoSendRequest() {
        return new ArduinoSendRequest(environment.getProperty("request"));
    }

   @Bean
    public CrazyBot CrazyBot() {

       CrazyBot crazyBot = new CrazyBot();
       crazyBot.setBotUserName(environment.getProperty("telegrambot.userName"));
       crazyBot.setBotToken(environment.getProperty("telegrambot.botToken"));
       crazyBot.setBotPath(environment.getProperty("telegrambot.webHookPath"));

       return crazyBot;
    }

}
