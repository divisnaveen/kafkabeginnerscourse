package com.Divya.KafkaProducerPackage;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;


public class KafkaProducerDemo {

    public static void main(String[] args){
        String bootstrapServer = "127.0.0.1:9092";

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);
        ProducerRecord<String,String> record = new ProducerRecord("first_topic", "Kafka java progamming");

        producer.send(record);
        producer.flush();
        producer.close();

    }
}
