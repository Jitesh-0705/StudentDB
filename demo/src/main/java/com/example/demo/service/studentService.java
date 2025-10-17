package com.example.demo.service;

import com.example.demo.studentDto.newStudentDto;
import com.example.demo.studentDto.studentDto;

import java.util.List;
import java.util.Map;

public interface studentService {

    List<studentDto> getStudents();
    studentDto getStudent(int id);

    studentDto createNewStudent(newStudentDto newStudentDto);
    void deleteStudentById(int id);
    studentDto updateStudent(int id,newStudentDto newStudentDto);

    studentDto updatePartialStudent(int id, Map<String, Object> updates);
}
