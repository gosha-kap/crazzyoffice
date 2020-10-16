package ru.crazzyoffice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import ru.crazzyoffice.bots.CrazyBot;
import ru.crazzyoffice.bots.api.ArduinoSendRequest;

@Configuration
@ComponentScan(basePackages = "ru.crazzyoffice.bots")
@PropertySource("classpath:telegram.properties")
public class BotConfig {

    @Autowired
    private Environment environment;


    @Bean
    public ArduinoSendRequest  arduinoSendRequest(){
        return new ArduinoSendRequest(environment.getProperty("arduino.request.pologaya"),
                                        environment.getProperty("arduino.request.garage"));
    }

   @Bean
    public CrazyBot CrazyBot() {
        DefaultBotOptions options = ApiContext
                .getInstance(DefaultBotOptions.class);

        options.setProxyHost(environment.getProperty("telegrambot.proxyHost"));
        options.setProxyPort(Integer.parseInt(
                                environment.getProperty("telegrambot.proxyPort")));
        options.setProxyType(DefaultBotOptions.ProxyType.valueOf(
                environment.getProperty("telegrambot.proxyType")));

       CrazyBot crazyBot = new CrazyBot(options);
       crazyBot.setBotUserName(environment.getProperty("telegrambot.userName"));
       crazyBot.setBotToken(environment.getProperty("telegrambot.botToken"));
       crazyBot.setBotPath(environment.getProperty("telegrambot.webHookPath"));

       return crazyBot;
    }

}
