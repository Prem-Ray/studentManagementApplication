package org.premanshuray.studentmanagementapplication.Controller;

import org.premanshuray.studentmanagementapplication.DTO.AdmissionRecordDTO;
import org.premanshuray.studentmanagementapplication.DTO.StudentDTO;
import org.premanshuray.studentmanagementapplication.Service.AdmissionRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admissionRecord")
public class AdmissionRecordController {

    private final AdmissionRecordService admissionRecordService;

    public AdmissionRecordController(AdmissionRecordService admissionRecordService) {
        this.admissionRecordService = admissionRecordService;
    }

    @GetMapping
    public List<AdmissionRecordDTO>getAllAdmissionRecords(){
        return admissionRecordService.findAllAdmissionRecords();
    }

    @GetMapping(path="/{id}")
    public AdmissionRecordDTO getAdmissionRecordById(@PathVariable Long id){
        return admissionRecordService.findAdmissionRecordById(id);
    }

    @PostMapping
    public AdmissionRecordDTO saveAdmissionRecord(@RequestBody AdmissionRecordDTO admissionRecordDTO){
        return admissionRecordService.saveAdmissionRecord(admissionRecordDTO);
    }

    @PutMapping(path="/{id}")
    public AdmissionRecordDTO updateAdmissionRecord(@PathVariable Long id, @RequestBody AdmissionRecordDTO admissionRecordDTO){
        return admissionRecordService.updateAdmissionRecord(id,admissionRecordDTO);
    }

    @PatchMapping(path="/{id}")
    public AdmissionRecordDTO partialUpdateAdmissionRecord(@PathVariable Long id, @RequestBody AdmissionRecordDTO admissionRecordDTO){
        return admissionRecordService.partialUpdateAdmissionRecord(id,admissionRecordDTO);
    }

    @DeleteMapping(path="/{id}")
    public String deleteAdmissionRecordById(@PathVariable Long id){
        return admissionRecordService.deleteAdmissionRecordById(id);
    }


    @GetMapping(path="search/{id}/student")
    public StudentDTO getStudentOfAdmissionRecord(@PathVariable Long id) {
        return admissionRecordService.findStudentOfAdmissionRecord(id);
    }

}
