package com.neu.edu.service;

import com.neu.edu.common.page.PageData;
import com.neu.edu.common.service.CrudService;
import com.neu.edu.common.utils.Result;
import com.neu.edu.dto.AdminDTO;
import com.neu.edu.dto.AqiFeedbackDetailDTO;
import com.neu.edu.dto.GridMemberDTO;
import com.neu.edu.entity.AdminEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
public interface AdminService extends CrudService<AdminEntity, AdminDTO> {

    List<AdminDTO> selectByAdminCode(String adminCode);

    Result<PageData<AqiFeedbackDetailDTO>> getAqiFeedbackDetailPage(Map<String, Object> params);

    Result<List<GridMemberDTO>> getGridMemberListByLocation(Map<String, Object> params);
}