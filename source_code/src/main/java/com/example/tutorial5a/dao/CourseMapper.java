package com.example.tutorial5a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Many;

import com.example.tutorial5a.model.CourseModel;
import com.example.tutorial5a.model.StudentModel;

@Mapper
public interface CourseMapper { 
	//List a course based on id_course
    @Select("select id_course, name, "
    		+ "credits from course where id_course = #{id_course}")
    @Results(value= {
    		@Result(property="id_course", column="id_course"),
    		@Result(property="name", column="name"),
    		@Result(property="credits", column="credits"),
    		@Result(property="students", column="id_course", 
    			javaType=List.class, many=@Many(select="selectStudents"))
    })
    CourseModel selectCourse(@Param("id_course") String id_course);
    
   
    //list of all students on a course with id_course tertentu
    @Select("select student.npm, name, gpa "+
    		"from studentcourse join student " +
    		"on studentcourse.npm = student.npm " +
    		"where studentcourse.id_course = #{id_course}")
    List<StudentModel> selectStudents (@Param("id_course") String id_course);
}
