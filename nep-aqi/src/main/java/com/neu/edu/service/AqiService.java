package com.neu.edu.service;

import com.neu.edu.common.service.CrudService;
import com.neu.edu.dto.AqiDTO;
import com.neu.edu.entity.AqiEntity;

import java.util.List;

/**
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
public interface AqiService extends CrudService<AqiEntity, AqiDTO> {
    List<AqiDTO> getAqiList();
}