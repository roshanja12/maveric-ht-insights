package org.acme.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import lombok.extern.slf4j.Slf4j;
import org.acme.dtos.KafkaRequestDto;
import org.acme.model.InsightsCustomers;
import org.acme.model.InsightsLoans;
import org.acme.model.InsightsSavingsAccount;
import org.acme.repository.InsightsCustomerRepository;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import java.util.Random;

import static org.acme.enums.Type.CUSTOMER_CREATED;
import static org.acme.enums.Type.SAVING_ACCOUNT_CREATED;

/**
 * A bean consuming data from the "test" Kafka topic (mapped to "requests" channel) and giving out a random quote.
 * The result is pushed to the "quotes" Kafka topic.
 */
@Slf4j
@ApplicationScoped
public class InsightsProcessor {


    @Inject
    public InsightsCustomerRepository insightsCustomerRepository;



    private Random random = new Random();

//    @Incoming("requests")
//    @Outgoing("quotes")
//    @Blocking
//    public Quote process(String quoteRequest) throws InterruptedException {
//        Log.info("getting req from requests url");
//        Log.info("sending back  data from quotes" +quoteRequest);
//        // simulate some hard working task
//        Thread.sleep(200);
//        return new Quote(quoteRequest, random.nextInt(100));
//    }


//    @Incoming("requests")
//    @Outgoing("quotes")
//    @Blocking
//    public InsightsCustomers getDetailsFromInsight(InsightsCustomers map)
//    {
//        repository.persist(map);
//        Log.info(" sending a data to quates o" +map);
//        return  map;
//    }

    @Incoming("requests")
    @Outgoing("quotes")
    @Blocking
    public InsightsCustomers getDetailsFromInsight(KafkaRequestDto requestDto) {
        InsightsCustomers customers = null;
        InsightsLoans loans = null;
        InsightsSavingsAccount savingsAccount = null;
        log.info("request ----------------- "+requestDto);


        try {
            if (requestDto.type == CUSTOMER_CREATED) {
                ObjectMapper mapper = new ObjectMapper();
                customers= mapper.convertValue(requestDto.type, InsightsCustomers.class);
                log.info("customerrrrrrrrrrr "+customers);
                //customers = (InsightsCustomers) requestDto.message;
                insightsCustomerRepository.persist(customers);
            } else if (requestDto.type == SAVING_ACCOUNT_CREATED) {

            } else {

            }
            log.info(" sending a data to quates o" + requestDto);
        } catch (Exception e) {
            log.error("getting a details from kafka :: error " + e.getMessage());
        }

        return customers;
    }
}
