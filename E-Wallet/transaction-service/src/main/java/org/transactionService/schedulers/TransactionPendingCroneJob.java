package org.transactionService.schedulers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.transactionService.dto.GetUserResponse;
import org.transactionService.model.Transaction;
import org.transactionService.model.TransactionStatus;
import org.transactionService.service.FeignUserService;
import org.transactionService.service.TransactionService;

import java.util.List;

@Component
@Slf4j
public class TransactionPendingCroneJob {
    @Autowired
    TransactionService transactionService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String TRANSACTION_COMPLETED_TOPIC = "transaction-completed";

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    FeignUserService feignUserService;

    @Scheduled(cron = "0 01 21 1/1 * ?",zone = "Asia/Kolkata")
    public void MakeTransactionPendingStateAsFailed() throws JsonProcessingException {
        log.info("In Crone Job");
        List<Transaction> transactionList=transactionService.getPendingTransactions();
        for(int i=0;i<transactionList.size();i++)
        {
            log.info("For Every Transaction of Status PENDING marked as FAILED");
            transactionList.get(i).setTransactionStatus(TransactionStatus.FAILED);
            transactionService.saveTransaction(transactionList.get(i));
            GetUserResponse senderUserDetails=feignUserService.getUserByPhone(transactionList.get(i).getSenderId());
            GetUserResponse recieverUserDetails=feignUserService.getUserByPhone(transactionList.get(i).getReceiverId());
            JSONObject obj=new JSONObject();
            obj = new JSONObject();
            //Following fields needed to listen to Notification Service for sending/receiving notification mail to user.
            obj.put("transactionId", transactionList.get(i).getExternalId());
            obj.put("transactionStatus", transactionList.get(i).getTransactionStatus());
            obj.put("amount", transactionList.get(i).getAmount());
            obj.put("senderEmail", senderUserDetails.getEmail());
            obj.put("receiverEmail", recieverUserDetails.getEmail());
            obj.put("senderPhone", senderUserDetails.getPhone());
            obj.put("receiverPhone", recieverUserDetails.getPhone());
            kafkaTemplate.send(TRANSACTION_COMPLETED_TOPIC, this.objectMapper.writeValueAsString(obj));
        }
    }
}
