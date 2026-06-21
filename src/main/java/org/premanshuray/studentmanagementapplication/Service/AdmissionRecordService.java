package org.premanshuray.studentmanagementapplication.Service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.premanshuray.studentmanagementapplication.DTO.AdmissionRecordDTO;
import org.premanshuray.studentmanagementapplication.DTO.StudentDTO;
import org.premanshuray.studentmanagementapplication.Entity.AdmissionRecord;
import org.premanshuray.studentmanagementapplication.Repository.AdmissionRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmissionRecordService {

    private final AdmissionRecordRepository admissionRecordRepository;
    private final ModelMapper modelMapper;
    private final StudentService studentService;

    public List<AdmissionRecordDTO> findAllAdmissionRecords() {
        List<AdmissionRecord> admissionRecords = admissionRecordRepository.findAll();
        return admissionRecords.stream()
                .map(admissionRecord -> modelMapper.map(admissionRecord, AdmissionRecordDTO.class)).toList();
    }

    public AdmissionRecordDTO findAdmissionRecordById(Long id) {
        AdmissionRecord admissionRecordEntity = admissionRecordRepository.findById(id).orElseThrow(()-> new RuntimeException("Admission record not found with id: " + id));
        return modelMapper.map(admissionRecordEntity, AdmissionRecordDTO.class);
    }

    public StudentDTO findStudentOfAdmissionRecord(Long id) {
        AdmissionRecord admissionRecordEntity = admissionRecordRepository.findById(id).orElseThrow(()-> new RuntimeException("Admission Record nor found with id:"+id));
        return studentService.findStudentById(admissionRecordEntity.getStudent().getId());
    }

    public AdmissionRecordDTO saveAdmissionRecord(AdmissionRecordDTO admissionRecordDTO) {
        AdmissionRecord admissionRecordEntity = modelMapper.map(admissionRecordDTO,AdmissionRecord.class);
        AdmissionRecord savedAdmissionRecord = admissionRecordRepository.save(admissionRecordEntity);
        return modelMapper.map(savedAdmissionRecord,AdmissionRecordDTO.class);
    }

    public AdmissionRecordDTO updateAdmissionRecord(Long id, AdmissionRecordDTO admissionRecordDTO) {
        if(!isIdExist(id)) throw new RuntimeException("Admission Record with id: " + id + " not found");
        AdmissionRecord admissionRecordEntity = modelMapper.map(admissionRecordDTO,AdmissionRecord.class);
        AdmissionRecord updatedAdmissionRecord = admissionRecordRepository.save(admissionRecordEntity);
        return modelMapper.map(updatedAdmissionRecord,AdmissionRecordDTO.class);
    }

    public AdmissionRecordDTO partialUpdateAdmissionRecord(Long id, AdmissionRecordDTO admissionRecordDTO) {
        AdmissionRecord admissionRecordEntity = admissionRecordRepository.findById(id).orElseThrow(()-> new RuntimeException("Admission record not found with id: " + id));
        modelMapper.map(admissionRecordDTO,admissionRecordEntity);
        AdmissionRecord updatedAdmissionRecord = admissionRecordRepository.save(admissionRecordEntity);
        return modelMapper.map(updatedAdmissionRecord,AdmissionRecordDTO.class);
    }

    public String deleteAdmissionRecordById(Long id) {
        AdmissionRecord admissionRecordEntity = admissionRecordRepository.findById(id).orElseThrow(()-> new RuntimeException("Admission record not found with id: " + id));
        admissionRecordRepository.deleteById(id);
        return "Admission record deleted successfully with id:" +id;

    }

    public Boolean isIdExist(Long id){
        return  admissionRecordRepository.existsById(id);
    }


}
