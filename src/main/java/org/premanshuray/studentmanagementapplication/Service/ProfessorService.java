package org.premanshuray.studentmanagementapplication.Service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.premanshuray.studentmanagementapplication.DTO.ProfessorDTO;
import org.premanshuray.studentmanagementapplication.DTO.StudentDTO;
import org.premanshuray.studentmanagementapplication.Entity.Professor;
import org.premanshuray.studentmanagementapplication.Entity.Student;
import org.premanshuray.studentmanagementapplication.Repository.ProfessorRepository;
import org.premanshuray.studentmanagementapplication.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;

    public List<ProfessorDTO> getAllProfessors() {
        return professorRepository.findAll()
                .stream()
                .map(professor -> modelMapper.map(professor, ProfessorDTO.class))
                .toList();
    }


    public ProfessorDTO findProfessorByName(String name) {
       Professor professor = professorRepository.findByName(name);
       return modelMapper.map(professor,ProfessorDTO.class);
    }


    public ProfessorDTO findProfessorById(Long id) {
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor not found with id: " + id));
        return modelMapper.map(professor,ProfessorDTO.class);
    }

    public ProfessorDTO createProfessor(ProfessorDTO professor) {
        Professor professorEntity = modelMapper.map(professor, Professor.class);
        Professor savedProfessor = professorRepository.save(professorEntity);
        return modelMapper.map(savedProfessor, ProfessorDTO.class);

    }

    public String deleteProfessor(Long id) {
        if(!isIdExist(id))
            throw new RuntimeException("Professor not found with id: " + id);
        professorRepository.deleteById(id);
        return "Professor deleted successfully";
    }

    public ProfessorDTO updateProfessor(Long id, ProfessorDTO professorDTO) {
        Professor professorEntity = professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor not found with id: " + id));
        modelMapper.map(professorDTO,professorEntity);
        Professor updatedProfessor = professorRepository.save(professorEntity);
        return modelMapper.map(updatedProfessor,ProfessorDTO.class);
    }

    public Boolean isIdExist(Long id){
        return  professorRepository.existsById(id);
    }

    public List<StudentDTO> getAllStudentsOfProfessor(Long id) {

        Professor professor = professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor not found with id: " + id));

        return professor.getStudents()
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .toList();
    }

    public ProfessorDTO assignStudentToProfessor(Long id, Long studentId) {
        Professor professorEntity = professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor not found with id:"+id));
        Student studentEntity = studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("Student not found with id: " + studentId));
        professorEntity.getStudents().add(studentEntity);
        Professor updatedProfessor = professorRepository.save(professorEntity);
        return modelMapper.map(updatedProfessor,ProfessorDTO.class);

    }

    public String deleteStudentFromProfessor(Long id, Long studentId) {
        Professor professorEntity = professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor not found with id:"+id));
        Student studentEntity = studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("Student not found with id: " + studentId));
        professorEntity.getStudents().remove(studentEntity);
        professorRepository.save(professorEntity);
        return "Student removed successfully";
    }
}
