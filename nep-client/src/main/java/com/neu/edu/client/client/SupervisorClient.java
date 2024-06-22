package com.neu.edu.client.client;

import com.neu.edu.common.utils.Result;
import com.neu.edu.client.dto.SupervisorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "supervisor-service")
public interface SupervisorClient {
    @GetMapping("/nep/supervisor/{telId}")
    Result<SupervisorDTO> get(@PathVariable("telId") String telId);

}
