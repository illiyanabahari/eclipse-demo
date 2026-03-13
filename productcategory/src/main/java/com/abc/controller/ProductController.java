package com.abc.controller;

import com.abc.entities.Category;
import com.abc.entities.Product;
import com.abc.repository.CategoryRepository;
import com.abc.repository.ProductRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repo;
    private final CategoryRepository categoryRepo;

    public ProductController(ProductRepository repo, CategoryRepository categoryRepo) {
        this.repo = repo;
        this.categoryRepo = categoryRepo;
    }

    @GetMapping
    public String index(@RequestParam(name = "q", required = false) String q,
                        Model model) {

        if (q != null && !q.trim().isEmpty()) {
            model.addAttribute("products", repo.findByProductnameContainingIgnoreCase(q.trim()));
            model.addAttribute("q", q.trim());
        } else {
            model.addAttribute("products", repo.findAll());
            model.addAttribute("q", "");
        }

        model.addAttribute("content", "/WEB-INF/views/products/index.jsp");
        return "layouts/main";

    }
    
    @GetMapping("/new")
    public String createForm(Model model)
    {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("content", "/WEB-INF/views/products/form.jsp");
        return "layouts/main";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Product product,
                       @RequestParam("categoryId") Integer categoryId) {

        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setCategory(category);

        repo.save(product);

        return "redirect:/products";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Product product = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("product not found"));
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("content", "/WEB-INF/views/products/form.jsp");
        return "layouts/main";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        repo.deleteById(id);
        return "redirect:/products";
    }

}
