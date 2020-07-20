package com.example.demo.app.task;

import java.security.Principal;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Account;
import com.example.demo.service.ContentsService;
import com.example.demo.service.DbUserDetails;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "members")
@Slf4j
@Secured("IS_AUTHENTICATED_FULLY")
public class ContentsController {
  private final ContentsService contentsService;

  public ContentsController(ContentsService contentsService) {
    this.contentsService = contentsService;
  }

  @GetMapping(value = "/")
  public String any(Principal principal) {
    Authentication authentication = (Authentication) principal;
    DbUserDetails  loginUser = (DbUserDetails) authentication.getPrincipal();
    log.info("#doService id:{}, name:{}, roles:{}", loginUser.getAccount().getId(), loginUser.getAccount().getName(),loginUser.getAccount().getRoles());
    contentsService.doService();
    return "members/index";
  }

  @GetMapping(value = "user")
  @Secured("ROLE_USER")
  public String user(@AuthenticationPrincipal DbUserDetails loginUser) {
	  log.info("#user id:{}, name:{}, roles:{}", loginUser.getAccount().getId(), loginUser.getAccount().getName(),loginUser.getAccount().getRoles());
    return "members/user/index";
  }

  @GetMapping(value = "admin")
  @Secured("ROLE_ADMIN")
  public String admin(@AuthenticationPrincipal(expression = "account") Account account) {
	  log.info("#admin id:{}, name:{}, roles:{}", account.getId(), account.getName(), account.getRoles());
    return "members/admin/index";
  }

}
