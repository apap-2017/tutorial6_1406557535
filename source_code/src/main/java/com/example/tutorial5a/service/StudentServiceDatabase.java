package com.example.tutorial5a.service;

import java.util.List;

//import org.hibernate.validator.internal.util.logging.Log.logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tutorial5a.dao.CourseMapper;
import com.example.tutorial5a.dao.StudentMapper;
import com.example.tutorial5a.model.CourseModel;
import com.example.tutorial5a.model.StudentModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceDatabase implements StudentService
{
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public StudentModel selectStudent (String npm)
    {
        log.info ("select student with npm {}", npm);
        return studentMapper.selectStudent (npm);
    }


    @Override
    public List<StudentModel> selectAllStudents ()
    {
    	log.info ("select all students");
        return studentMapper.selectAllStudents ();
    }


    @Override
    public void addStudent (StudentModel student)
    {
        studentMapper.addStudent (student);
    }


    @Override
    public void deleteStudent (String npm)
    {
    	log.info("student "+npm+" deleted");
    	studentMapper.deleteStudent(npm);
    }
    
    public void updateStudent(StudentModel student) {
    	log.info(student.getName() +" updated");
    	studentMapper.updateStudent(student);
    }

}
