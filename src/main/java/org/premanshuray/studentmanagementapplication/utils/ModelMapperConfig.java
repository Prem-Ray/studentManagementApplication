package org.premanshuray.studentmanagementapplication.utils;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.premanshuray.studentmanagementapplication.DTO.StudentDTO;
import org.premanshuray.studentmanagementapplication.Entity.Professor;
import org.premanshuray.studentmanagementapplication.Entity.Student;
import org.premanshuray.studentmanagementapplication.Entity.Subject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();


        TypeMap<Student, StudentDTO> typeMap = modelMapper.createTypeMap(Student.class, StudentDTO.class);

        Converter<List<Professor>, List<Long>> professorIdsConverter =
                context -> context == null || context.getSource() == null
                        ? Collections.emptyList()
                        : context.getSource().stream().map(Professor::getId).toList();

        Converter<List<Subject>, List<Long>> subjectIdsConverter =
                context -> context == null || context.getSource() == null
                        ? Collections.emptyList()
                        : context.getSource().stream().map(Subject::getId).toList();

        typeMap.addMappings(mapper -> {
            mapper.using(professorIdsConverter)
                    .map(Student::getProfessors, StudentDTO::setProfessorIds);

            mapper.using(subjectIdsConverter)
                    .map(Student::getSubjects, StudentDTO::setSubjectIds);
        });

        return modelMapper;
    }
}
