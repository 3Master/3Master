package com.threemaster.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.threemaster.entity.Teacher;
import com.threemaster.entity.User;
import com.threemaster.repository.TeacherRepository;
import com.threemaster.repository.UserRepository;
import com.threemaster.util.HttpUtils;

@Controller
public class TeacherController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private TeacherRepository teacherRepository;
    
    @RequestMapping(value="/teachers/{teacherId}", method=RequestMethod.POST)
    @ResponseBody
    public void addTeacher( @PathVariable Integer teacherId, HttpServletRequest request){
        User current = HttpUtils.loginRequired(request);
        User teacher = userRepository.findOne(teacherId);
        Teacher teacherRelation = teacherRepository.findByTeacherAndStudent(teacher, current);
        if(teacherRelation != null){
            return;
        }
        teacherRelation = new Teacher();
        teacherRelation.setActive(false);
        teacherRelation.setStudent(current);
        teacherRelation.setTeacher(teacher);
        teacherRepository.save(teacherRelation);
    }
    
    @RequestMapping(value="/teachers/{teacherId}", method=RequestMethod.DELETE)
    public String deleteTeacher( @PathVariable Integer teacherId, HttpServletRequest request){
        User current = HttpUtils.loginRequired(request);
        User teacher = userRepository.findOne(teacherId);
        Teacher teacherRelation = teacherRepository.findByTeacherAndStudent(teacher, current);
        teacherRepository.delete(teacherRelation);
        return "redirect:/message";
    }


    
    @RequestMapping(value="/students/{studnetId}", method=RequestMethod.POST)
    public String addStudent( @PathVariable Integer studnetId, HttpServletRequest request){
        User current = HttpUtils.loginRequired(request);
        User studnet = userRepository.findOne(studnetId);
        Teacher teacherRelation = teacherRepository.findByTeacherAndStudent(current, studnet);
        teacherRelation.setActive(true);
        teacherRepository.save(teacherRelation);
        return "redirect:/message";
    }

    
    @RequestMapping(value="/students/{studnetId}", method=RequestMethod.DELETE)
    public String deleteStudent( @PathVariable Integer studnetId, HttpServletRequest request){
        User current = HttpUtils.loginRequired(request);
        User studnet = userRepository.findOne(studnetId);
        Teacher teacherRelation = teacherRepository.findByTeacherAndStudent(current, studnet);
        teacherRepository.delete(teacherRelation);
        return "redirect:/message";
    }

}
