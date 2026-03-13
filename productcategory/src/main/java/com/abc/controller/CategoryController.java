package com.abc.controller;

import com.abc.entities.Category;
import com.abc.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository repo;

    public CategoryController(CategoryRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String index(@RequestParam(name = "q", required = false) String q,
                        Model model) {

        if (q != null && !q.trim().isEmpty()) {
            model.addAttribute("categories", repo.findByCategorynameContainingIgnoreCase(q.trim()));
            model.addAttribute("q", q.trim());
        } else {
            model.addAttribute("categories", repo.findAll());
            model.addAttribute("q", "");
        }

        model.addAttribute("content", "/WEB-INF/views/categories/index.jsp");
        return "layouts/main";

    }
    
    @GetMapping("/new")
    public String createForm(Model model)
    {
        model.addAttribute("category", new Category());
        model.addAttribute("content", "/WEB-INF/views/categories/form.jsp");
        return "layouts/main";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Category category)
    {
        repo.save(category);
        return "redirect:/categories";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Category category = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        model.addAttribute("category", category);
        model.addAttribute("content", "/WEB-INF/views/categories/form.jsp");
        return "layouts/main";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        repo.deleteById(id);
        return "redirect:/categories";
    }

}
