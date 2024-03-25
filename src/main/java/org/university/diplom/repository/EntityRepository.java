package org.university.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.university.diplom.model.MechanicalWave;

import java.util.UUID;

@Repository
public interface EntityRepository extends JpaRepository<MechanicalWave, UUID> {
}
