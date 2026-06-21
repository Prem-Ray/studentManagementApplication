package org.premanshuray.studentmanagementapplication.Repository;

import org.premanshuray.studentmanagementapplication.Entity.AdmissionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionRecordRepository extends JpaRepository<AdmissionRecord, Long> {
}