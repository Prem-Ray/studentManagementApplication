package org.premanshuray.studentmanagementapplication.DTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionRecordDTO {
    private Long id;

    private Integer fees;

    private Long studentId;
}
