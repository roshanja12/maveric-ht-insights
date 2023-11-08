package org.acme.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.enums.Type;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "insight_savings_account")
public class InsightsSavingsAccount {
    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false)
    String status;

    @Column(name="customer_id",nullable = false)
    Long customerId;

    @Column(name="min_opening_balance",nullable = false)
    BigDecimal minOpeningBalance;

    @Column(name="interest_compounding_period",nullable = false)
    BigDecimal interestCompoundingPeriod;

    @Column(name="allow_over_draft",nullable = false)
    Boolean allowOverDraft;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Type type;

    @CreationTimestamp
    Instant createdAt;

    @Column(nullable = false)
    String city;
}
