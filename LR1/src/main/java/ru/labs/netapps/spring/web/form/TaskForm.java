package ru.labs.netapps.spring.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskForm {
    @NotBlank(message = "title обязателен")
    @Size(max = 200, message = "title слишком длинный (max 200)")
    private String title;

    @NotBlank(message = "description обязателен")
    private String description;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
