package com.neu.edu.controller;

import com.neu.edu.common.annotation.LogOperation;
import com.neu.edu.common.constant.Constant;
import com.neu.edu.common.page.PageData;
import com.neu.edu.common.utils.JwtUtils;
import com.neu.edu.common.utils.Result;
import com.neu.edu.common.utils.UserContext;
import com.neu.edu.common.validator.ValidatorUtils;
import com.neu.edu.common.validator.group.AddGroup;
import com.neu.edu.common.validator.group.DefaultGroup;
import com.neu.edu.client.dto.AqiDTO;
import com.neu.edu.client.dto.AqiFeedbackDTO;
import com.neu.edu.client.dto.SupervisorDTO;
import com.neu.edu.service.SupervisorService;
import com.neu.edu.client.vo.SupervisorAqiFeedbackRecordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;


/**
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@RestController
@RequestMapping("nep/supervisor")
@Api(tags = "")
public class SupervisorController {
    @Autowired
    private SupervisorService supervisorService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("demo:supervisor:page")
    public Result<PageData<SupervisorDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<SupervisorDTO> page = supervisorService.page(params);

        return new Result<PageData<SupervisorDTO>>().ok(page);
    }

    @GetMapping("{telId}")
    @ApiOperation("信息")
    @RequiresPermissions("demo:supervisor:info")
    public Result<SupervisorDTO> get(@PathVariable("telId") String telId) {
        SupervisorDTO data = supervisorService.selectByTelId(telId);

        return new Result<SupervisorDTO>().ok(data);
    }

    @PostMapping("login")
    @ApiOperation("登录")
    public Result<SupervisorDTO> login(@RequestParam("telId") String telId, @RequestParam("password") String password) {
        SupervisorDTO data = supervisorService.selectByTelId(telId);

        if (data == null) {
            return new Result<SupervisorDTO>().error(401, "The account does not exist.");
        }

        if (data.getPassword().equals(password)) {

            data.setToken(JwtUtils.createToken(Long.valueOf(data.getTelId())));
            return new Result<SupervisorDTO>().ok(data);
        }

        return new Result<SupervisorDTO>().error(401, "Wrong password.");
    }

    @GetMapping("aqi_list")
    @ApiOperation("获取AQI列表")
    public Result<List<AqiDTO>> getAqiList() {
        Result<List<AqiDTO>> result = supervisorService.getAqiList();
        if (CollectionUtils.isEmpty(result.getData())) {
            return new Result<List<AqiDTO>>().error(204, "No AQI data.");
        }
        return result;
    }

    @GetMapping("records")
    @ApiOperation("获取反馈记录")
    public Result<PageData<SupervisorAqiFeedbackRecordVO>> pageRecords(@ApiIgnore @RequestParam Map<String, Object> params) {
        params.put("telId", UserContext.getUser().toString());
        Result<PageData<SupervisorAqiFeedbackRecordVO>> pageDataResult = supervisorService.pageRecords(params);
        return pageDataResult;
    }

    @PostMapping("save_aqifeedback")
    public Result saveAqiFeedback(@RequestBody AqiFeedbackDTO dto) {
        supervisorService.saveAqiFeedback(dto);
        return new Result();
    }


    @PostMapping("sign_up")
    @ApiOperation("注册")
    @LogOperation("注册")
    public Result signUp(@RequestBody SupervisorDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        supervisorService.save(dto);

        return new Result();
    }

//    @PutMapping
//    @ApiOperation("修改")
//    @LogOperation("修改")
//    @RequiresPermissions("demo:supervisor:update")
//    public Result update(@RequestBody SupervisorDTO dto) {
//        //效验数据
//        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
//
//        supervisorService.update(dto);
//
//        return new Result();
//    }
//
//    @DeleteMapping
//    @ApiOperation("删除")
//    @LogOperation("删除")
//    @RequiresPermissions("demo:supervisor:delete")
//    public Result delete(@RequestBody Long[] ids) {
//        //效验数据
//        AssertUtils.isArrayEmpty(ids, "id");
//
//        supervisorService.delete(ids);
//
//        return new Result();
//    }

}