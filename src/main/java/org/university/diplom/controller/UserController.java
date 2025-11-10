package org.university.diplom.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.university.diplom.dto.AuthResponseDto;
import org.university.diplom.dto.UserLoginRequestDto;
import org.university.diplom.dto.UserRegisterRequestDto;
import org.university.diplom.service.UserService;

import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.Charset;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String returnLoginPage(Model model) {
        UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto();
        model.addAttribute("userLoginRequestDto", userLoginRequestDto);
        return "user/login";
    }

    @GetMapping("/register")
    public String returnRegisterPage(Model model) {
        UserRegisterRequestDto userRegisterRequestDto = new UserRegisterRequestDto();
        model.addAttribute("userRegisterRequestDto", userRegisterRequestDto);
        return "user/register";
    }

    @PostMapping("/register")
    public String createUser(@Valid UserRegisterRequestDto userEntityDto) {
        userService.createUser(userEntityDto);
        return "redirect:/user/login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid UserLoginRequestDto userLoginRequestDto, HttpServletResponse httpServletResponse) {
        AuthResponseDto authResponseDto = userService.loginUser(userLoginRequestDto);
        Cookie accessCookie = new Cookie(
                "Authorization",
                URLEncoder.encode("Bearer %s".formatted(authResponseDto.accessToken()), Charset.defaultCharset())
        );
        Cookie refreshToken = new Cookie(
                "Refresh",
                URLEncoder.encode("Bearer %s".formatted(authResponseDto.refreshToken()), Charset.defaultCharset())
        );
        accessCookie.setPath("/");
        httpServletResponse.addCookie(accessCookie);
        refreshToken.setPath("/");
        httpServletResponse.addCookie(refreshToken);
        return "redirect:/waves";
    }
}
