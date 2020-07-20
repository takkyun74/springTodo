package com.example.demo.app.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.service.RegisterMemberService;

@Controller
@RequestMapping("/management")
public class ManagementController {
	
	private final RegisterMemberService registerMemberService;
	
	@Autowired
	public ManagementController(RegisterMemberService registerMemberService) {
		this.registerMemberService = registerMemberService;
	}

	@GetMapping
    public String account(
    		Account account,
    		Model model
    		) {
    	
        List<Account> list = registerMemberService.findAllAccount();
        
        model.addAttribute("list", list);
        model.addAttribute("title", "ユーザーの管理が");

        return "management";
    }
	
	@PostMapping("/delete")
    public String delete(
    	@RequestParam("account.id") int id,
    	Model model) {
    	
    	//タスクを一件削除しリダイレクト
		registerMemberService.deleteById(id);
    	
        return "redirect:/management";
    }
	

}
