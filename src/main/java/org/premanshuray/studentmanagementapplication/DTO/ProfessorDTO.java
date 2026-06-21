package org.premanshuray.studentmanagementapplication.DTO;

import lombok.*;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {
    private Long id;
    private String title;
    private List<Long> subjectIds;
    private List<Long> studentIds;
}
