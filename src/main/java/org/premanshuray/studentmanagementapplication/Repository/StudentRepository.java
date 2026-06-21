package org.premanshuray.studentmanagementapplication.Repository;

import org.premanshuray.studentmanagementapplication.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT DISTINCT s FROM Student s JOIN FETCH s.professors")
    public List<Student>findAllStudents();

    @Query("select s from Student s where lower(s.name) like lower(concat('%', :name, '%'))")
    Optional<Student> findByName(@Param("name") String name);

}