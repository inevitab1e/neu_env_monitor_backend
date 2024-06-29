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
import com.neu.edu.client.dto.AssignmentInfoDTO;
import com.neu.edu.client.dto.GridMemberDTO;
import com.neu.edu.service.GridMemberService;
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
import java.util.Objects;


/**
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@RestController
@RequestMapping("nep/grid")
@Api(tags = "")
@Slf4j
public class GridMemberController {
    @Autowired
    private GridMemberService gridMemberService;

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("send_msg")
    @ApiOperation("发送验证码")
    public Result sendMsg(@RequestParam Map<String, Object> params) {
        String tel = (String) params.get("tel");
        Integer serviceCode = Integer.valueOf(params.get("serviceCode").toString());
        if (StrUtil.isNotEmpty(tel)) {
            // 生成随机6位验证码
            String code = ValidateCodeUtils.generateValidateCode(6).toString();
            log.info("code={}", code);

            // 调用阿里云提供的短信服务API完成发送短信
            if (Objects.equals(serviceCode, SMSUtils.VALIDATION_SMS_SERVICE_CODE)) {
                // 发送登录验证码
                SMSUtils.sendMessage("NEP", "SMS_468695259", tel, code);
                // 将生成的验证码缓存到Redis种，设置有效期5分钟
                redisUtils.set("GridMember_Validation_" + tel, code, RedisUtils.MINUTE_FIVE_EXPIRE);
            } else if (Objects.equals(serviceCode, SMSUtils.REGISTER_SMS_SERVICE_CODE)) {
                // 发送注册验证码
                SMSUtils.sendMessage("NEP", "SMS_468765170", tel, code);
                // 将生成的验证码缓存到Redis种，设置有效期5分钟
                redisUtils.set("GridMember_Register_" + tel, code, RedisUtils.MINUTE_FIVE_EXPIRE);
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
    @RequiresPermissions("demo:gridmember:page")
    public Result<PageData<GridMemberDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<GridMemberDTO> page = gridMemberService.page(params);
        return new Result<PageData<GridMemberDTO>>().ok(page);
    }

    @GetMapping("{gmId}")
    @ApiOperation("信息")
    @RequiresPermissions("demo:gridmember:info")
    public Result<GridMemberDTO> get(@PathVariable("gmId") Long gmId) {
        GridMemberDTO data = gridMemberService.get(gmId);

        return new Result<GridMemberDTO>().ok(data);
    }

    @PostMapping("login_by_pwd")
    @ApiOperation("登录")
    public Result<GridMemberDTO> loginByPwd(@RequestParam("gmCode") String gmCode, @RequestParam("password") String password) {
        List<GridMemberDTO> gridMemberDTOList = gridMemberService.selectByGmCode(gmCode);

        if (CollectionUtils.isEmpty(gridMemberDTOList)) {
            return new Result<GridMemberDTO>().error(401, "The account does not exist.");
        }

        for (GridMemberDTO gridMemberDTO : gridMemberDTOList) {
            if (gridMemberDTO.getPassword().equals(password)) {
                gridMemberDTO.setToken(JwtUtils.createToken(gridMemberDTO.getGmId().longValue()));
                redisUtils.set(gridMemberDTO.getToken(), gridMemberDTO.getToken());
                return new Result<GridMemberDTO>().ok(gridMemberDTO);
            }
        }

        return new Result<GridMemberDTO>().error(401, "Wrong password.");
    }

    @PostMapping("login_by_sms")
    @ApiOperation("手机号验证码登录")
    public Result<GridMemberDTO> loginBySms(@RequestParam Map<String, Object> params) {
        // 手机号
        String tel = (String) params.get("tel");
        // 验证码
        Integer code = Integer.valueOf(params.get("code").toString());
        // redis中的验证码
        Integer redisCode = Integer.valueOf(redisUtils.get("GridMember_Validation_" + tel).toString());

        // 验证请求中的验证码是否与redis中的相同
        if (code == null || !code.equals(redisCode)) {
            // 验证失败
            return new Result<GridMemberDTO>().error(401, "Wrong verification code.");
        }

        // 验证成功
        // 查询对应用户信息
        GridMemberDTO gridMemberDTO = gridMemberService.selectByTel(tel);
        // 没有该用户
        if (gridMemberDTO == null) {
            return new Result<GridMemberDTO>().error(204, "The account does not exist. Please register first.");
        }

        // 查询到该用户 颁发token
        gridMemberDTO.setToken(JwtUtils.createToken(Long.valueOf(gridMemberDTO.getTel())));
        redisUtils.set(gridMemberDTO.getToken(), gridMemberDTO.getToken());

        // 删除redis中的验证码
        redisUtils.delete("GridMember_Validation_" + tel);

        return new Result<GridMemberDTO>().ok(gridMemberDTO);
    }

    @GetMapping("{tel}")
    @ApiOperation("查询电话号是否存在")
    public Result<GridMemberDTO> queryTel(@PathVariable("tel") String tel) {
        GridMemberDTO gridMemberDTO = gridMemberService.selectByTel(tel);
        if (gridMemberDTO == null) {
            return new Result<GridMemberDTO>().error(204, "The account does not exist.");
        }
        return new Result<GridMemberDTO>().ok(gridMemberDTO);
    }

    @GetMapping("get_assignments")
    @ApiOperation("根据gmId获取任务")
    public Result<PageData<AssignmentInfoDTO>> getAssignments(@RequestParam Map<String, Object> params) {
        params.put("gmId", UserContext.getUserId());
        Result<PageData<AssignmentInfoDTO>> data = gridMemberService.getAssignments(params);
        return data;
    }

    @GetMapping("get_assignment_by_afId/{afId}")
    @ApiOperation("根据afId获取任务详情")
    public Result<AssignmentInfoDTO> getAssignmentByAfId(@PathVariable("afId") Long afId) {
        Result<AssignmentInfoDTO> result = gridMemberService.getAssignmentByAfId(afId);
        return result;
    }

    @PostMapping("confirm")
    @ApiOperation("确认提交监测数据")
    public Result confirm(@RequestBody AssignmentInfoDTO dto) {
        //效验数据
//        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        dto.setGmId(UserContext.getUserId());
        gridMemberService.confirm(dto);

        return new Result();
    }

    @GetMapping("get_gridmember_by_location")
    @ApiOperation("根据位置获取网格成员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "provinceId", value = "省id", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "cityId", value = "城市id", paramType = "query", required = true, dataType = "int")
    })
    public Result<List<GridMemberDTO>> getGridMemberByLocation(@RequestParam Map<String, Object> params) {
        List<GridMemberDTO> gridMemberDTOList = gridMemberService.getGridMemberByLocation(params);
        if (gridMemberDTOList == null) {
            return new Result<List<GridMemberDTO>>().error(204, "There are no grid members in this area");
        }
        return new Result<List<GridMemberDTO>>().ok(gridMemberDTOList);
    }

    @PutMapping("update")
    @ApiOperation("更新gridmember信息")
    public Result update(@RequestBody GridMemberDTO dto) {
        gridMemberService.update(dto);
        return new Result();
    }


    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("demo:gridmember:save")
    public Result save(@RequestBody GridMemberDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        gridMemberService.save(dto);

        return new Result();
    }

//    @PutMapping
//    @ApiOperation("修改")
//    @LogOperation("修改")
//    @RequiresPermissions("demo:gridmember:update")
//    public Result update(@RequestBody GridMemberDTO dto) {
//        //效验数据
//        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
//
//        gridMemberService.update(dto);
//
//        return new Result();
//    }
//
//    @DeleteMapping
//    @ApiOperation("删除")
//    @LogOperation("删除")
//    @RequiresPermissions("demo:gridmember:delete")
//    public Result delete(@RequestBody Long[] ids) {
//        //效验数据
//        AssertUtils.isArrayEmpty(ids, "id");
//
//        gridMemberService.delete(ids);
//
//        return new Result();
//    }
}