package org.ivanov.userservice.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

@Service
public class MessageService {
    @Value("${kafka.host}")
    private String kafkaHost;

    @Async
    public void readMessages() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", kafkaHost);
        props.setProperty("group.id", "mystery-consumers");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("auto.commit.interval.ms", "20000");


                KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
                consumer.subscribe(List.of("test_topic"));

                System.out.println("Создан потребитель ");

                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(5000));
                    for (ConsumerRecord<String, String> record : records) {
                        // Вычисляем разницу времени между отправкой и получением сообщения (в миллисекундах)
                        long timeToReceive = System.currentTimeMillis() - record.timestamp();
                        System.out.printf(" сообщение: %s, ключ: %s, номер партиции: %d, офсет: %d, время на доставку: %d%n",
                                record.value(), record.key(), record.partition(), record.offset(), timeToReceive);
                    }
                }
    }
}
