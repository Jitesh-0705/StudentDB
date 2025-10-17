package com.example.demo.controller;
import com.example.demo.entity.Student;
import com.example.demo.repository.studentRepository;
import com.example.demo.service.studentService;
import com.example.demo.studentDto.newStudentDto;
import com.example.demo.studentDto.studentDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/students")
public class studentController {
    private final studentService studentService;

    @GetMapping
    public ResponseEntity<List<studentDto>> getStudents(){
        return ResponseEntity.ok(studentService.getStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<studentDto> getStudent(@PathVariable int id){
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @PostMapping
    public ResponseEntity<studentDto> createStudent(@RequestBody @Valid newStudentDto newStudentDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(newStudentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id){
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<studentDto> updateStudent(@PathVariable int id,@RequestBody newStudentDto newStudentDto){

        return ResponseEntity.ok(studentService.updateStudent(id,newStudentDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<studentDto> updatePartialStudent(@PathVariable int id, @RequestBody Map<String,Object> updates){

        return ResponseEntity.ok(studentService.updatePartialStudent(id,updates));
    }
}
