package com.example.demo.app.task;

import java.time.LocalDateTime;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class TaskForm {

	//1桁の整数　　少数桁数0、1桁で渡されたかどうか
    @Digits(integer = 1, fraction = 0)
    private int typeId;
    
    private int userId;

    @NotNull (message = "タイトルを入力してください。")
    @Size(min = 1, max = 20, message="20文字以内で入力してください。")
    private String title;

    @NotNull (message = "内容を入力してください。")
    private String detail;

    @NotNull (message = "期限を設定してください。")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Future (message = "期限が過去に設定されています。")
    private LocalDateTime deadline;

    public boolean isNewTask;
    
    public TaskForm() {}

	public TaskForm(int typeId,
			int userId,
			String title,
			String detail, 
			LocalDateTime deadline,
			boolean isNewTask) {
		this.typeId = typeId;
		this.title = title;
		this.detail = detail;
		this.deadline = deadline;
		this.isNewTask = isNewTask;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	public boolean isNewTask() {
		return isNewTask;
	}

	public void setNewTask(boolean isNewTask) {
		this.isNewTask = isNewTask;
	};

}