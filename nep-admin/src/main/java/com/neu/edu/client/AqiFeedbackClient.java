package com.neu.edu.client;

import com.neu.edu.common.page.PageData;
import com.neu.edu.common.utils.Result;
import com.neu.edu.dto.AqiFeedbackDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "aqifeedback-service")
public interface AqiFeedbackClient {
    @GetMapping("nep/aqifeedback/page")
    Result<PageData<AqiFeedbackDTO>> page(@RequestParam Map<String, Object> params);

    @PutMapping("nep/aqifeedback/update")
    Result update(@RequestBody AqiFeedbackDTO dto);
}
