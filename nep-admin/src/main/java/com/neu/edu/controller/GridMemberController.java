package com.neu.edu.controller;

import com.neu.edu.common.annotation.LogOperation;
import com.neu.edu.common.utils.ExcelUtils;
import com.neu.edu.common.utils.Result;
import com.neu.edu.common.validator.AssertUtils;
import com.neu.edu.common.validator.ValidatorUtils;
import com.neu.edu.dto.GridMemberDTO;
import com.neu.edu.common.constant.Constant;
import com.neu.edu.common.page.PageData;
import com.neu.edu.common.validator.group.AddGroup;
import com.neu.edu.common.validator.group.DefaultGroup;
import com.neu.edu.common.validator.group.UpdateGroup;
import com.neu.edu.excel.GridMemberExcel;
import com.neu.edu.service.GridMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@RestController
@RequestMapping("demo/gridmember")
@Api(tags="")
public class GridMemberController {
    @Autowired
    private GridMemberService gridMemberService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("demo:gridmember:page")
    public Result<PageData<GridMemberDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<GridMemberDTO> page = gridMemberService.page(params);

        return new Result<PageData<GridMemberDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("demo:gridmember:info")
    public Result<GridMemberDTO> get(@PathVariable("id") Long id){
        GridMemberDTO data = gridMemberService.get(id);

        return new Result<GridMemberDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("demo:gridmember:save")
    public Result save(@RequestBody GridMemberDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        gridMemberService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("demo:gridmember:update")
    public Result update(@RequestBody GridMemberDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        gridMemberService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("demo:gridmember:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        gridMemberService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("demo:gridmember:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<GridMemberDTO> list = gridMemberService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "", list, GridMemberExcel.class);
    }

}