package com.toior.community.controller;

import com.toior.community.dto.AccessTokenDto;
import com.toior.community.dto.GithubUser;
import com.toior.community.mapper.UserMapper;
import com.toior.community.model.User;
import com.toior.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class OAuthController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;


    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callBack(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state,  HttpServletResponse response) {

        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);

        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        if (githubUser != null) {
            //success
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        } else {
            //failed!
            return "redirect:/";

        }
    }

}
