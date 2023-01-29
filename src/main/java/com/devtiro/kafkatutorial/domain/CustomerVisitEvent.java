package com.devtiro.kafkatutorial.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerVisitEvent {

    private String customerId;

    private LocalDateTime dateTime;

}
