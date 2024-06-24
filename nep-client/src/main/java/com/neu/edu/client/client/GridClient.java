package com.neu.edu.client.client;

import com.neu.edu.client.dto.GridMemberDTO;
import com.neu.edu.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@FeignClient(value = "grid-service")
// 交给spring管理
@Resource
public interface GridClient {

    @GetMapping("nep/grid/get_gridmember_by_location")
    Result<List<GridMemberDTO>> getGridMemberByLocation(@RequestParam Map<String, Object> params);

    @GetMapping("nep/grid/{gmId}")
    Result<GridMemberDTO> get(@PathVariable("gmId") Long gmId);
}
