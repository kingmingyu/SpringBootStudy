package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ArticleController {
    //private static final Logger log = LoggerFactory.getLogger(ArticleController.class);
    @Autowired //스프링 부트가 미리 생성해 놓은 리파지토리 객체 주입(DI)
    private ArticleRepository articleRepository; // 2. articleRepository 객체 선언 -> 스프링 부트는 객체를 만들지 않아도 알아서 만들어준다.
    @Autowired
    private CommentService commentService;
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create") //URL 요청 접수
    public String createArticle(ArticleForm form){ //폼 데이터를 DTO로 받기
        log.info(form.toString());
        //System.out.println(form.toString()); // DTO에 폼 데이터가 잘 담겼는지 확인
        //1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString());
        //2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article); // 1. article 엔티티를 저장해 saved 객체에 반환
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return "redirect:/articles/" + saved.getId(); //리다이렉트를 작성할 위치
    }

    @GetMapping("/articles/{id}") // 데이터 요청 접수
    public String show(@PathVariable Long id, Model model) {//매게변수로 id 받아오기
        log.info("id = " + id); //id 로그 찍기
        //1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);
        //2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos); //댓글 목록 모델에 등록
        //3. 뷰 페이지 반환하기
        return "articles/show"; //세부 데이터에서 목록으로 돌아가기 링크를 넣을 뷰 파일 확인
    }

    @GetMapping("/articles")
    public String index(Model model) {
        //1. 모든 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        //2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        //3. 뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        //뷰 페이지 설정하기
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){ //매개변수로 DTO받아오기
        log.info(form.toString());
        //1. DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity(); //DTO(form)를 엔티티(articleEntity)로 변환하기
        log.info(articleEntity.toString());//엔티티로 잘 변환됐는지 로그 찍기
        //2. 엔티티를 DB에 저장하기
        //2-1. DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //2-2. 기존 데이터 값 갱신하기
        if(target != null){
            articleRepository.save(articleEntity); //엔티티를 DB에 저장(갱신)
        }
        //3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다.");
        //1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2. 대상 엔티티 삭제하기
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다!");
        }
        //3. 결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }
}
