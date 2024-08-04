package org.transactionService.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.transactionService.dto.GetUserResponse;

@FeignClient("USER-SERVICE")
public interface FeignUserService {
    @GetMapping("/user/phone/{phone}")
    public GetUserResponse getUserByPhone(@PathVariable(required = false) String phone);
}
