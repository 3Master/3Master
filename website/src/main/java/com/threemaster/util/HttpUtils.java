package com.threemaster.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.threemaster.entity.User;
import com.threemaster.exception.LoginRequiredException;
import com.threemaster.repository.UserRepository;

@Component
public class HttpUtils {
    
    private static UserRepository userRepository;
    
    @Autowired
    private void setUserRepository(UserRepository userRepository){
        HttpUtils.userRepository = userRepository;
    }
    
    public static User loginRequired(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object currentUser = session.getAttribute("currentUser");
        if(currentUser == null){
            throw new LoginRequiredException();
        }
        return userRepository.findOne(((User)currentUser).getId());
    }

}
