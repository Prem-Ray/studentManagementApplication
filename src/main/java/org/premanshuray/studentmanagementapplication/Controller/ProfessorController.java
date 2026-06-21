package org.premanshuray.studentmanagementapplication.Controller;

import org.premanshuray.studentmanagementapplication.DTO.ProfessorDTO;
import org.premanshuray.studentmanagementapplication.DTO.StudentDTO;
import org.premanshuray.studentmanagementapplication.Service.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/professors")
public class ProfessorController {

    ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public List<ProfessorDTO> getAllProfessors(){
        return professorService.getAllProfessors();
    }

    @GetMapping(path="/search/{name}")
    public ProfessorDTO getProfessorByName(@PathVariable String name){
        return professorService.findProfessorByName(name);
    }

    @GetMapping(path="/search/{id}")
    public ProfessorDTO getProfessorById(@PathVariable Long id){
        return professorService.findProfessorById(id);
    }

    @PostMapping
    public ProfessorDTO createNewProfessor(@RequestBody ProfessorDTO professor){
        return professorService.createProfessor(professor);
    }

    @DeleteMapping(path="/{id}")
    public String deleteProfessorById(@PathVariable Long id){
        return professorService.deleteProfessor(id);
    }

    @PutMapping(path="/{id}")
    public ProfessorDTO updatedProfessor(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO){
        return professorService.updateProfessor(id, professorDTO);
    }

    @GetMapping(path="/search/{id}/students")
    public List<StudentDTO>getAllStudentsOfProfessor(@PathVariable Long id){
        return professorService.getAllStudentsOfProfessor(id);
    }

    @PostMapping(path="/search/{id}/students/{studentId}")
    public ProfessorDTO assignStudentToProfessor(@PathVariable Long id, @PathVariable Long studentId){
        return professorService.assignStudentToProfessor(id,studentId);
    }

    @DeleteMapping(path="/search/{id}/students/{studentId}")
    public String deleteStudentFromProfessor(@PathVariable Long id, @PathVariable Long studentId){
        return professorService.deleteStudentFromProfessor(id,studentId);
    }


}
