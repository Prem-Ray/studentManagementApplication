package org.premanshuray.studentmanagementapplication.Controller;

import org.premanshuray.studentmanagementapplication.DTO.ProfessorDTO;
import org.premanshuray.studentmanagementapplication.DTO.StudentDTO;
import org.premanshuray.studentmanagementapplication.Service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/students")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public List<StudentDTO> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/{id}")
    public StudentDTO getStudentById(@PathVariable Long id){
        return studentService.findStudentById(id);
    }

    @GetMapping(path = "search/{name}")
    public StudentDTO getStudentByName(@PathVariable String name){
        return studentService.findStudentByName(name);
    }

    @PostMapping
    public StudentDTO createNewStudent(@RequestBody StudentDTO student){
        return studentService.createStudent(student);
    }

    @DeleteMapping(path="/{id}")
    public String deleteStudentById(@PathVariable Long id){
        return studentService.deleteStudent(id);
    }

    @PutMapping(path="/{id}")
    public StudentDTO updatedStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO){
        return studentService.updatedStudent(id, studentDTO);
    }


    @GetMapping(path="/{id}/professors")
    public List<ProfessorDTO> getProfessorsOfStudent(@PathVariable Long id){
        return studentService.getProfessorsOfStudent(id);
    }

    @PatchMapping("/{id}/professors")
    public StudentDTO assignProfessorToStudent(@PathVariable Long id, @RequestBody Long professorId) {
        return studentService.assignProfessorToStudent(id, professorId);
    }

    @DeleteMapping(path="/{id}/professors/{professorId}")
    public String deleteProfessorFromStudent(@PathVariable Long id, @RequestBody Long professorId){
        return studentService.deleteProfessorFromStudent(id,professorId);
    }




}
