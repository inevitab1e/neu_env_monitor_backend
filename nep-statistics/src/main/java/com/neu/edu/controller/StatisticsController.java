package com.neu.edu.controller;

import com.neu.edu.common.annotation.LogOperation;
import com.neu.edu.common.utils.Result;
import com.neu.edu.common.validator.ValidatorUtils;
import com.neu.edu.dto.StatisticsDTO;
import com.neu.edu.common.constant.Constant;
import com.neu.edu.common.page.PageData;
import com.neu.edu.common.validator.group.AddGroup;
import com.neu.edu.common.validator.group.DefaultGroup;
import com.neu.edu.service.StatisticsService;
import com.neu.edu.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;


/**
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@RestController
@RequestMapping("nep/statistics")
@Api(tags = "")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("get_province_aqi_index_exceeded_info")
    @ApiOperation("获取省份aqi指数超标信息")
    public Result<List<ProvinceAqiIndexVO>> getProvinceAqiIndexExceededInfo() {
        Result<List<ProvinceAqiIndexVO>> data = statisticsService.getProvinceAqiIndexExceededInfo();
        return data;
    }

    @GetMapping("get_aqi_count_info")
    @ApiOperation("获取aqi指数统计信息")
    public Result<List<AqiCountVO>> getAqiCountInfo() {
        Result<List<AqiCountVO>> data = statisticsService.getAqiCountInfo();
        return data;
    }

    @GetMapping("get_aqi_month_count_info")
    @ApiOperation("获取aqi指数月统计信息")
    public Result<List<AqiMonthCountVO>> getAqiMonthCountInfo() {
        Result<List<AqiMonthCountVO>> data = statisticsService.getAqiMonthCountInfo();
        return data;
    }

    @GetMapping("get_coverage_info")
    @ApiOperation("获取覆盖率统计信息")
    public Result<CoverageVO> getCoverageInfo() {
        Result<CoverageVO> data = statisticsService.getCoverageInfo();
        return data;
    }

    @GetMapping("get_summary_data_info")
    @ApiOperation("获取汇总数据统计信息 总数 优良 污染")
    public Result<SummaryDataVO> getSummaryDataInfo() {
        Result<SummaryDataVO> data = statisticsService.getSummaryDataInfo();
        return data;
    }


    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("demo:statistics:page")
    public Result<PageData<StatisticsDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<StatisticsDTO> page = statisticsService.page(params);
        return new Result<PageData<StatisticsDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("demo:statistics:info")
    public Result<StatisticsDTO> get(@PathVariable("id") Long id) {
        StatisticsDTO data = statisticsService.get(id);

        return new Result<StatisticsDTO>().ok(data);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("demo:statistics:save")
    public Result save(@RequestBody StatisticsDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        statisticsService.save(dto);

        return new Result();
    }

//    @PutMapping
//    @ApiOperation("修改")
//    @LogOperation("修改")
//    @RequiresPermissions("demo:statistics:update")
//    public Result update(@RequestBody StatisticsDTO dto) {
//        //效验数据
//        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
//
//        statisticsService.update(dto);
//
//        return new Result();
//    }
//
//    @DeleteMapping
//    @ApiOperation("删除")
//    @LogOperation("删除")
//    @RequiresPermissions("demo:statistics:delete")
//    public Result delete(@RequestBody Long[] ids) {
//        //效验数据
//        AssertUtils.isArrayEmpty(ids, "id");
//
//        statisticsService.delete(ids);
//
//        return new Result();
//    }

}