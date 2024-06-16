package com.neu.edu.client;

import com.neu.edu.common.utils.Result;
import com.neu.edu.dto.GridMemberDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "grid-service")
public interface GridClient {

    @GetMapping("nep/grid/get_gridmember_by_location")
    Result<List<GridMemberDTO>> getGridMemberByLocation(@RequestParam Map<String, Object> params);
}
