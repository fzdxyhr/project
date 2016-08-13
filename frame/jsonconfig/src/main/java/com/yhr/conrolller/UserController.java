package com.yhr.conrolller;

import com.yhr.UserVo;
import com.yhr.po.User;
import com.yhr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by YHR on 2016/8/13.
 */

@RestController
@RequestMapping("/v1")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<User> list(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public User create(@RequestBody UserVo userVo){
        User user = new User();
        user.setUserName(userVo.getUserName());
        user.setPassword(userVo.getPassword());
        user.setId(userVo.getId());
        user = userRepository.save(user);
        return user;
    }

}
