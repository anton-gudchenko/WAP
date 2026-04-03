package ru.labs.netapps.spring.web;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.labs.netapps.spring.repo.TaskRepository;
import ru.labs.netapps.spring.web.form.TaskForm;

@Controller
@RequestMapping("/app/tasks")
public class TaskMvcController {
    private final TaskRepository repo;

    public TaskMvcController(TaskRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String list(HttpSession session, Model model) {
        String last4 = String.valueOf(session.getAttribute("last4"));
        model.addAttribute("tasks", repo.findAll(last4));
        model.addAttribute("last4", last4);
        return "tasks";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("taskForm", new TaskForm());
        return "task_form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("taskForm") TaskForm form,
                         BindingResult br,
                         HttpSession session,
                         RedirectAttributes ra) {
        if (br.hasErrors()) {
            return "task_form";
        }

        String last4 = String.valueOf(session.getAttribute("last4"));
        repo.create(form.getTitle().trim(), form.getDescription().trim(), last4);

        ra.addFlashAttribute("flash", "Создано");
        return "redirect:/app/tasks";
    }
}
