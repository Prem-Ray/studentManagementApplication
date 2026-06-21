package org.premanshuray.studentmanagementapplication.Controller;

import org.premanshuray.studentmanagementapplication.DTO.ProfessorDTO;
import org.premanshuray.studentmanagementapplication.DTO.StudentDTO;
import org.premanshuray.studentmanagementapplication.DTO.SubjectDTO;
import org.premanshuray.studentmanagementapplication.Service.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<SubjectDTO> getAllSubjects(){
        return subjectService.findAllSubjects();
    }

    @GetMapping(path="/search/{id}")
    public SubjectDTO getSubjectById(@PathVariable Long id){
        return subjectService.findSubjectById(id);
    }

    @GetMapping(path="/search/{title}")
    public SubjectDTO getSubjectByTitle(@PathVariable String title){
        return subjectService.findSubjectByTitle(title);
    }

    @GetMapping("/search/professor")
    public ProfessorDTO getProfessorOfSubject(@RequestParam String title) {
        return subjectService.findProfessorOfSubject(title);
    }

    @PostMapping
    public SubjectDTO addSubject(@RequestBody SubjectDTO subjectDTO){
        return subjectService.addSubject(subjectDTO);
    }

    @DeleteMapping(path="/{id}")
    public String deleteSubject(@PathVariable Long id){
        return subjectService.deleteSubject(id);
    }

    @PutMapping(path="/{id}")
    public SubjectDTO updateSubject(@RequestBody SubjectDTO subjectDTO, @PathVariable Long id){
        return subjectService.updateSubject(subjectDTO,id);
    }

    @PatchMapping(path="/{id}")
    public SubjectDTO partialUpdateSubject(@RequestBody SubjectDTO subjectDTO, @PathVariable Long id){
        return subjectService.partialUpdateSubject(subjectDTO,id);
    }


    @GetMapping(path="/search/students/{subjectId}")
    public List<StudentDTO> getStudentsOfSubject(@PathVariable Long subjectId){
        return subjectService.findStudentsOfSubject(subjectId);
    }

}
