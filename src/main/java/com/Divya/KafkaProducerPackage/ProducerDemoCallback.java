package com.Divya.KafkaProducerPackage;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


public class ProducerDemoCallback {

    public static void main(String[] args){

        final Logger logger = LoggerFactory.getLogger(ProducerDemoCallback.class);

        String bootstrapServer = "127.0.0.1:9092";

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);

        for(int i =0; i<10; i++) {
            String topic = "first_topic";
            String value = "hello world" + Integer.toString(i);
            String key = "id " +Integer.toString(i);
            ProducerRecord<String, String> record = new ProducerRecord(topic,key,value);

            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    // executes everytime a record is send or an exception is thrown
                    if (e == null) {
                        //received meta data
                        logger.info("Received meta data : \n" + "\n " +
                                "Topic:" + recordMetadata.topic() + "\n" +
                                "Partitions: " + recordMetadata.partition() + "\n" +
                                "Offset: " + recordMetadata.offset() + "\n " +
                                "Timestamp: " + recordMetadata.timestamp());
                    } else {
                        logger.error("Error while producing", e);
                    }

                }
            });
        }
        producer.flush();
        producer.close();

    }
}
