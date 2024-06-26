package com.neu.edu.client.client.fallback;

import com.neu.edu.client.client.GridClient;
import com.neu.edu.client.dto.GridMemberDTO;
import com.neu.edu.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.List;
import java.util.Map;

@Slf4j
public class GridClientFallbackFactory implements FallbackFactory<GridClient> {
    @Override
    public GridClient create(Throwable cause) {
        return new GridClient() {
            @Override
            public Result<List<GridMemberDTO>> getGridMemberByLocation(Map<String, Object> params) {
                return new Result<List<GridMemberDTO>>().error(429, "Flow Limiting");
            }

            @Override
            public Result<GridMemberDTO> get(Long gmId) {
                return new Result<GridMemberDTO>().error(429, "Flow Limiting");
            }
        };
    }
}
