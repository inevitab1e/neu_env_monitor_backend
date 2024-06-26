package com.neu.edu.client.client.fallback;

import com.neu.edu.client.client.AqiFeedbackClient;
import com.neu.edu.client.dto.AqiFeedbackDTO;
import com.neu.edu.common.page.PageData;
import com.neu.edu.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.Map;

@Slf4j
public class AqiClientFallbackFactory implements FallbackFactory<AqiFeedbackClient> {
    @Override
    public AqiFeedbackClient create(Throwable cause) {
        return new AqiFeedbackClient() {
            @Override
            public Result<PageData<AqiFeedbackDTO>> page(Map<String, Object> params) {
                return new Result<PageData<AqiFeedbackDTO>>().error(429, "Flow Limiting");
            }

            @Override
            public Result<AqiFeedbackDTO> get(Long afId) {
                return new Result<AqiFeedbackDTO>().error(429, "Flow Limiting");
            }

            @Override
            public Result update(AqiFeedbackDTO dto) {
                return new Result().error(429, "Flow Limiting");
            }

            @Override
            public Result save(AqiFeedbackDTO dto) {
                return new Result().error(429, "Flow Limiting");
            }
        };
    }
}
