package com.neu.edu.controller;

import com.neu.edu.common.annotation.LogOperation;
import com.neu.edu.common.constant.Constant;
import com.neu.edu.common.page.PageData;
import com.neu.edu.common.utils.Result;
import com.neu.edu.dto.AdminDTO;
import com.neu.edu.common.validator.AssertUtils;
import com.neu.edu.common.validator.ValidatorUtils;
import com.neu.edu.common.validator.group.AddGroup;
import com.neu.edu.common.validator.group.DefaultGroup;
import com.neu.edu.common.validator.group.UpdateGroup;
import com.neu.edu.dto.AqiFeedbackDetailDTO;
import com.neu.edu.dto.GridMemberDTO;
import com.neu.edu.service.AdminService;
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
@RequestMapping("nep/admin")
@Api(tags = "")
public class AdminController {
    @Autowired
    private AdminService adminsService;

    @GetMapping("{adminId}")
    @ApiOperation("信息")
    @RequiresPermissions("demo:admin:info")
    public Result<AdminDTO> get(@PathVariable("adminId") Long adminId) {
        System.out.println("adminId:" + adminId);
        AdminDTO data = adminsService.get(adminId);

        return new Result<AdminDTO>().ok(data);
    }

    @PostMapping("login")
    public Result<AdminDTO> login(@RequestParam("adminCode") String adminCode, @RequestParam("password") String password) {
        List<AdminDTO> adminDTOList = adminsService.selectByAdminCode(adminCode);

        if (CollectionUtils.isEmpty(adminDTOList)) {
            return new Result<AdminDTO>().error(403, "账号不存在");
        }

        for (AdminDTO adminDTO : adminDTOList) {
            if (adminDTO.getPassword().equals(password)) {
                return new Result<AdminDTO>().ok(adminDTO);
            }
        }

        return new Result<AdminDTO>().error(403, "密码错误");
    }

    @GetMapping("page_aqifeedback_detail")
    public Result<PageData<AqiFeedbackDetailDTO>> getAqiFeedbackDetailPage(@RequestParam Map<String, Object> params) {
        Result<PageData<AqiFeedbackDetailDTO>> result = adminsService.getAqiFeedbackDetailPage(params);
        return result;
    }

    @GetMapping("get_gridmember_by_location")
    public Result<List<GridMemberDTO>> getGridMemberListByLocation(@RequestParam Map<String, Object> params) {
        Result<List<GridMemberDTO>> result = adminsService.getGridMemberListByLocation(params);
        return result;
    }

//    @PostMapping
//    public Result assign(@RequestBody AssignDTO dto){
//
//    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("demo:admin:save")
    public Result save(@RequestBody AdminDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        adminsService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("demo:admin:update")
    public Result update(@RequestBody AdminDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        adminsService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("demo:admin:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        adminsService.delete(ids);

        return new Result();
    }
}