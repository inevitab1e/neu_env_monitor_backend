package com.neu.edu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.edu.client.*;
import com.neu.edu.common.page.PageData;
import com.neu.edu.common.utils.ConvertUtils;
import com.neu.edu.common.utils.Result;
import com.neu.edu.dao.AdminDao;
import com.neu.edu.dto.*;
import com.neu.edu.common.service.impl.CrudServiceImpl;
import com.neu.edu.entity.AdminEntity;
import com.neu.edu.service.AdminService;
import cn.hutool.core.util.StrUtil;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@Service
@RequiredArgsConstructor
public class AdminServiceImpl extends CrudServiceImpl<AdminDao, AdminEntity, AdminDTO> implements AdminService {

    //    private final AqiClient aqiClient;
    private final AqiFeedbackClient aqiFeedbackClient;
    private final LocationClient locationClient;
    private final SupervisorClient supervisorClient;
    private final GridClient gridClient;

    @Override
    public QueryWrapper<AdminEntity> getWrapper(Map<String, Object> params) {
        String adminId = (String) params.get("adminId");
        String adminCode = (String) params.get("adminCode");
        String password = (String) params.get("password");
        String remarks = (String) params.get("remarks");

        QueryWrapper<AdminEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(adminId), "admin_id", adminId)
                .eq(StrUtil.isNotBlank(adminCode), "admin_code", adminCode)
                .eq(StrUtil.isNotBlank(password), "password", password)
                .like(StrUtil.isNotBlank(remarks), "remarks", remarks);

        return wrapper;
    }


    @Override
    public List<AdminDTO> selectByAdminCode(String adminCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("adminCode", adminCode);
        QueryWrapper<AdminEntity> wrapper = getWrapper(params);
        List<AdminEntity> adminEntities = baseDao.selectList(wrapper);
        return ConvertUtils.sourceToTarget(adminEntities, AdminDTO.class);
    }

    @Override
    public Result<PageData<AqiFeedbackDetailDTO>> getAqiFeedbackDetailPage(Map<String, Object> params) {
        Result<PageData<AqiFeedbackDTO>> basePageResult = aqiFeedbackClient.page(params);
        if (CollectionUtils.isEmpty(basePageResult.getData().getList())) {
            return new Result<PageData<AqiFeedbackDetailDTO>>().error("未查询到数据");
        }

        int total = basePageResult.getData().getTotal();
        List<AqiFeedbackDTO> aqiFeedbackDTOS = basePageResult.getData().getList();
        List<AqiFeedbackDetailDTO> aqiFeedbackDetailDTOS = ConvertUtils.sourceToTarget(aqiFeedbackDTOS, AqiFeedbackDetailDTO.class);

        for (AqiFeedbackDetailDTO aqiFeedbackDetailDTO : aqiFeedbackDetailDTOS) {

            String telId = aqiFeedbackDetailDTO.getTelId();
            Result<SupervisorDTO> supervisorDTOResult = supervisorClient.get(telId);
            String realName = supervisorDTOResult.getData().getRealName();
            aqiFeedbackDetailDTO.setRealName(realName);

            Integer cityId = aqiFeedbackDetailDTO.getCityId();
            Result<GridCityDTO> cityDTOResult = locationClient.getCityByid(cityId);
            String cityName = cityDTOResult.getData().getCityName();
            aqiFeedbackDetailDTO.setCityName(cityName);

            Integer provinceId = aqiFeedbackDetailDTO.getProvinceId();
            Result<GridProvinceDTO> provinceDTOResult = locationClient.getProvinceById(provinceId);
            String provinceName = provinceDTOResult.getData().getProvinceName();
            aqiFeedbackDetailDTO.setProvinceName(provinceName);
        }

        PageData<AqiFeedbackDetailDTO> pageData = new PageData<>(aqiFeedbackDetailDTOS, total);
        return new Result<PageData<AqiFeedbackDetailDTO>>().ok(pageData);
    }

    @Override
    public Result<List<GridMemberDTO>> getGridMemberListByLocation(Map<String, Object> params) {
        Result<List<GridMemberDTO>> result = gridClient.getGridMemberByLocation(params);
        return result;
    }

    @Override
    public AdminDTO get(Long id) {
        return super.get(id);
    }
}