package org.acme.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.enums.LoanStatus;
import org.acme.enums.Type;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "insight_loans")
public class InsightsLoans {

    @Id
    @GeneratedValue
    public  Long id;
    @Enumerated(EnumType.STRING)
    @Column(name="loan_status",nullable = false)
    LoanStatus loanStatus;
    @Column(name="customer_id",nullable = false)
    Integer customerId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Type type;
    @CreationTimestamp
    Instant createdAt;
    @Column(nullable = false)
    BigDecimal amount;
}
