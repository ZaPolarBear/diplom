package org.university.diplom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.university.diplom.dto.RoleEntityDto;
import org.university.diplom.model.RoleEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleEntityMapper {

    RoleEntityDto toDto(RoleEntity roleEntity);

}