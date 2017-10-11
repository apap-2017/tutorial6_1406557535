package com.example.tutorial5a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tutorial5a.dao.CourseMapper;
import com.example.tutorial5a.model.StudentModel;
import com.example.tutorial5a.model.CourseModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CourseServiceDatabase implements CourseService 
{
	@Autowired
	private CourseMapper courseMapper;
	

	@Override
	public CourseModel selectCourse(String id_course) {
        log.info("select student with npm {}", id_course);
        return courseMapper.selectCourse(id_course);
	}

}
