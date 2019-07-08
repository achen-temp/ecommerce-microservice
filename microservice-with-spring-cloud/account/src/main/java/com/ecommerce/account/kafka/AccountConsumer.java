package com.ecommerce.account.kafka;


import com.ecommerce.account.domain.Order;
import com.ecommerce.account.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Properties;

import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;

@Component
public class AccountConsumer implements Runnable{

    private final KafkaConsumer kafkaConsumer;
    private static final String ACCOUNT_TOPIC = "########-account";

    @Autowired
    AccountService service;

    @Override
    public void run() {
        try{
            while(true){
                ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                for(ConsumerRecord<String, String> record : records){
                    System.out.println(">>>Account<<< ::: Received Order History : " + record);
                    String orderHistoryAsString = record.value();
                    try{
                        ObjectMapper mapper = new ObjectMapper();
                        Order order = mapper.readValue(orderHistoryAsString, Order.class);
                        service.updateOrderHistory(order);
                    }catch (Exception e){
                        System.out.println("Error Converting payload to Order history object");
                    }
                }
            }
        }catch (Exception e){
            System.out.println("Account consumer exit with Exception!");
        }finally {
            kafkaConsumer.close();
            System.out.println("Account Kafka Consumer is closed");
        }
    }


    public AccountConsumer() {
        String BROKERS  = "##########-##.srvs.cloudkafka.com:####";
        String USERNAME = "########";
        String PASSWORD = "3C_nEFInwNE########03e4C3EFtQyB";
        String GROUP_ID = "Account-Consumer";
        String JAASTEMPLATE = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
        String JAASCFG = String.format(JAASTEMPLATE, USERNAME, PASSWORD);

        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, BROKERS);
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(GROUP_ID_CONFIG, GROUP_ID);
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "SCRAM-SHA-256");
        props.put("sasl.jaas.config", JAASCFG);

        kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.subscribe(Arrays.asList(ACCOUNT_TOPIC));

        Thread thread = new Thread(this);
        thread.start();
    }



}
