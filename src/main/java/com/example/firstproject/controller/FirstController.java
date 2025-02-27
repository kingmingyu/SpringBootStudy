package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //1. 컨트롤러(어노테이션) 선언
public class FirstController {
    @GetMapping("/hi") // 3. URL 요청 접수
    public String niceToMeetYou(Model model) { //2. 메서드 작성
        model.addAttribute("username", "hongpark"); //model 객체가 "홍팍"값을 "username"에 연결해 웹 브라우저로 보냄
        return "greetings"; //greeings.mustache 파일 반환
    }
    @GetMapping("/bye") // 4. URL 요청 접수(나가는 화면)
    public String seeYouNext(Model model){
        model.addAttribute("nickname", "홍길동");
        return "goodbye";
    }
}
