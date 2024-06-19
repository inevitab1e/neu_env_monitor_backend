package com.neu.edu.client;

import com.neu.edu.common.annotation.LogOperation;
import com.neu.edu.common.page.PageData;
import com.neu.edu.common.utils.Result;
import com.neu.edu.common.validator.ValidatorUtils;
import com.neu.edu.common.validator.group.AddGroup;
import com.neu.edu.common.validator.group.DefaultGroup;
import com.neu.edu.dto.AqiFeedbackDTO;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "aqifeedback-service")
public interface AqiFeedbackClient {
    @GetMapping("nep/aqifeedback/page")
    Result<PageData<AqiFeedbackDTO>> page(@RequestParam Map<String, Object> params);

    @PostMapping("nep/aqifeedback/save")
    Result save(@RequestBody AqiFeedbackDTO dto);
}
