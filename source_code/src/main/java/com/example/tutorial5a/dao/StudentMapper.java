package com.example.tutorial5a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Many;

import com.example.tutorial5a.model.CourseModel;
import com.example.tutorial5a.model.StudentModel;

@Mapper
public interface StudentMapper
{
	// list one of student with their course with param npm
    @Select("select npm, name, gpa from student where npm = #{npm}")
    @Results(value = {
    			@Result(property="npm", column="npm"),
    			@Result(property="name", column="name"),
    			@Result(property="gpa", column="gpa"),
    			@Result(property="courses", column="npm", 
    					javaType= List.class, many=@Many(select="selectCourses"))
    })
    StudentModel selectStudent (@Param("npm") String npm);

    // list all student with their list of courses
    @Select("select npm, name, gpa from student")
    	@Results(value= {
    			@Result(property="npm", column="npm"),
    			@Result(property="name", column="name"),
    			@Result(property="gpa", column="gpa"),
    			@Result(property="courses", column="npm",
    					javaType=List.class, many=@Many(select="selectCourses"))
    	})
    List<StudentModel> selectAllStudents ();

    @Insert("INSERT INTO student (npm, name, gpa) VALUES (#{npm}, #{name}, #{gpa})")
    void addStudent (StudentModel student);
    
    @Delete("DELETE FROM student where npm = #{npm}")
    void deleteStudent (String npm);
    
    @Update("UPDATE student SET name = #{name}, "
    		+ "npm = #{npm}, "
    		+ "gpa= #{gpa} WHERE npm = #{npm}")
    void updateStudent (StudentModel student);
    
    //list of all course pada student dengan npm tertentu
    @Select("select course.id_course, name, credits "+
    		"from studentcourse join course " +
    		"on studentcourse.id_course = course.id_course " +
    		"where studentcourse.npm = #{npm}")
    List<CourseModel> selectCourses (@Param("npm") String npm);
       
}
