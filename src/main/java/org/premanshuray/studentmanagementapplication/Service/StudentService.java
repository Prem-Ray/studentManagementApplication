package org.premanshuray.studentmanagementapplication.Service;


import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.premanshuray.studentmanagementapplication.DTO.ProfessorDTO;
import org.premanshuray.studentmanagementapplication.DTO.StudentDTO;
import org.premanshuray.studentmanagementapplication.Entity.Professor;
import org.premanshuray.studentmanagementapplication.Entity.Student;
import org.premanshuray.studentmanagementapplication.Repository.ProfessorRepository;
import org.premanshuray.studentmanagementapplication.Repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final ModelMapper modelMapper;

    private final StudentRepository studentRepository;
    private final ProfessorService professorService;
    private final ProfessorRepository professorRepository;




    public List<StudentDTO> getAllStudents() {
        List<Student> studentEntity = studentRepository.findAllStudents();

        return studentEntity.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());

    }

    public StudentDTO findStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return modelMapper.map(student, StudentDTO.class);
    }

    public StudentDTO findStudentByName(String name){
        Student student = studentRepository.findByName(name)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        return modelMapper.map(student, StudentDTO.class);
    }

    public StudentDTO createStudent(StudentDTO student) {
        Student student1 = modelMapper.map(student, Student.class);
        Student studentEntity = studentRepository.save(student1);
        return modelMapper.map(studentEntity, StudentDTO.class);
    }

    public String deleteStudent(Long id) {
        if(!isIdExist(id))
            throw new RuntimeException("Student not found with id: " + id);
        studentRepository.deleteById(id);
        return "Student with id " + id + " deleted successfully";
    }

    public StudentDTO updatedStudent(Long id,StudentDTO studentDTO) {
        Student studentEntity = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        modelMapper.map(studentDTO,studentEntity);
        Student updatedStudent = studentRepository.save(studentEntity);
        return modelMapper.map(updatedStudent,StudentDTO.class);
    }

    public List<ProfessorDTO> getProfessorsOfStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return student.getProfessors()
                .stream()
                .map(professor -> modelMapper.map(professor, ProfessorDTO.class))
                .toList();
    }

    public StudentDTO assignProfessorToStudent(Long id, Long professorId) {
        Student studentEntity = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        Professor professorEntity = professorRepository.findById(professorId).orElseThrow(()->new RuntimeException("Professor not found with id: " + professorId));
        studentEntity.getProfessors().add(professorEntity);
        Student updatedStudentEntity = studentRepository.save(studentEntity);
        return modelMapper.map(updatedStudentEntity,StudentDTO.class);
    }

    public String deleteProfessorFromStudent(Long id, Long professorId) {
        Student studentEntity = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        Professor professorEntity = professorRepository.findById(professorId).orElseThrow(()->new RuntimeException("Professor not found with id: " + professorId));
        studentEntity.getProfessors().remove(professorEntity);
        studentRepository.save(studentEntity);
        return "Professor removed successfully";
    }

    public Boolean isIdExist(Long id){
        return  studentRepository.existsById(id);
    }

}