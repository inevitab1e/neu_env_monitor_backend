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
import com.neu.edu.client.dto.AssignmentInfoDTO;
import com.neu.edu.client.dto.GridMemberDTO;
import com.neu.edu.service.GridMemberService;
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
@RequestMapping("nep/grid")
@Api(tags = "")
public class GridMemberController {
    @Autowired
    private GridMemberService gridMemberService;

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

    @PostMapping("login")
    @ApiOperation("登录")
    public Result<GridMemberDTO> login(@RequestParam("gmCode") String gmCode, @RequestParam("password") String password) {
        List<GridMemberDTO> gridMemberDTOList = gridMemberService.selectByGmCode(gmCode);

        if (CollectionUtils.isEmpty(gridMemberDTOList)) {
            return new Result<GridMemberDTO>().error(401, "The account does not exist.");
        }

        for (GridMemberDTO adminDTO : gridMemberDTOList) {
            if (adminDTO.getPassword().equals(password)) {
                adminDTO.setToken(JwtUtils.createToken(adminDTO.getGmId().longValue()));
                return new Result<GridMemberDTO>().ok(adminDTO);
            }
        }

        return new Result<GridMemberDTO>().error(401, "Wrong password.");
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