package com.example.demo.service;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.StudentEntity;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Long save(StudentDTO studentDTO) {
        // DTO를 Entity로 변환
//        StudentEntity studentEntity = new StudentEntity();
//        studentEntity.setStudentNumber(studentDTO.getStudentNumber());
//        studentEntity.setStudentName(studentDTO.getStudentName());
//        studentEntity.setStudentMobile(studentDTO.getStudentMobile());
//        studentEntity.setStudentMajor(studentDTO.getStudentMajor());

        // DTO -> Entity 변환을 위한 메서드 호출
        StudentEntity studentEntity = StudentEntity.toSaveEntity(studentDTO);
        Long savedId = studentRepository.save(studentEntity).getId();
        return savedId;
    }

    public List<StudentDTO> findAll() {
        List<StudentEntity> studentEntityList = studentRepository.findAll();
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for(StudentEntity studentEntity : studentEntityList){
            StudentDTO studentDTO = StudentDTO.toStudentDTO(studentEntity);
            studentDTOList.add(studentDTO);
        }
        return studentDTOList;
    }
}
