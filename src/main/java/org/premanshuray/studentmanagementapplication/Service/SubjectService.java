package org.premanshuray.studentmanagementapplication.Service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.premanshuray.studentmanagementapplication.DTO.ProfessorDTO;
import org.premanshuray.studentmanagementapplication.DTO.StudentDTO;
import org.premanshuray.studentmanagementapplication.DTO.SubjectDTO;
import org.premanshuray.studentmanagementapplication.Entity.Professor;
import org.premanshuray.studentmanagementapplication.Entity.Subject;
import org.premanshuray.studentmanagementapplication.Repository.ProfessorRepository;
import org.premanshuray.studentmanagementapplication.Repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final ProfessorRepository professorRepository;
    private final ModelMapper modelMapper;

    public List<SubjectDTO> findAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();

        return subjects.stream()
                .map(subject -> modelMapper.map(subject, SubjectDTO.class))
                .toList();
    }

    public SubjectDTO findSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));
        return modelMapper.map(subject,SubjectDTO.class);
    }

    public SubjectDTO findSubjectByTitle(String title) {
        Subject subject = subjectRepository.findByTitle(title);
        return modelMapper.map(subject,SubjectDTO.class);
    }

    public SubjectDTO addSubject(SubjectDTO subjectDTO) {
        Subject subject = modelMapper.map(subjectDTO,Subject.class);
        Subject savedSubject = subjectRepository.save(subject);
        return modelMapper.map(savedSubject,SubjectDTO.class);
    }

    public String deleteSubject(Long id) {
        if(!isIdExist(id)){
            throw new RuntimeException("Subject not found with id: " + id);
        }
        subjectRepository.deleteById(id);
        return "Subject deleted successfully";
    }

    public SubjectDTO updateSubject(SubjectDTO subjectDTO, Long id) {
        Subject subjectEntity = subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));
        modelMapper.map(subjectDTO,subjectEntity);
        Subject updatedSubjectEntity = subjectRepository.save(subjectEntity);
        return modelMapper.map(updatedSubjectEntity,SubjectDTO.class);
    }

    public SubjectDTO partialUpdateSubject(SubjectDTO subjectDTO, Long id) {

        Subject subjectEntity = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));

        if (subjectDTO.getTitle() != null) {
            subjectEntity.setTitle(subjectDTO.getTitle());
        }

        if (subjectDTO.getProfessorId() != null) {
            Professor professor = professorRepository.findById(subjectDTO.getProfessorId())
                    .orElseThrow(() -> new RuntimeException("Professor not found"));

            subjectEntity.setProfessor(professor);
        }

        Subject updated = subjectRepository.save(subjectEntity);

        return modelMapper.map(updated, SubjectDTO.class);
    }

    public ProfessorDTO findProfessorOfSubject(String title) {
        Subject subject = subjectRepository.findByTitle(title);
        Professor professor = subject.getProfessor();
        return modelMapper.map(professor, ProfessorDTO.class);
    }


    public Boolean isIdExist(Long id){
        return subjectRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public List<StudentDTO> findStudentsOfSubject(Long subjectId) {
        Subject subjectEntity = subjectRepository.findById(subjectId).orElseThrow(() -> new RuntimeException("Subject not found with id: " + subjectId));
        return subjectEntity.getStudents()
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .toList();
    }
}
