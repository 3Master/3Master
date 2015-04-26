package com.threemaster.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Lists;
import com.threemaster.entity.Message;
import com.threemaster.entity.Teacher;
import com.threemaster.entity.User;
import com.threemaster.repository.MessageRepository;
import com.threemaster.repository.TeacherRepository;
import com.threemaster.repository.UserRepository;
import com.threemaster.util.HttpUtils;

@Controller
public class HomeController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private TeacherRepository teacherRepository;
    
    @Autowired
    private MessageRepository messageRepository;
    
    @PostConstruct
    public void init(){
        User user = new User();
        for(Integer integer = 0; integer < 10; integer ++){
            user = new User();
            user.setEmail("test" + integer + "@test.com");
            user.setPassword(integer + "");
            user.setSkill1("ae");
            user.setSkill2("sdag");
            user.setSkill3("agsd");
            user.setUsername("a" + integer);
            userRepository.save(user);
        }
        User user0 = userRepository.findByUsername("a0");
        User user1 = userRepository.findByUsername("a1");
        User user2 = userRepository.findByUsername("a2");
        User user3 = userRepository.findByUsername("a3");
        User user4 = userRepository.findByUsername("a4");
        Teacher teacher = new Teacher();
        teacher.setActive(true);
        teacher.setTeacher(user0);
        teacher.setStudent(user1);
        teacherRepository.save(teacher);
        
        teacher = new Teacher();
        teacher.setActive(false);
        teacher.setTeacher(user0);
        teacher.setStudent(user2);
        teacherRepository.save(teacher);
        
        teacher = new Teacher();
        teacher.setActive(true);
        teacher.setTeacher(user3);
        teacher.setStudent(user0);
        teacherRepository.save(teacher);
        
        teacher = new Teacher();
        teacher.setActive(false);
        teacher.setTeacher(user4);
        teacher.setStudent(user0);
        teacherRepository.save(teacher);
    }


    @RequestMapping(value = "", method=RequestMethod.GET)
    public String home(){
        return "redirect:/register";
    }
    
    @RequestMapping(value = "/register", method=RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/login", method=RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/chat/{toId}", method=RequestMethod.GET)
    public String chat(HttpServletRequest request, Model model, @PathVariable Integer toId){
        User current = HttpUtils.loginRequired(request);
        model.addAttribute("currentUser", current);
        User to = userRepository.findOne(toId);
        model.addAttribute("to", to);
        List<Message> messages =  messageRepository.findByFromIdAndToIdAndRead(to.getId(), current.getId(), false);
        for (Message message : messages) {
            message.setRead(true);
        }
        messageRepository.save(messages);
        model.addAttribute("messages", messages);
        return "chat";
    }

    private List<User> fillTeachers(List<User> users, User current){
        List<User> userLisr = Lists.newArrayList();
        for (User user : users) {
            Teacher teacher = teacherRepository.findByTeacherAndStudent(user, current);
            if(teacher == null || current.getId().equals(user.getId())){
                userLisr.add(user);
            }
            
        }
        return userLisr;
    }

    @RequestMapping(value = "/search", method=RequestMethod.GET)
    public String search(HttpServletRequest request, 
            @PageableDefault(value = 15, sort = { "createdTime" }, direction = Sort.Direction.DESC) Pageable pageable,
            Model model, @RequestParam(value="skill", required=false, defaultValue="") String skill){
        User current = HttpUtils.loginRequired(request);
        List<Message> messages = messageRepository.fingByUser(current.getId());
        model.addAttribute("messageCount", messages.size());
        List<User> users = null;
        if(skill.equals("")){
            users = userRepository.findAll(pageable).getContent();
        }else {
            users = userRepository.findBySkill1OrSkill2OrSkill3(skill, skill, skill, pageable).getContent();
        }
        model.addAttribute("users",fillTeachers(users, current));
        return "search";
    }
    
    private List<User> getTeachers(List<Teacher> teachers, User current){
        List<User> users = Lists.newArrayList();
        for (Teacher teacher : teachers) {
            User user = teacher.getTeacher();
            user.setMessageCount(messageRepository.findByFromIdAndToIdAndRead(user.getId(), current.getId(), false).size());
            users.add(user);
        }
        return users;
    }
    
    private List<User> getStudents(List<Teacher> teachers, User current){
        List<User> users = Lists.newArrayList();
        for (Teacher teacher : teachers) {
            User user = teacher.getStudent();
            user.setMessageCount(messageRepository.findByFromIdAndToIdAndRead(user.getId(), current.getId(), false).size());
            users.add(user);
        }
        return users;
    }

    @RequestMapping(value = "/message", method=RequestMethod.GET)
    public String message(HttpServletRequest request, Model model){
        User user = HttpUtils.loginRequired(request);
        List<User> passedTeachers = getTeachers(teacherRepository.findByStudentAndActive(user, true), user);
        List<User> unpassedTeachers = getTeachers(teacherRepository.findByStudentAndActive(user, false), user);
        List<User> passedStudents = getStudents(teacherRepository.findByTeacherAndActive(user, true), user);
        List<User> unpassedStudents = getStudents(teacherRepository.findByTeacherAndActive(user, false), user);
        model.addAttribute("passedTeachers", passedTeachers);
        model.addAttribute("unpassedTeachers", unpassedTeachers);
        model.addAttribute("passedStudents", passedStudents);
        model.addAttribute("unpassedStudents", unpassedStudents);
        return "message";
    }
}
