package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.studentRepository;
import com.example.demo.studentDto.newStudentDto;
import com.example.demo.studentDto.studentDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.awt.event.MouseWheelEvent;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class studentServiceImp implements studentService {
    private final studentRepository studentRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<studentDto> getStudents(){
        List<Student> l= studentRepository.findAll();
        List<studentDto> l1=l.stream()
                .map(student -> modelMapper.map(student, studentDto.class))
                .toList();

        return l1;
    }

    @Override
    public studentDto getStudent(int id) {
        Student student= studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("student not found"));
        return modelMapper.map(student, studentDto.class);
    }

    @Override
    public studentDto createNewStudent(newStudentDto newStudentDto) {
        Student newStudent=modelMapper.map(newStudentDto, Student.class);
        Student s=studentRepository.save(newStudent);
        return modelMapper.map(s, studentDto.class);
    }

    @Override
    public void deleteStudentById(int id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("doesnt exist");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public studentDto updateStudent(int id, newStudentDto newStudentDto) {
        Student student= studentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("student not found"));
        modelMapper.map(newStudentDto,student);
        student=studentRepository.save(student);
        return modelMapper.map(student,studentDto.class);
    }

    @Override
    public studentDto updatePartialStudent(int id, Map<String, Object> updates) {
        Student student= studentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("student not found"));
        updates.forEach((field,value)->{
            switch (field){
                case "name":student.setName((String)value);
                break;
                case  "email":student.setEmail((String)value);
                break;
                default:
                    throw new IllegalArgumentException("field not supported");
            }
        });
        Student saved=studentRepository.save(student);
        return modelMapper.map(saved,studentDto.class);
    }
}
