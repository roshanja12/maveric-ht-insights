package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.acme.enums.Type;
import org.acme.model.InsightsCustomers;
import org.acme.repository.InsightsCustomerRepository;
import org.jboss.logging.Logger;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ankushk
 */

@ApplicationScoped
@Slf4j
public class InsightsService {

    @Inject
    InsightsCustomerRepository insightsCustomerRepository;

    @Transactional
    public Map<String, Long> getCustomerCountByMonth(int year) {
        try {
            log.info("getCustomerCountByMonth service called");
            List<InsightsCustomers> customers = insightsCustomerRepository.list("year", year);

            Map<String, Long> customerCountByMonth = customers.stream()
                    .filter(customer -> Type.CUSTOMER_CREATED.equals(customer.type))
                    .collect(Collectors.groupingBy(InsightsCustomers::getMonth, Collectors.counting()));

            List<String> orderedMonths = Arrays.asList(
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"
            );

            Map<String, Long> sortedCustomerCountByMonth = new LinkedHashMap<>();
            orderedMonths.forEach(month -> sortedCustomerCountByMonth.put(month, customerCountByMonth.getOrDefault(month, 0L)));

            return sortedCustomerCountByMonth;
        } catch (Exception e) {
            log.error("An error occurred in getCustomerCountByMonth", e);
            throw new RuntimeException("Error in getCustomerCountByMonth", e);
        }
    }

}
