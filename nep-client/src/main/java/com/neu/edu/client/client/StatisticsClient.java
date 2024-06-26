package com.neu.edu.client.client;

import com.neu.edu.client.vo.*;
import com.neu.edu.common.page.PageData;
import com.neu.edu.common.utils.Result;
import com.neu.edu.client.dto.StatisticsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@FeignClient(value = "statistics-service")
// 交给spring管理
@Resource
public interface StatisticsClient {
    @GetMapping("nep/statistics/page")
    Result<PageData<StatisticsDTO>> page(@RequestParam Map<String, Object> params);

    @GetMapping("nep/statistics/get_province_aqi_index_exceeded_info")
    Result<List<ProvinceAqiIndexVO>> getProvinceAqiIndexExceededInfo();

    @GetMapping("nep/statistics/get_aqi_count_info")
    Result<List<AqiCountVO>> getAqiCountInfo();

    @GetMapping("nep/statistics/get_aqi_month_count_info")
    Result<List<AqiMonthCountVO>> getAqiMonthCountInfo();

    @GetMapping("nep/statistics/get_coverage_info")
    Result<CoverageVO> getCoverageInfo();

    @GetMapping("nep/statistics/get_summary_data_info")
    Result<SummaryDataVO> getSummaryDataInfo();

    @PostMapping("nep/statistics/save")
    Result save(@RequestBody StatisticsDTO dto);

    @GetMapping("nep/statistics/{id}")
    Result<StatisticsDTO> get(Long id);
}
