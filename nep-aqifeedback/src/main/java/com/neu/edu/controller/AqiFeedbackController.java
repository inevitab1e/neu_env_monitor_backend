package com.neu.edu.controller;

import com.neu.edu.common.annotation.LogOperation;
import com.neu.edu.common.constant.Constant;
import com.neu.edu.common.page.PageData;
import com.neu.edu.common.utils.Result;
import com.neu.edu.common.validator.AssertUtils;
import com.neu.edu.common.validator.ValidatorUtils;
import com.neu.edu.common.validator.group.AddGroup;
import com.neu.edu.common.validator.group.DefaultGroup;
import com.neu.edu.common.validator.group.UpdateGroup;
import com.neu.edu.dto.AqiFeedbackDTO;
import com.neu.edu.service.AqiFeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;


/**
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@RestController
@RequestMapping("nep/aqifeedback")
@Api(tags = "")
@CrossOrigin("*")
public class AqiFeedbackController {
    @Autowired
    private AqiFeedbackService aqiFeedbackService;

    @GetMapping("page")
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<PageData<AqiFeedbackDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<AqiFeedbackDTO> page = aqiFeedbackService.page(params);

        if (CollectionUtils.isEmpty(page.getList())) {
            return new Result<PageData<AqiFeedbackDTO>>().error(403, "未查询到反馈记录");
        }
        return new Result<PageData<AqiFeedbackDTO>>().ok(page);
    }


    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("demo:aqifeedback:info")
    public Result<AqiFeedbackDTO> get(@PathVariable("id") Long id) {
        AqiFeedbackDTO data = aqiFeedbackService.get(id);

        return new Result<AqiFeedbackDTO>().ok(data);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("demo:aqifeedback:save")
    public Result save(@RequestBody AqiFeedbackDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        aqiFeedbackService.save(dto);

        return new Result();
    }

    @PutMapping("update")
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("demo:aqifeedback:update")
    public Result update(@RequestBody AqiFeedbackDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        aqiFeedbackService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("demo:aqifeedback:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        aqiFeedbackService.delete(ids);

        return new Result();
    }
}