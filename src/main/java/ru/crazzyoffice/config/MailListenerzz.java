package ru.crazzyoffice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.integration.mail.support.DefaultMailHeaderMapper;
import org.springframework.integration.mapping.HeaderMapper;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.support.PeriodicTrigger;
import ru.crazzyoffice.service.MailService;

import javax.mail.internet.MimeMessage;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Properties;

@Configuration
@EnableIntegration

public class MailListenerzz {

    @Bean
    public HeaderMapper<MimeMessage> mailHeaderMapper() {
        return new DefaultMailHeaderMapper();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata defaultPoller() {

        PollerMetadata pollerMetadata = new PollerMetadata();
        pollerMetadata.setTrigger(new PeriodicTrigger(1000));
        return pollerMetadata;
    }



    @Bean
    public IntegrationFlow mailListener5() {
        System.out.println("111111");

        Properties props = new Properties();
       // props.setProperty("mail.debug", "true");
        props.setProperty("mail.imap.ssl.enable", "false");
        props.setProperty("mail.imap.starttls.enable","false");


        return IntegrationFlows.from(
                Mail.imapInboundAdapter(
                        "imap://" +
                                URLEncoder.encode("control@mikrotik.vsk", Charset.defaultCharset())
        + ":3453@10.210.251.250:143/INBOX"
                ).simpleContent(true)
                 .javaMailProperties(props),
                 e -> e.autoStartup(true)
                         .poller(p -> p.fixedDelay(5000)))
                .transform(Mail.toStringTransformer())
                .channel(MessageChannels.queue("imapChannel"))
                .get();



    }

}
