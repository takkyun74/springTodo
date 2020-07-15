package com.example.demo.app.task;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.entity.Task;
import com.example.demo.service.RegisterMemberService;
import com.example.demo.service.TaskService;

/**
 * トップ画面
 * 一覧
 */
@Controller
@RequestMapping("/top")
public class TopController {

    private final TaskService taskService;

    private final RegisterMemberService registerMemberService;
    
    @Autowired
    public TopController(TaskService taskService,
    		RegisterMemberService registerMemberService) {
        this.taskService = taskService;
		this.registerMemberService = registerMemberService;
    }

    

    /**
     * タスクの一覧を表示します
     * @param taskForm
     * @param model
     * @return resources/templates下のHTMLファイル名
     */
    @GetMapping
    public String task(
    		@AuthenticationPrincipal User user,
    		Account account,
    		TaskForm taskForm, Model model) {
    	
    	
    	
    	String userName = user.getUsername();
    	Optional<Account> accountOpt = registerMemberService.getAccount(userName);
        
        //TaskFormがnullでなければ中身を取り出し
        //isPresentで中身が入っているか確認
        if(accountOpt.isPresent()) {
        	account = accountOpt.get();
        }
        model.addAttribute("userId", account.getId());
    	
    	//新規登録か更新かを判断する仕掛け
        taskForm.setNewTask(true);
        
        //Taskのリストを取得する
        List<Task> list = taskService.findAll();
        

        model.addAttribute("list", list);
        model.addAttribute("title", "全ユーザータスク一覧");

        return "top";
    }

//    /**
//     * タスクデータを一件挿入
//     * @param taskForm
//     * @param result
//     * @param model
//     * @param principal
//     * @return
//     */
//    @PostMapping("/insert")
//    public String insert(
//    	@Valid @ModelAttribute TaskForm taskForm,
//        BindingResult result,
//        Model model) {
//    	
////    	Task task = null;
//    	
//    	//TaskFormのデータをTaskに格納
////        Task task = new Task();
////        task.setUserId(1);
////        task.setTypeId(taskForm.getTypeId());
////        task.setTitle(taskForm.getTitle());
////        task.setDetail(taskForm.getDetail());
////        task.setDeadline(taskForm.getDeadline());
//        
//    	Task task = makeTask(taskForm, 0);
//    	
//    	//エラーがなければ
//        if (!result.hasErrors()) {
//        	
//        	//一件挿入後リダイレクト
//        	taskService.insert(task);
//        	
//        	return "redirect:/top";
//
//        	//エラーがあった場合
//        } else {
//            taskForm.setNewTask(true);
//            model.addAttribute("taskForm", taskForm);
//            List<Task> list = taskService.findAll();
//            model.addAttribute("list", list);
//            model.addAttribute("title", "タスク一覧（バリデーション）");
//            return "top/index";
//        }
//    }

    /**
     * 一件タスクデータを取得し、フォーム内に表示
     * @param taskForm
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String showUpdate(
    	TaskForm taskForm,
        @PathVariable int id,
        Model model) {

    	//Taskを取得(Optionalでラップ)
        Optional<Task> taskOpt = taskService.getTask(id);
        
        //TaskFormへの詰め直し
        Optional<TaskForm> taskFormOpt = taskOpt.map(t -> makeTaskForm(t));
        
        //TaskFormがnullでなければ中身を取り出し
        //isPresentで中身が入っているか確認
        if(taskFormOpt.isPresent()) {
        	taskForm = taskFormOpt.get();
        }
		
        model.addAttribute("taskForm", taskForm);
        List<Task> list = taskService.findAll();
        model.addAttribute("list", list);
        model.addAttribute("taskId", id);
        model.addAttribute("title", "更新用フォーム");

        return "top/index";
    }
    
//    /**
//     * タスクidを取得し、一件のデータ更新
//     * @param taskForm
//     * @param result
//     * @param id
//     * @param model
//     * @param redirectAttributes
//     * @return
//     */
//    @PostMapping("/update")
//    public String update(
//    	@Valid @ModelAttribute TaskForm taskForm,
//    	BindingResult result,
//    	//hiddenの受け取りはRequestParamを使う
//    	@RequestParam("taskId") int taskId,
//    	Model model,
//    	//セッションを一度だけ使う
//    	RedirectAttributes redirectAttributes) {
//        
//    	//TaskFormのデータをTaskに格納
//    	Task task = makeTask(taskForm, taskId);
//    	
//    	
//        if (!result.hasErrors()) {
//        	
//        	//更新処理、フラッシュスコープの使用、リダイレクト（個々の編集ページ）
//        	taskService.update(task);
//        	redirectAttributes.addFlashAttribute("complete", "変更が完了しました");
//        	
//            return "redirect:/task/" + taskId;
//            
//        } else {
//            model.addAttribute("taskForm", taskForm);
//            model.addAttribute("title", "タスク一覧");
//            return "task/index";
//        }
//        
//        
//    }

    /**
     * タスクidを取得し、一件のデータ削除
     * @param id
     * @param model
     * @return
     */
    @PostMapping("/delete")
    public String delete(
    	@RequestParam("taskId") int id,
    	Model model) {
    	
    	//タスクを一件削除しリダイレクト
    	taskService.deleteById(id);
    	
        return "redirect:/top";
    }

    /**
     * TaskFormのデータをTaskに入れて返す
     * @param taskForm
     * @param taskId 新規登録の場合は0を指定
     * @return
     */
    private Task makeTask(TaskForm taskForm, int taskId) {
        Task task = new Task();
        if(taskId != 0) {
        	task.setId(taskId);
        }
        task.setUserId(1);
        task.setTypeId(taskForm.getTypeId());
        task.setTitle(taskForm.getTitle());
        task.setDetail(taskForm.getDetail());
        task.setDeadline(taskForm.getDeadline());
        return task;
    }

    /**
     * TaskのデータをTaskFormに入れて返す
     * @param task
     * @return
     */
    private TaskForm makeTaskForm(Task task) {
    	
        TaskForm taskForm = new TaskForm();

        taskForm.setTypeId(task.getTypeId());
        taskForm.setTitle(task.getTitle());
        taskForm.setDetail(task.getDetail());
        taskForm.setDeadline(task.getDeadline());
        taskForm.setNewTask(false);
        
        return taskForm;
    }

}