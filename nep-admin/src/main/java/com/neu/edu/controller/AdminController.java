package com.neu.edu.controller;

import com.neu.edu.client.dto.*;
import com.neu.edu.client.vo.*;
import com.neu.edu.common.utils.JwtUtils;
import com.neu.edu.dto.*;
import com.neu.edu.common.constant.Constant;
import com.neu.edu.common.page.PageData;
import com.neu.edu.common.utils.Result;
import com.neu.edu.service.AdminService;

import feign.Headers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@RestController
@RequestMapping("nep/admin")
@Api(tags = "")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private AdminService adminsService;

    @PostMapping("login")
    @ApiOperation("管理员登录")
    public Result<AdminDTO> login(@RequestParam("adminCode") String adminCode, @RequestParam("password") String password) {
        List<AdminDTO> adminDTOList = adminsService.selectByAdminCode(adminCode);

        if (CollectionUtils.isEmpty(adminDTOList)) {
            return new Result<AdminDTO>().error(403, "The account does not exist");
        }

        for (AdminDTO adminDTO : adminDTOList) {
            if (adminDTO.getPassword().equals(password)) {
                adminDTO.setToken(JwtUtils.createToken(adminDTO.getAdminId()));
                return new Result<AdminDTO>().ok(adminDTO);
            }
        }

        return new Result<AdminDTO>().error(403, "Wrong password");
    }

    @GetMapping("page_aqifeedback_detail")
    @ApiOperation("分页得到AQI反馈详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<PageData<AqiFeedbackDetailDTO>> getAqiFeedbackDetailPage(@RequestHeader("user-info") @RequestParam Map<String, Object> params) {
        Result<PageData<AqiFeedbackDetailDTO>> result = adminsService.getAqiFeedbackDetailPage(params);
        return result;
    }

    @GetMapping("get_grid_province_list")
    @ApiOperation("得到网格省份列表")
    public Result<List<GridProvinceDTO>> getGridProvinceList() {
        Result<List<GridProvinceDTO>> result = adminsService.getGridProvinceList();
        return result;
    }

    @GetMapping("get_city_list_by_province_id/{provinceId}")
    @ApiOperation("根据省id获取市列表")
    public Result<List<GridCityDTO>> getCityListByProvinceId(@PathVariable("provinceId") Integer provinceId) {
        Result<List<GridCityDTO>> result = adminsService.getCityListByProvinceId(provinceId);
        return result;
    }

    @GetMapping("get_gridmember_by_location")
    @ApiOperation("根据位置获取网格员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "provinceId", value = "省id", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "cityId", value = "城市id", paramType = "query", required = true, dataType = "int")
    })
    public Result<List<GridMemberDTO>> getGridMemberListByLocation(@RequestParam Map<String, Object> params) {
        Result<List<GridMemberDTO>> result = adminsService.getGridMemberListByLocation(params);
        return result;
    }

    @PutMapping("assign_grid_member")
    public Result assignGridMember(@RequestBody AqiFeedbackDTO dto) {
        Result result = adminsService.assignGridMember(dto);
        return result;
    }

    @GetMapping("page_confirmed_aqifeedback")
    @ApiOperation("分页得到已确认的AQI反馈")
    public Result<PageData<ConfirmedAqiFeedbackDTO>> getConfirmedAqiFeedbackPage(@RequestParam Map<String, Object> params) {
        Result<PageData<ConfirmedAqiFeedbackDTO>> result = adminsService.getConfirmedAqiFeedbackPage(params);
        return result;
    }

    @GetMapping("get_province_aqi_index_exceeded_info")
    @ApiOperation("获取省份aqi指数超标信息")
    public Result<List<ProvinceAqiIndexVO>> getProvinceAqiIndexExceededInfo() {
        Result<List<ProvinceAqiIndexVO>> result = adminsService.getProvinceAqiIndexExceededInfo();
        return result;
    }

    @GetMapping("get_aqi_count_info")
    @ApiOperation("获取aqi指数统计信息")
    public Result<List<AqiCountVO>> getAqiCountInfo() {
        Result<List<AqiCountVO>> result = adminsService.getAqiCountInfo();
        return result;
    }

    @GetMapping("get_aqi_month_count_info")
    @ApiOperation("获取aqi指数月统计信息")
    public Result<List<AqiMonthCountVO>> getAqiMonthCountInfo() {
        Result<List<AqiMonthCountVO>> result = adminsService.getAqiMonthCountInfo();
        return result;
    }

    @GetMapping("get_coverage_info")
    @ApiOperation("获取覆盖率统计信息")
    Result<CoverageVO> getCoverageInfo() {
        Result<CoverageVO> result = adminsService.getCoverageInfo();
        return result;
    }

    @GetMapping("get_summary_data_info")
    @ApiOperation("获取汇总数据统计信息 总数 优良 污染")
    Result<SummaryDataVO> getSummaryDataInfo() {
        Result<SummaryDataVO> result = adminsService.getSummaryDataInfo();
        return result;
    }

//    @GetMapping("{adminId}")
//    @ApiOperation("信息")
//    @RequiresPermissions("demo:admin:info")
//    public Result<AdminDTO> get(@PathVariable("adminId") Long adminId) {
//        System.out.println("adminId:" + adminId);
//        AdminDTO data = adminsService.get(adminId);
//
//        return new Result<AdminDTO>().ok(data);
//    }
//
//    @PostMapping
//    @ApiOperation("保存")
//    @LogOperation("保存")
//    @RequiresPermissions("demo:admin:save")
//    public Result save(@RequestBody AdminDTO dto) {
//        //效验数据
//        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
//
//        adminsService.save(dto);
//
//        return new Result();
//    }
//
//    @PutMapping
//    @ApiOperation("修改")
//    @LogOperation("修改")
//    @RequiresPermissions("demo:admin:update")
//    public Result update(@RequestBody AdminDTO dto) {
//        //效验数据
//        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
//
//        adminsService.update(dto);
//
//        return new Result();
//    }
//
//    @DeleteMapping
//    @ApiOperation("删除")
//    @LogOperation("删除")
//    @RequiresPermissions("demo:admin:delete")
//    public Result delete(@RequestBody Long[] ids) {
//        //效验数据
//        AssertUtils.isArrayEmpty(ids, "id");
//
//        adminsService.delete(ids);
//
//        return new Result();
//    }
}