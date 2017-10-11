package com.example.tutorial5a.service;

import java.util.List;

import com.example.tutorial5a.model.CourseModel;
import com.example.tutorial5a.model.StudentModel;

public interface StudentService
{
    StudentModel selectStudent (String npm);

    List<StudentModel> selectAllStudents ();

    void addStudent (StudentModel student);

    void deleteStudent (String npm);
    
    void updateStudent(StudentModel student);
}
