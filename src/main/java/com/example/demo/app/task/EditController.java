package com.example.demo.app.task;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Account;
import com.example.demo.entity.Task;
import com.example.demo.service.RegisterMemberService;

@Controller
@RequestMapping("/edit")
public class EditController {

	private final RegisterMemberService registerMemberService;

	@Autowired
	public EditController(RegisterMemberService registerMemberService) {
		this.registerMemberService = registerMemberService;
	}

//	@RequestMapping("/edit")
	public String showEdit(Model model) {
		// ログイン画面。
		return "edit";
	}

	@GetMapping("/{id}")
	public String showUpdate(MemberRegistrationForm memberRegistrationForm,
			@PathVariable int id, Model model) {

		Optional<Account> accountOpt = registerMemberService.getAccountId(id);

		// TaskFormへの詰め直し
		Optional<MemberRegistrationForm> memberOpt = accountOpt.map(t -> makeMember(t));

		// TaskFormがnullでなければ中身を取り出し
		// isPresentで中身が入っているか確認
		if (memberOpt.isPresent()) {
			memberRegistrationForm = memberOpt.get();
		}

		
		model.addAttribute("memberForm", memberRegistrationForm);
		List<Account> list = registerMemberService.findAllAccount();
		
		model.addAttribute("list", list);
		model.addAttribute("id", id);
		model.addAttribute("title", "ユーザー更新用フォーム");

		return "edit";
	}
	
	
	@PostMapping("/update")
    public String update(
    	@Valid @ModelAttribute MemberRegistrationForm memberRegistrationForm,
    	BindingResult result,
    	
    	//hiddenの受け取りはRequestParamを使う
    	@RequestParam("id") int id,
    	Model model,
    	//セッションを一度だけ使う
    	RedirectAttributes redirectAttributes) {
        
		
    	//TaskFormのデータをTaskに格納
    	Account account = makeAccount(memberRegistrationForm, id);
    	
    	
        if (!result.hasErrors()) {
        	
        	//更新処理、フラッシュスコープの使用、リダイレクト（個々の編集ページ）
        	registerMemberService.update(account);
        	redirectAttributes.addFlashAttribute("complete", "変更が完了しました");
        	
            return "redirect:/edit/" + id;
            
        } else {
            model.addAttribute("memberRegistrationForm", memberRegistrationForm);
            model.addAttribute("title", "登録内容一覧");
            return "edit";
        }
        
        
    }
	
	 private Account makeAccount(MemberRegistrationForm memberRegistrationForm, 
			 int id) {
	        Account account = new Account();
	        if(id != 0) {
	        	account.setId(id);
	        }
	        account.setId(memberRegistrationForm.getId());
	        account.setName(memberRegistrationForm.getUserName());
	        account.setPassword(memberRegistrationForm.getPassword());
	        account.setRoles(memberRegistrationForm.getRoles());
	        account.setEnable_flag(memberRegistrationForm.getEnable_flag());
	        
	        return account;
	    }

	private MemberRegistrationForm makeMember(Account account) {
		
		MemberRegistrationForm memberRegistrationForm = new MemberRegistrationForm();

		memberRegistrationForm.setId(account.getId());
		memberRegistrationForm.setUserName(account.getName());
		memberRegistrationForm.setPassword(account.getPassword());
		memberRegistrationForm.setRoles(account.getRoles());
		memberRegistrationForm.setEnable_flag(account.getEnable_flag());
		
		return memberRegistrationForm;
	}

}
