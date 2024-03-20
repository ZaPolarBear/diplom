package org.university.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.university.diplom.constants.FunctionType;
import org.university.diplom.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>
{
    boolean existsByFunction(String function, FunctionType functionType);
    Image findByFunction(String function, FunctionType functionType);
}