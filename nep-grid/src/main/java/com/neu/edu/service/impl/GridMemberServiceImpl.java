package com.neu.edu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.edu.client.client.AqiFeedbackClient;
import com.neu.edu.client.client.LocationClient;
import com.neu.edu.client.client.StatisticsClient;
import com.neu.edu.client.client.SupervisorClient;
import com.neu.edu.client.dto.*;
import com.neu.edu.common.page.PageData;
import com.neu.edu.common.service.impl.CrudServiceImpl;
import com.neu.edu.common.utils.ConvertUtils;
import com.neu.edu.common.utils.Result;
import com.neu.edu.common.utils.UserContext;
import com.neu.edu.dao.GridMemberDao;
import com.neu.edu.entity.GridMemberEntity;
import com.neu.edu.service.GridMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@Service
@RequiredArgsConstructor
public class GridMemberServiceImpl extends CrudServiceImpl<GridMemberDao, GridMemberEntity, GridMemberDTO> implements GridMemberService {

    private final AqiFeedbackClient aqiFeedbackClient;
    private final LocationClient locationClient;
    private final SupervisorClient supervisorClient;
    private final StatisticsClient statisticsClient;

    @Override
    public QueryWrapper<GridMemberEntity> getWrapper(Map<String, Object> params) {
        String gmId = (String) params.get("gmId");
        String gmCode = (String) params.get("gmCode");
        String password = (String) params.get("password");
        Integer provinceId = (Integer) params.get("provinceId");
        Integer cityId = (Integer) params.get("cityId");
        Integer state = (Integer) params.get("state");

        QueryWrapper<GridMemberEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(gmId), "gm_id", gmId)
                .eq(StrUtil.isNotBlank(gmCode), "gm_code", gmCode)
                .eq(StrUtil.isNotBlank(password), "password", password)
                .eq(provinceId != null, "province_id", provinceId)
                .eq(cityId != null, "city_id", cityId)
                .eq(state != null, "state", state);

        return wrapper;
    }


    @Override
    public List<GridMemberDTO> selectByGmCode(String gmCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("gmCode", gmCode);
        QueryWrapper<GridMemberEntity> wrapper = getWrapper(params);
        List<GridMemberEntity> gridMemberEntities = baseDao.selectList(wrapper);
        return ConvertUtils.sourceToTarget(gridMemberEntities, com.neu.edu.client.dto.GridMemberDTO.class);
    }

    @Override
    public Result<PageData<AssignmentInfoDTO>> getAssignments(Map<String, Object> params) {
        params.put("gmId", UserContext.getUserId());
        Result<PageData<AqiFeedbackDTO>> baseResult = aqiFeedbackClient.page(params);
        if (baseResult.getData() == null || CollectionUtils.isEmpty(baseResult.getData().getList())) {
            return new Result<PageData<AssignmentInfoDTO>>().error(204, "No task assigned");
        }

        Integer total = baseResult.getData().getTotal();
        List<AqiFeedbackDTO> list = baseResult.getData().getList();
        List<AssignmentInfoDTO> assignmentInfoDTOS = ConvertUtils.sourceToTarget(list, AssignmentInfoDTO.class);

        for (AssignmentInfoDTO assignmentInfoDTO : assignmentInfoDTOS) {
//            Result<SupervisorDTO> supervisorDTOResult = supervisorClient.get(assignmentInfoDTO.getTelId());
//            String realName = supervisorDTOResult.getData().getRealName();
//            assignmentInfoDTO.setRealName(realName);
//
//            Result<GridCityDTO> gridCityDTOResult = locationClient.getCityByid(assignmentInfoDTO.getCityId());
//            String cityName = gridCityDTOResult.getData().getCityName();
//            assignmentInfoDTO.setCityName(cityName);
//
//            Result<GridProvinceDTO> gridProvinceDTOResult = locationClient.getProvinceById(assignmentInfoDTO.getProvinceId());
//            String provinceName = gridProvinceDTOResult.getData().getProvinceName();
//            assignmentInfoDTO.setProvinceName(provinceName);
            buildAssignmentInfoDTO(assignmentInfoDTO);
        }

        PageData<AssignmentInfoDTO> pageData = new PageData<>(assignmentInfoDTOS, total);

        return new Result<PageData<AssignmentInfoDTO>>().ok(pageData);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirm(AssignmentInfoDTO dto) {
        AqiFeedbackDTO aqiFeedbackDTO = BeanUtil.copyProperties(dto, AqiFeedbackDTO.class);
        aqiFeedbackClient.update(aqiFeedbackDTO);

        StatisticsDTO statisticsDTO = BeanUtil.copyProperties(dto, StatisticsDTO.class);
        statisticsClient.save(statisticsDTO);

    }

    @Override
    public List<GridMemberDTO> getGridMemberByLocation(Map<String, Object> params) {
        String provinceId = (String) params.get("provinceId");
        String cityId = (String) params.get("cityId");

        QueryWrapper<GridMemberEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("province_id", provinceId)
                .eq("city_id", cityId);

        List<GridMemberEntity> gridMemberEntities = baseDao.selectList(wrapper);

        if (CollectionUtils.isEmpty(gridMemberEntities)) {
            return null;
        }

        List<com.neu.edu.client.dto.GridMemberDTO> gridMemberDTOList = ConvertUtils.sourceToTarget(gridMemberEntities, GridMemberDTO.class);

        return gridMemberDTOList;

    }

    @Override
    public Result<AssignmentInfoDTO> getAssignmentByAfId(Long afId) {
        Result<AqiFeedbackDTO> baseResult = aqiFeedbackClient.get(afId);
        AqiFeedbackDTO baseData = baseResult.getData();
        if (baseData == null) {
            return new Result<AssignmentInfoDTO>().error(204, "Failed to load data.");
        }
        AssignmentInfoDTO data = ConvertUtils.sourceToTarget(baseData, AssignmentInfoDTO.class);
        buildAssignmentInfoDTO(data);
        return new Result<AssignmentInfoDTO>().ok(data);
    }

    /**
     * 抽取的搭建AssignmentInfoDTO的方法
     *
     * @descriptions: TODO
     * @author: FEI BO
     * @date: 2024-06-26 9:52
     * @version: 1.0
     */
    private void buildAssignmentInfoDTO(AssignmentInfoDTO assignmentInfoDTO) {
        Result<SupervisorDTO> supervisorDTOResult = supervisorClient.get(assignmentInfoDTO.getTelId());
        String realName = supervisorDTOResult.getData().getRealName();
        assignmentInfoDTO.setRealName(realName);

        Result<GridCityDTO> gridCityDTOResult = locationClient.getCityByid(assignmentInfoDTO.getCityId());
        String cityName = gridCityDTOResult.getData().getCityName();
        assignmentInfoDTO.setCityName(cityName);

        Result<GridProvinceDTO> gridProvinceDTOResult = locationClient.getProvinceById(assignmentInfoDTO.getProvinceId());
        String provinceName = gridProvinceDTOResult.getData().getProvinceName();
        assignmentInfoDTO.setProvinceName(provinceName);
    }
}