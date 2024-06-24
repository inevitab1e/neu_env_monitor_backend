package com.neu.edu.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.edu.common.service.impl.CrudServiceImpl;
import com.neu.edu.common.utils.ConvertUtils;
import com.neu.edu.dao.AqiFeedbackDao;
import com.neu.edu.dto.AqiFeedbackDTO;
import com.neu.edu.entity.AqiFeedbackEntity;
import com.neu.edu.service.AqiFeedbackService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@Service
public class AqiFeedbackServiceImpl extends CrudServiceImpl<AqiFeedbackDao, AqiFeedbackEntity, AqiFeedbackDTO> implements AqiFeedbackService {

    private final AqiFeedbackDao aqiFeedbackDao;

    public AqiFeedbackServiceImpl(AqiFeedbackDao aqiFeedbackDao) {
        super();
        this.aqiFeedbackDao = aqiFeedbackDao;
    }

    @Override
    public QueryWrapper<AqiFeedbackEntity> getWrapper(Map<String, Object> params) {
        String telId = (String) params.get("telId");
        String gmId = (String) params.get("gmId");
        String provinceId = (String) params.get("provinceId");
        String cityId = (String) params.get("cityId");
        String estimatedGrade = (String) params.get("estimatedGrade");
        String afDate = (String) params.get("afDate");
        String state = (String) params.get("state");

        QueryWrapper<AqiFeedbackEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(telId), "tel_id", telId)
                .eq(StrUtil.isNotBlank(gmId), "gm_id", gmId)
                .eq(StrUtil.isNotBlank(provinceId), "province_id", provinceId)
                .eq(StrUtil.isNotBlank(cityId), "city_id", cityId)
                .eq(StrUtil.isNotBlank(estimatedGrade), "estimated_grade", estimatedGrade)
                .eq(StrUtil.isNotBlank(afDate), "af_date", afDate)
                .eq(StrUtil.isNotBlank(state), "state", state);
        return wrapper;
    }

    public void save(AqiFeedbackDTO dto) {
        AqiFeedbackEntity aqiFeedbackEntity = ConvertUtils.sourceToTarget(dto, AqiFeedbackEntity.class);
        aqiFeedbackDao.save(aqiFeedbackEntity);
    }
//    @Override
//    public PageData<AqiFeedbackDTO> pageByTelId(Map<String, Object> params, String telId) {
//        Map<String, Object> selectParams = new HashMap<>();
//        selectParams.put("telId", telId);
//        QueryWrapper<AqiFeedbackEntity> wrapper = getWrapper(selectParams);
//        IPage<AqiFeedbackEntity> page = baseDao.selectPage(getPage(params, null, false), wrapper);
//        return getPageData(page, currentDtoClass());
//    }
}