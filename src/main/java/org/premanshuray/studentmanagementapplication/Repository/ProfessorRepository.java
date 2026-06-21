package org.premanshuray.studentmanagementapplication.Repository;

import org.premanshuray.studentmanagementapplication.Entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("select p from Professor p where lower(p.title) like lower(concat('%', :name, '%'))")
    public Professor findByName(@Param("name") String name);

}