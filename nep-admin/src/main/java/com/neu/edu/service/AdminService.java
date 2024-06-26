package com.neu.edu.service;

import com.neu.edu.client.dto.*;
import com.neu.edu.client.vo.*;
import com.neu.edu.common.page.PageData;
import com.neu.edu.common.service.CrudService;
import com.neu.edu.common.utils.Result;
import com.neu.edu.dto.*;
import com.neu.edu.entity.AdminEntity;


import java.util.List;
import java.util.Map;

/**
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
public interface AdminService extends CrudService<AdminEntity, AdminDTO> {

    List<AdminDTO> selectByAdminCode(String adminCode);

    Result<PageData<AqiFeedbackDetailDTO>> getAqiFeedbackDetailPage(Map<String, Object> params);

    Result<List<GridMemberDTO>> getGridMemberListByLocation(Map<String, Object> params);

    Result<PageData<ConfirmedAqiFeedbackDTO>> getConfirmedAqiFeedbackPage(Map<String, Object> params);

    Result<List<ProvinceAqiIndexVO>> getProvinceAqiIndexExceededInfo();

    Result<List<AqiCountVO>> getAqiCountInfo();

    Result<List<AqiMonthCountVO>> getAqiMonthCountInfo();

    Result<CoverageVO> getCoverageInfo();

    Result<SummaryDataVO> getSummaryDataInfo();

    Result<List<GridProvinceDTO>> getGridProvinceList();

    Result<List<GridCityDTO>> getCityListByProvinceId(Integer provinceId);

    Result assignGridMember(AqiFeedbackDTO dto);

    Result<AqiFeedbackDetailDTO> getAqiFeedbackDetailByAfId(Long afId);

    Result<ConfirmedAqiFeedbackDTO> getConfirmedAqiFeedbackById(Long id);
}