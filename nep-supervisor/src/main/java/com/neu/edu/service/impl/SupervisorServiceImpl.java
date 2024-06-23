package com.neu.edu.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.edu.client.client.AqiClient;
import com.neu.edu.client.client.AqiFeedbackClient;
import com.neu.edu.client.client.LocationClient;
import com.neu.edu.client.dto.*;
import com.neu.edu.client.dto.SupervisorDTO;
import com.neu.edu.common.page.PageData;
import com.neu.edu.common.service.impl.CrudServiceImpl;
import com.neu.edu.common.utils.ConvertUtils;
import com.neu.edu.common.utils.Result;
import com.neu.edu.dao.SupervisorDao;
import com.neu.edu.entity.SupervisorEntity;
import com.neu.edu.service.SupervisorService;
import com.neu.edu.client.vo.SupervisorAqiFeedbackRecordVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@Service
@RequiredArgsConstructor
public class SupervisorServiceImpl extends CrudServiceImpl<SupervisorDao, SupervisorEntity, com.neu.edu.client.dto.SupervisorDTO> implements SupervisorService {

    private final AqiClient aqiClient;
    private final AqiFeedbackClient aqiFeedbackClient;
    private final LocationClient locationClient;

    @Override
    public QueryWrapper<SupervisorEntity> getWrapper(Map<String, Object> params) {
        String telId = (String) params.get("telId");
        String password = (String) params.get("password");
        String sex = (String) params.get("sex");

        QueryWrapper<SupervisorEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(telId), "tel_id", telId)
                .eq(StrUtil.isNotBlank(password), "password", password)
                .eq(StrUtil.isNotBlank(sex), "sex", sex);

        return wrapper;
    }

    /**
     * 根据手机号查询用户信息
     *
     * @descriptions: TODO
     * @author: FEI BO
     * @date: 2024-06-12 0:09
     * @version: 1.0
     */
    @Override
    public com.neu.edu.client.dto.SupervisorDTO selectByTelId(String telId) {
        Map<String, Object> params = new HashMap<>();
        params.put("telId", telId);
        QueryWrapper<SupervisorEntity> wrapper = getWrapper(params);
        SupervisorEntity supervisorEntity = baseDao.selectOne(wrapper);
        return ConvertUtils.sourceToTarget(supervisorEntity, SupervisorDTO.class);
    }

    @Override
    public Result<List<AqiDTO>> getAqiList() {
        return aqiClient.getAqiList();
    }

    /**
     * 分页查询反馈记录
     *
     * @descriptions: 分页查询反馈记录
     * @author: FEI BO
     * @date: 2024-06-11 23:26
     * @version: 1.0
     */
    @Override
    public Result<PageData<SupervisorAqiFeedbackRecordVO>> pageRecords(Map<String, Object> params) {
        // 调用aqiFeedbackClient分页查询反馈列表
        Result<PageData<AqiFeedbackDTO>> aqiFeedbackResult = aqiFeedbackClient.page(params);
        if (aqiFeedbackResult.getData() == null || CollectionUtils.isEmpty(aqiFeedbackResult.getData().getList())) {
            return new Result<PageData<SupervisorAqiFeedbackRecordVO>>().error(204, "No records were found");
        }
        // 获取分页数据
        List<AqiFeedbackDTO> aqiFeedbackDTOList = aqiFeedbackResult.getData().getList();

        // 转换为VO
        // 获取总记录数
        Integer total = aqiFeedbackResult.getData().getTotal();
        List<SupervisorAqiFeedbackRecordVO> supervisorAqiFeedbackRecordVOS = new ArrayList<>(total);
        for (AqiFeedbackDTO aqiFeedbackDTO : aqiFeedbackDTOList) {
            // 获取基本数据
            Integer estimatedGrade = aqiFeedbackDTO.getEstimatedGrade();
            String afDate = aqiFeedbackDTO.getAfDate();
            String afTime = aqiFeedbackDTO.getAfTime();
            //  调用client获取地区数据
            Result<GridProvinceDTO> provinceResult = locationClient.getProvinceById(aqiFeedbackDTO.getProvinceId());
            String provinceName = provinceResult.getData().getProvinceName();
            Result<GridCityDTO> cityResult = locationClient.getCityByid(aqiFeedbackDTO.getCityId());
            String cityName = cityResult.getData().getCityName();
            // 调用client获取aqi颜色
            Result<AqiDTO> aqiDTOResult = aqiClient.get(Long.valueOf(aqiFeedbackDTO.getEstimatedGrade()));
            String color = aqiDTOResult.getData().getColor();
            // 创建vo 加入到list
            SupervisorAqiFeedbackRecordVO supervisorAqiFeedbackRecordVO = new SupervisorAqiFeedbackRecordVO(estimatedGrade, color, afDate, afTime, provinceName, cityName);
            supervisorAqiFeedbackRecordVOS.add(supervisorAqiFeedbackRecordVO);
        }
        PageData<SupervisorAqiFeedbackRecordVO> pageData = new PageData<>(supervisorAqiFeedbackRecordVOS, total);

        return new Result<PageData<SupervisorAqiFeedbackRecordVO>>().ok(pageData);
    }

    @Override
    public void saveAqiFeedback(AqiFeedbackDTO dto) {
        aqiFeedbackClient.save(dto);
    }
}