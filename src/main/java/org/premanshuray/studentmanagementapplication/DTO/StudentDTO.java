package org.premanshuray.studentmanagementapplication.DTO;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private List<Long> professorIds;
    private List<Long> subjectIds;
    private Long admissionRecordId;
}
