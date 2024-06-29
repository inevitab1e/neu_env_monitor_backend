package com.neu.edu.controller;

import cn.hutool.core.util.StrUtil;
import com.neu.edu.common.annotation.LogOperation;
import com.neu.edu.common.constant.Constant;
import com.neu.edu.common.page.PageData;
import com.neu.edu.common.redis.RedisUtils;
import com.neu.edu.common.utils.*;
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
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SupervisorController {
    @Autowired
    private SupervisorService supervisorService;

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("send_msg")
    @ApiOperation("发送验证码")
    public Result sendMsg(@RequestParam Map<String, Object> params) {
        String telId = (String) params.get("telId");
        Integer serviceCode = Integer.valueOf(params.get("serviceCode").toString());
        if (StrUtil.isNotEmpty(telId)) {
            // 生成随机6位验证码
            String code = ValidateCodeUtils.generateValidateCode(6).toString();
            log.info("code={}", code);

            // 调用阿里云提供的短信服务API完成发送短信
            if (serviceCode == SMSUtils.VALIDATION_SMS_SERVICE_CODE) {
                // 发送登录验证码
                SMSUtils.sendMessage("NEP", "SMS_468695259", telId, code);
                // 将生成的验证码缓存到Redis种，设置有效期5分钟
                redisUtils.set("Supervisor_Validation_" + telId, code, RedisUtils.MINUTE_FIVE_EXPIRE);
            } else if (serviceCode == SMSUtils.REGISTER_SMS_SERVICE_CODE) {
                // 发送注册验证码
                SMSUtils.sendMessage("NEP", "SMS_468765170", telId, code);
                // 将生成的验证码缓存到Redis种，设置有效期5分钟
                redisUtils.set("Supervisor_Register_" + telId, code, RedisUtils.MINUTE_FIVE_EXPIRE);
            } else {
                return new Result().error("The requested service code does not exist");
            }
            // 发送成功
            Result result = new Result();
            result.setMsg("The verification code is sent successfully");
            return result;
        }
        // 发送失败
        return new Result().error("The verification code failed to be sent");
    }

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
    public Result<SupervisorDTO> get(@PathVariable("telId") String telId) {
        SupervisorDTO data = supervisorService.selectByTelId(telId);
        if (data == null) {
            return new Result<SupervisorDTO>().error(204, "The account does not exist.");
        }
        return new Result<SupervisorDTO>().ok(data);
    }

    @PostMapping("login_by_pwd")
    @ApiOperation("账号密码登录")
    public Result<SupervisorDTO> loginByPwd(@RequestParam("telId") String telId, @RequestParam("password") String password) {
        SupervisorDTO supervisorDTO = supervisorService.selectByTelId(telId);

        if (supervisorDTO == null) {
            return new Result<SupervisorDTO>().error(204, "The account does not exist. Please register first.");
        }

        if (supervisorDTO.getPassword().equals(password)) {
            supervisorDTO.setToken(JwtUtils.createToken(Long.valueOf(supervisorDTO.getTelId())));
            redisUtils.set(supervisorDTO.getToken(), supervisorDTO.getToken());
            return new Result<SupervisorDTO>().ok(supervisorDTO);
        }

        return new Result<SupervisorDTO>().error(401, "Wrong password.");
    }

    @PostMapping("login_by_sms")
    @ApiOperation("手机号验证码登录")
    public Result<SupervisorDTO> loginBySms(@RequestParam Map<String, Object> params) {
        // 手机号
        String telId = (String) params.get("telId");
        // 验证码
        Integer code = Integer.valueOf(params.get("code").toString());
        // redis中的验证码
        Integer redisCode = Integer.valueOf(redisUtils.get("Supervisor_Validation_" + telId).toString());

        // 验证请求中的验证码是否与redis中的相同
        if (code == null || !code.equals(redisCode)) {
            // 验证失败
            return new Result<SupervisorDTO>().error(401, "Wrong verification code.");
        }

        // 验证成功
        // 查询对应用户信息
        SupervisorDTO supervisorDTO = supervisorService.selectByTelId(telId);
        // 没有该用户
        if (supervisorDTO == null) {
            return new Result<SupervisorDTO>().error(204, "The account does not exist. Please register first.");
        }

        // 查询到该用户 颁发token
        supervisorDTO.setToken(JwtUtils.createToken(Long.valueOf(supervisorDTO.getTelId())));
        redisUtils.set(supervisorDTO.getToken(), supervisorDTO.getToken());

        // 删除redis中的验证码
        redisUtils.delete("Supervisor_Validation_" + telId);

        return new Result<SupervisorDTO>().ok(supervisorDTO);

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
        params.put("telId", UserContext.getUserId().toString());
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
    public Result signUp(@RequestBody Map<String, Object> params) {
        // 获取前端数据
        Integer code = Integer.valueOf(params.get("code").toString());
        String telId = (String) params.get("telId");
        // 获取redis中的验证码
        Integer redisCode = (Integer) redisUtils.get("GridMember_Register_" + telId);
        // 前端的验证码是否与redis中一致
        if (code == null || code.equals(redisCode)) {
            return new Result().error(401, "Wrong verification code.");
        }

        // 转换数据类型
        SupervisorDTO dto = ConvertUtils.sourceToTarget(params, SupervisorDTO.class);

        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        supervisorService.save(dto);
        // 删除redis中的验证码
        redisUtils.delete("GridMember_Register_" + telId);

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