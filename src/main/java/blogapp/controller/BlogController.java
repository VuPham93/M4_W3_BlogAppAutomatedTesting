package blogapp.controller;

import blogapp.model.Blog;
import blogapp.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import blogapp.service.blog.IBlogService;
import blogapp.service.category.ICategoryService;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping("/")
    public ModelAndView listBlogs(@PageableDefault(size = 10, direction = Sort.Direction.DESC, sort = "id") Pageable pageable) {
        Page<Blog> blogs = blogService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("blog/list");
        modelAndView.addObject("blogList", blogs);
        return modelAndView;
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<Blog>> getAll() {
        Iterable<Blog> blogs = blogService.findAll();
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/search/{searchValue}")
    public ResponseEntity<Iterable<Blog>> searchBlogs(@PathVariable String searchValue) {
        Iterable<Blog> blogs = blogService.findAllByNameContaining(searchValue);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Blog> showDetail(@PathVariable Long id) {
        Blog blog = blogService.findById(id);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Blog> create(@RequestBody Blog blog) {
        blogService.save(blog);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Blog> edit(@PathVariable Long id, @RequestBody Blog blog) {
        blogService.save(blog);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable Long id) {
        blogService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
