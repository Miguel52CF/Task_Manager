package com.tareas.tareas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

  @GetMapping("")
  public String Home() {
    return "index.jsp";
  }

  @GetMapping("Board")
  public String Board(){
    return "Task/board.jsp";
  }
}
