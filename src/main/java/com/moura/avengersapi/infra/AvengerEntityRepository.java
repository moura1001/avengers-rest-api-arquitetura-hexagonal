package com.moura.avengersapi.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvengerEntityRepository extends JpaRepository<AvengerEntity, Long> {
}
