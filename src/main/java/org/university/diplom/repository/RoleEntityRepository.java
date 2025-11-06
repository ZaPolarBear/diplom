package org.university.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.university.diplom.model.RoleEntity;

import javax.validation.constraints.NotNull;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {
    @NotNull
    RoleEntity getByName(String name);
}