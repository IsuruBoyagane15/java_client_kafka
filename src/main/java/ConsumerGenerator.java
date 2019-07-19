import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerGenerator {

    public static KafkaConsumer<String, String> generateConsumer(String kafkaServer, String topic) {

        Properties props = new Properties();

        props.put("bootstrap.servers", kafkaServer);
        props.put("group.id",topic); //specifies consumer group - all the clients which want to come to concences should be part of same group
        props.put("enable.auto.commit","true"); //let consumer to commit most lrecently read offset to kafka - default behaviour
        props.put("auto.commit.interval.ms","1000");
        String deserializer = "org.apache.kafka.common.serialization.StringDeserializer";
        props.put("key.deserializer",deserializer);
        props.put("value.deserializer",deserializer);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList(topic));
        return consumer;
    }


}