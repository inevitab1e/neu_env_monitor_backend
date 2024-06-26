package com.neu.edu.controller;

import com.neu.edu.common.annotation.LogOperation;
import com.neu.edu.common.utils.Result;
import com.neu.edu.common.validator.AssertUtils;
import com.neu.edu.dto.GridCityDTO;
import com.neu.edu.common.constant.Constant;
import com.neu.edu.common.page.PageData;
import com.neu.edu.common.validator.ValidatorUtils;
import com.neu.edu.common.validator.group.AddGroup;
import com.neu.edu.common.validator.group.DefaultGroup;
import com.neu.edu.common.validator.group.UpdateGroup;
import com.neu.edu.dto.GridProvinceDTO;
import com.neu.edu.service.GridCityService;
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
@RequestMapping("nep/gridcity")
@Api(tags = "")
public class GridCityController {
    @Autowired
    private GridCityService gridCityService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("demo:gridcity:page")
    public Result<PageData<GridCityDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<GridCityDTO> page = gridCityService.page(params);

        return new Result<PageData<GridCityDTO>>().ok(page);
    }

    @GetMapping("{cityId}")
    @ApiOperation("信息")
    @RequiresPermissions("demo:gridcity:info")
    public Result<GridCityDTO> getCityById(@PathVariable("cityId") Long cityId) {
        GridCityDTO data = gridCityService.get(cityId);

        return new Result<GridCityDTO>().ok(data);
    }

    @GetMapping("get_city_list_by_province_id/{provinceId}")
    @ApiOperation("根据省id获取市列表")
    public Result<List<GridCityDTO>> getCityListByProvinceId(@PathVariable("provinceId") Integer provinceId) {
        Result<List<GridCityDTO>> result = gridCityService.getCityListByProvinceId(provinceId);
        return result;
    }


//    @PostMapping
//    @ApiOperation("保存")
//    @LogOperation("保存")
//    @RequiresPermissions("demo:gridcity:save")
//    public Result save(@RequestBody GridCityDTO dto) {
//        //效验数据
//        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
//
//        gridCityService.save(dto);
//
//        return new Result();
//    }
//
//    @PutMapping
//    @ApiOperation("修改")
//    @LogOperation("修改")
//    @RequiresPermissions("demo:gridcity:update")
//    public Result update(@RequestBody GridCityDTO dto) {
//        //效验数据
//        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
//
//        gridCityService.update(dto);
//
//        return new Result();
//    }
//
//    @DeleteMapping
//    @ApiOperation("删除")
//    @LogOperation("删除")
//    @RequiresPermissions("demo:gridcity:delete")
//    public Result delete(@RequestBody Long[] ids) {
//        //效验数据
//        AssertUtils.isArrayEmpty(ids, "id");
//
//        gridCityService.delete(ids);
//
//        return new Result();
//    }

}