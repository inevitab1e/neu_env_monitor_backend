package com.neu.edu.service;

import com.neu.edu.common.page.PageData;
import com.neu.edu.common.service.CrudService;
import com.neu.edu.common.utils.Result;
import com.neu.edu.client.dto.AssignmentInfoDTO;
import com.neu.edu.client.dto.GridMemberDTO;
import com.neu.edu.entity.GridMemberEntity;

import java.util.List;
import java.util.Map;

/**
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
public interface GridMemberService extends CrudService<GridMemberEntity, GridMemberDTO> {

    List<GridMemberDTO> selectByGmCode(String gmCode);

    Result<PageData<AssignmentInfoDTO>> getAssignments(Map<String, Object> params);

    void confirm(AssignmentInfoDTO dto);

    List<GridMemberDTO> getGridMemberByLocation(Map<String, Object> params);

    Result<AssignmentInfoDTO> getAssignmentByAfId(Long afId);

    GridMemberDTO selectByTel(String tel);
}