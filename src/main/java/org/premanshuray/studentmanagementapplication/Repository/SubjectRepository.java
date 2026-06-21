package org.premanshuray.studentmanagementapplication.Repository;

import org.premanshuray.studentmanagementapplication.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("select s from Subject s where lower(s.title) like lower(concat('%', :title, '%'))")
    Subject findByTitle(@Param("title") String title);
}