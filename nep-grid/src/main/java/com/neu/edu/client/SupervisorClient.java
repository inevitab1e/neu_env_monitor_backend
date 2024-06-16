package com.neu.edu.client;

import com.neu.edu.common.utils.Result;
import com.neu.edu.dto.SupervisorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "supervisor-service")
public interface SupervisorClient {
    @GetMapping("/nep/supervisor/{telId}")
    public Result<SupervisorDTO> get(@PathVariable("telId") String telId);

}
