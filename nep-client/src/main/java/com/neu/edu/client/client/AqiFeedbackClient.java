package com.neu.edu.client.client;

import com.neu.edu.client.dto.AqiFeedbackDTO;
import com.neu.edu.common.page.PageData;
import com.neu.edu.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "aqifeedback-service")
public interface AqiFeedbackClient {
    @GetMapping("nep/aqifeedback/page")
    Result<PageData<AqiFeedbackDTO>> page(@RequestHeader("user-info") @RequestParam Map<String, Object> params);

    @PutMapping("nep/aqifeedback/update")
    Result update(@RequestBody AqiFeedbackDTO dto);

    @PostMapping("nep/aqifeedback/save")
    Result save(@RequestBody AqiFeedbackDTO dto);
}
