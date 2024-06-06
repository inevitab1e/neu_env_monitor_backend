package com.neu.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.edu.common.service.impl.CrudServiceImpl;
import com.neu.edu.dao.AqiFeedbackDao;
import com.neu.edu.dto.AqiFeedbackDTO;
import com.neu.edu.entity.AqiFeedbackEntity;
import com.neu.edu.service.AqiFeedbackService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
 *
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@Service
public class AqiFeedbackServiceImpl extends CrudServiceImpl<AqiFeedbackDao, AqiFeedbackEntity, AqiFeedbackDTO> implements AqiFeedbackService {

    @Override
    public QueryWrapper<AqiFeedbackEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<AqiFeedbackEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}