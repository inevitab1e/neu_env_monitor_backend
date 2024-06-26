package com.neu.edu.client.client.fallback;

import com.neu.edu.client.client.SupervisorClient;
import com.neu.edu.client.dto.SupervisorDTO;
import com.neu.edu.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

@Slf4j
public class SupervisorClientFallbackFactory implements FallbackFactory<SupervisorClient> {
    @Override
    public SupervisorClient create(Throwable cause) {
        return new SupervisorClient() {
            @Override
            public Result<SupervisorDTO> get(String telId) {
                return new Result<SupervisorDTO>().error(429, "Flow Limiting");
            }
        };
    }
}
