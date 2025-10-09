package io.fiap.erp.repository;

import io.fiap.erp.model.AuditoriaLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<AuditoriaLog, Long> {


}
