package org.transactionService.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


/**
 * This class is used as a data transfer object for creating transaction.
 *
 * @author rpranay665@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTransactionRequest {
    @NotBlank(message = "Receiver is mandatory.")
    private String receiver;
    @NotBlank(message = "Sender is mandatory.")
    private String sender;
    @Min(value = 1, message = "Amount must be at least 1 rupees")
    private Long amount;
    private String reason;
}
