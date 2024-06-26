package com.neu.edu.client.client.fallback;

import com.neu.edu.client.client.StatisticsClient;
import com.neu.edu.client.dto.StatisticsDTO;
import com.neu.edu.client.vo.*;
import com.neu.edu.common.page.PageData;
import com.neu.edu.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.List;
import java.util.Map;

@Slf4j
public class StatisticsClientFallbackFactory implements FallbackFactory<StatisticsClient> {
    @Override
    public StatisticsClient create(Throwable cause) {
        return new StatisticsClient() {
            @Override
            public Result<PageData<StatisticsDTO>> page(Map<String, Object> params) {
                return new Result<PageData<StatisticsDTO>>().error(429, "Flow Limiting");
            }

            @Override
            public Result<List<ProvinceAqiIndexVO>> getProvinceAqiIndexExceededInfo() {
                return new Result<List<ProvinceAqiIndexVO>>().error(429, "Flow Limiting");
            }

            @Override
            public Result<List<AqiCountVO>> getAqiCountInfo() {
                return new Result<List<AqiCountVO>>().error(429, "Flow Limiting");
            }

            @Override
            public Result<List<AqiMonthCountVO>> getAqiMonthCountInfo() {
                return new Result<List<AqiMonthCountVO>>().error(429, "Flow Limiting");
            }

            @Override
            public Result<CoverageVO> getCoverageInfo() {
                return new Result<CoverageVO>().error(429, "Flow Limiting");
            }

            @Override
            public Result<SummaryDataVO> getSummaryDataInfo() {
                return new Result<SummaryDataVO>().error(429, "Flow Limiting");
            }

            @Override
            public Result save(StatisticsDTO dto) {
                return new Result().error(429, "Flow Limiting");
            }

            @Override
            public Result<StatisticsDTO> get(Long id) {
                return new Result<StatisticsDTO>().error(429, "Flow Limiting");
            }
        };
    }
}
