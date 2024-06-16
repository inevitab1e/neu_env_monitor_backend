package com.neu.edu.controller;

import com.neu.edu.common.utils.Result;
import com.neu.edu.dto.AqiDTO;
import com.neu.edu.service.AqiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@RestController
@RequestMapping("nep/aqi")
@Api(tags = "")
public class AqiController {
    @Autowired
    private AqiService aqiService;

    @GetMapping
    @ApiOperation("获取AQI列表")
    public Result<List<AqiDTO>> getAqiList() {
        List<AqiDTO> data = aqiService.getAqiList();
        if (CollectionUtils.isEmpty(data)) {
            return new Result<List<AqiDTO>>().error("未查询到数据");
        }
        return new Result<List<AqiDTO>>().ok(data);
    }

//    @GetMapping("{aqiId}")
//    @ApiOperation("获取AQI信息")
//    public Result<AqiDTO> get(@PathVariable("aqiId") Long aqiId) {
//        AqiDTO data = aqiService.get(aqiId);
//
//        return new Result<AqiDTO>().ok(data);
//    }

    @GetMapping("{aqiId}")
    @ApiOperation("获取AQI信息")
    public Result<AqiDTO> get(@PathVariable("aqiId") Long aqiId){
        AqiDTO data = aqiService.get(aqiId);

        return new Result<AqiDTO>().ok(data);
    }

}