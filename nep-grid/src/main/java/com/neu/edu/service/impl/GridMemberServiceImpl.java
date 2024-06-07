package com.neu.edu.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.edu.common.service.impl.CrudServiceImpl;
import com.neu.edu.dao.GridMemberDao;
import com.neu.edu.dto.GridMemberDTO;
import com.neu.edu.entity.GridMemberEntity;
import com.neu.edu.service.GridMemberService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
 *
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@Service
public class GridMemberServiceImpl extends CrudServiceImpl<GridMemberDao, GridMemberEntity, GridMemberDTO> implements GridMemberService {

    @Override
    public QueryWrapper<GridMemberEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<GridMemberEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}