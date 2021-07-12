package com.bootcampProject.BootcampProject.common;

import com.bootcampProject.BootcampProject.domain.BaseDomain;
import com.bootcampProject.BootcampProject.dto.BaseDTO;

public interface DTOTransform<T extends BaseDomain, T1 extends BaseDTO> {

    T1 toDTO(T domainBase);
    T fromDTO(T1 baseDTO);
}
