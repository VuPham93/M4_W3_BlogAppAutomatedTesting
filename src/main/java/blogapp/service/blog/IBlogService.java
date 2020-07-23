package blogapp.service.blog;

import blogapp.model.Blog;
import blogapp.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlogService {
    Iterable<Blog> findAll();

    Page<Blog> findAll(Pageable pageable);

    void save(Blog blog);

    Blog findById(Long id);

    void remove(Long id);

    Iterable<Blog> findAllByCategory(Category category);

    Page<Blog> findAllByNameContaining(String name, Pageable pageable);

    Iterable<Blog> findAllByNameContaining(String name);
}
