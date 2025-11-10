package org.university.diplom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.university.diplom.dto.UserRegisterRequestDto;
import org.university.diplom.model.UserEntity;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = RoleEntityMapper.class
)
public interface UserEntityMapper {

    UserEntity toEntity(UserRegisterRequestDto userEntityDto);

}
