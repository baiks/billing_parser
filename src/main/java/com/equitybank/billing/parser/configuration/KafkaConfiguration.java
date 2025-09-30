package com.equitybank.billing.parser.configuration;

import com.equitybank.billing.parser.ParserApplication;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.util.backoff.FixedBackOff;

//@Configuration
//@EnableKafka
//public class KafkaConfiguration {
//
//    private final Logger LOGGER = LoggerFactory.getLogger(ParserApplication.class);
//
//    private final TaskExecutor exec = new SimpleAsyncTaskExecutor();
//
//    @Bean
//    public SeekToCurrentErrorHandler errorHandler(KafkaTemplate<Object, Object> template) {
//        return new SeekToCurrentErrorHandler(
//                new DeadLetterPublishingRecoverer(template), new FixedBackOff(1000L, 2));
//    }
//
//    @Bean
//    public RecordMessageConverter converter() {
//        return new StringJsonMessageConverter();
//    }
//
//    @KafkaListener(id = "logGroup", topics = "topicLogs")
//    public void listen(@Payload Object foo) {
////        if (foo.getFoo().startsWith("fail")) {
////            throw new RuntimeException("failed");
////        }
//        this.exec.execute(() -> LOGGER.info("Received: " + foo));
//    }
//
//    @KafkaListener(id = "dltGroup", topics = "topicLogs.DLT")
//    public void dltListen(String in) {
//        this.exec.execute(() -> LOGGER.info("Received from DLT: " + in));
//    }
//
//    @Bean
//    public NewTopic topic() {
//        return new NewTopic("topicLogs", 1, (short) 1);
//    }
//
//    @Bean
//    public NewTopic dlt() {
//        return new NewTopic("topicLogs.DLT", 1, (short) 1);
//    }
//
//}
