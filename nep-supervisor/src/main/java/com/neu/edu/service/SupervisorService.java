package com.neu.edu.service;

import com.neu.edu.common.page.PageData;
import com.neu.edu.common.service.CrudService;
import com.neu.edu.common.utils.Result;
import com.neu.edu.client.dto.AqiDTO;
import com.neu.edu.client.dto.AqiFeedbackDTO;
import com.neu.edu.client.dto.SupervisorDTO;
import com.neu.edu.entity.SupervisorEntity;
import com.neu.edu.client.vo.SupervisorAqiFeedbackRecordVO;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
public interface SupervisorService extends CrudService<SupervisorEntity, SupervisorDTO> {

    SupervisorDTO selectByTelId(String telId);

    Result<List<AqiDTO>> getAqiList();

    Result<PageData<SupervisorAqiFeedbackRecordVO>> pageRecords(Map<String,Object> params);

    void saveAqiFeedback(AqiFeedbackDTO dto);
}