package com.example.demo.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ContentsServiceImpl implements ContentsService {
  @Override
  public void doService() {
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    DbUserDetails loginUser = (DbUserDetails) authentication.getPrincipal();
    log.info("#doService id:{}, name:{}", loginUser.getAccount().getId(), loginUser.getAccount().getName(), loginUser.getAccount().getRoles());
  }
}
