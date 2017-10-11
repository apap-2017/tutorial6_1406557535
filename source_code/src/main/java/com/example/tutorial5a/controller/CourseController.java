package com.example.tutorial5a.controller;

import com.example.tutorial5a.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tutorial5a.model.CourseModel;

@Controller
public class CourseController {
	
	@Autowired
	CourseService courseDAO;
	
    @RequestMapping("/course/view/{id_course}")
    public String viewPathCourse (Model model,
            @PathVariable(value = "id_course") String id_course)
    {
    
    	CourseModel course = courseDAO.selectCourse(id_course);

        if (course != null) {
            model.addAttribute ("course", course);
            model.addAttribute ("student", course);
            return "view-course";
        } else {
            model.addAttribute ("id_course", id_course);
            return "not-found-course";
        }
    }
}
