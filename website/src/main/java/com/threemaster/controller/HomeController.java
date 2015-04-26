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
        User user1 = new User();
        user1 = new User();
        user1.setEmail("test1@test.com");
        user1.setUsername("xierui");
        user1.setPassword("1");
        user1.setSkill1("做蛋炒饭");
        user1.setSkill2("睡觉");
        user1.setSkill3("调酒");
        user1.setAvatar("/img/1.jpg");
        user1 = userRepository.save(user1);
        
        User user2 = new User();
        user2 = new User();
        user2.setEmail("test2@test.com");
        user2.setUsername("流寒");
        user2.setPassword("1");
        user2.setSkill1("剑道");
        user2.setSkill2("空手道");
        user2.setSkill3("快速入睡");
        user2.setAvatar("/img/2.jpg");
        user2 = userRepository.save(user2);
        
        User user3 = new User();
        user3.setEmail("test3@test.com");
        user3.setUsername("小白");
        user3.setPassword("1");
        user3.setSkill1("篮球");
        user3.setSkill2("吹竖笛");
        user3.setSkill3("骑自行车");
        user3.setAvatar("/img/3.jpg");
        user3 = userRepository.save(user3);
        
        User user4 = new User();
        user4.setEmail("test4@test.com");
        user4.setUsername("harttle");
        user4.setPassword("1");
        user4.setSkill1("长跑");
        user4.setSkill2("武术");
        user4.setSkill3("叫人起床");
        user4.setAvatar("/img/4.jpg");
        user4 = userRepository.save(user4);
        
        User user5 = new User();
        user5.setEmail("test5@test.com");
        user5.setUsername("杰伦");
        user5.setPassword("1");
        user5.setSkill1("RAP");
        user5.setSkill2("写歌");
        user5.setSkill3("魔术");
        user5.setAvatar("/img/6.jpg");
        user5 = userRepository.save(user5);
        
        
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
