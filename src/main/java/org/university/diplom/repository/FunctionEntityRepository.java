package org.university.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.university.diplom.constants.FunctionType;
import org.university.diplom.model.FunctionEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FunctionEntityRepository extends JpaRepository<FunctionEntity, UUID> {

    Optional<FunctionEntity> findByFunctionAndType(String function, FunctionType functionType);

  //  Boolean existByFunctionAndFunctionType(String function, FunctionType functionType);
}
