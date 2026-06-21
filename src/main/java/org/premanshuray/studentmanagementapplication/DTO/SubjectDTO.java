package org.premanshuray.studentmanagementapplication.DTO;

import lombok.*;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {

    private Long id;

    private String title;

    private Long professorId;

    private String professorName;

    private List<Long> studentIds;

}
