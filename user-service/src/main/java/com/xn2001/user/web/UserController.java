package com.xn2001.user.web;

import com.xn2001.user.config.PatternProperties;
import com.xn2001.user.pojo.User;
import com.xn2001.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("/user")
@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PatternProperties properties;

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id,@RequestHeader("Truth") String Truth) {
        System.out.println(Truth);
        return userService.queryById(id);
    }

    @GetMapping("/now")
    public String now() {
        System.out.println(properties);
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(properties.getDateformat(), Locale.CHINA));
    }

    @GetMapping("/prop")
    public PatternProperties prop() {
        return properties;
    }
}
