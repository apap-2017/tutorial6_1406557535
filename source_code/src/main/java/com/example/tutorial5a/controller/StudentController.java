package com.example.tutorial5a.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tutorial5a.model.StudentModel;
import com.example.tutorial5a.service.CourseService;
import com.example.tutorial5a.service.StudentService;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;


    @RequestMapping("/")
    public String index ()
    {
        return "index";
    }


    @RequestMapping("/student/add")
    public String add ()
    {
        return "form-add";	
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa)
    {
        StudentModel student = new StudentModel (npm, name, gpa, null);
        studentDAO.addStudent (student);

        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);
        
        if (student != null) {
            model.addAttribute ("student", student);
           
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            model.addAttribute ("course", student);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentDAO.selectAllStudents ();
        model.addAttribute ("students", students);

        return "viewall";
    }


    @RequestMapping("/student/delete/{npm}")
    public String delete (
    		Model model, 
    		@PathVariable(value = "npm") String npm)
    {
        if (studentDAO.selectStudent (npm) != null) {
            model.addAttribute ("student", studentDAO.selectStudent (npm));
            studentDAO.deleteStudent (npm);
            return "delete";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }
    
    @RequestMapping("/student/update/{npm}")
    public String update(
    		Model model, 
    		@PathVariable(value="npm") String npm) {
    	
    	StudentModel students = studentDAO.selectStudent(npm);
    	if(students==null) {
    		return "not-found";
    	}
    	model.addAttribute("students",students);
    	
    	return "form-update";
    }
    
    @RequestMapping(
    		value = "/student/update/submit", 
    		method= RequestMethod.POST)
    public String updateSubmit(StudentModel students) {
    	studentDAO.updateStudent(students);
    	return "success-update";
    }
    
//    @RequestMapping("/course/view/{id_course}")
//    public String viewPathCourse (Model model,
//            @PathVariable(value = "id_course") String id_course)
//    {
////        StudentModel student = studentDAO.selectCourse(id_course);
//    	System.out.println("MASUK VIEW COURSE");
////        CourseModel course = studentDAO.selectCourse(id_course);
//    	CourseModel course = courseDAO.selectCourse(id_course);
//
//        if (course != null) {
//            model.addAttribute ("course", course);
//            model.addAttribute ("student", course);
//            return "view-course";
//        } else {
//            model.addAttribute ("id_course", id_course);
//            return "not-found-course";
//        }
//    }
    
    
//    @RequestMapping(
//    		value = "/student/update/submit", 
//    		method= RequestMethod.POST)
//    public String updateSubmit(
//    		@RequestParam(value="npm",required=false) String npm,
//    		@RequestParam(value="name",required=false) String name,
//    		@RequestParam(value="gpa",required=false) double gpa) {
//    	
//    	StudentModel students = new StudentModel(npm, name, gpa);
//    	studentDAO.updateStudent(students);
//    	return "success-update";
//    
//    }

}
