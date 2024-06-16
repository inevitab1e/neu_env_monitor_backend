package com.neu.edu.controller;

import com.neu.edu.common.annotation.LogOperation;
import com.neu.edu.common.utils.Result;
import com.neu.edu.common.validator.AssertUtils;
import com.neu.edu.common.validator.ValidatorUtils;
import com.neu.edu.dto.GridProvinceDTO;
import com.neu.edu.common.constant.Constant;
import com.neu.edu.common.page.PageData;
import com.neu.edu.common.validator.group.AddGroup;
import com.neu.edu.common.validator.group.DefaultGroup;
import com.neu.edu.common.validator.group.UpdateGroup;
import com.neu.edu.service.GridProvinceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@RestController
@RequestMapping("nep/gridprovince")
@Api(tags = "")
public class GridProvinceController {
    @Autowired
    private GridProvinceService gridProvinceService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("demo:gridprovince:page")
    public Result<PageData<GridProvinceDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<GridProvinceDTO> page = gridProvinceService.page(params);

        return new Result<PageData<GridProvinceDTO>>().ok(page);
    }

    @GetMapping("{provinceId}")
    @ApiOperation("信息")
    @RequiresPermissions("demo:gridprovince:info")
    public Result<GridProvinceDTO> getProvinceByid(@PathVariable("provinceId") Long provinceId) {
        GridProvinceDTO data = gridProvinceService.get(provinceId);

        return new Result<GridProvinceDTO>().ok(data);
    }

    @GetMapping("list")
    @ApiOperation("省列表")
    public Result<List<GridProvinceDTO>> getProvinceList() {
        List<GridProvinceDTO> data = gridProvinceService.list(new HashMap<>());
        if (CollectionUtils.isEmpty(data)) {
            return new Result<List<GridProvinceDTO>>().error("省信息列表加载失败");
        }
        return new Result<List<GridProvinceDTO>>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("demo:gridprovince:save")
    public Result save(@RequestBody GridProvinceDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        gridProvinceService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("demo:gridprovince:update")
    public Result update(@RequestBody GridProvinceDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        gridProvinceService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("demo:gridprovince:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        gridProvinceService.delete(ids);

        return new Result();
    }

}