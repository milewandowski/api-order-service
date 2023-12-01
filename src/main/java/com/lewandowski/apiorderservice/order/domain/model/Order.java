package com.lewandowski.apiorderservice.order.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    String id;
    String name;
    String phoneNumber;
    String emailAddress;
    Address address;
    BigDecimal amount;
    Status status;
    List<String> productIds;
}
