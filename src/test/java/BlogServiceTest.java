import blogapp.model.Blog;
import blogapp.repository.IBlogRepository;
import blogapp.service.blog.IBlogService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringJUnitJupiterConfig;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitJupiterConfig(BlogServiceTestConfig.class)
public class BlogServiceTest {
    @Autowired
    private IBlogService blogService;

    @Autowired
    private IBlogRepository blogRepository;

    @AfterEach
    private void resetMocks() {
        Mockito.reset(blogRepository);
    }

    @Test
    void testFindAll() {
        List<Blog> blogs = new ArrayList<>();
        blogs.add(new Blog(1L, "asda", "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"));
        PageRequest pageable = new PageRequest(0, 20);
        Page<Blog> blogPage = new PageImpl<>(blogs, pageable, 1);
        when(blogRepository.findAll(pageable)).thenReturn(blogPage);

        Page<Blog> actual = blogService.findAll(pageable);
        verify(blogRepository).findAll(pageable);
        assertEquals(blogPage, actual);
    }

    @Test
    void testFindOneFound() {
        Blog blog = new Blog(1L, "asda", "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
        when(blogRepository.findOne(1L)).thenReturn(blog);

        Blog actual = blogService.findById(1L);
        verify(blogRepository).findOne(1L);
        assertEquals(blog, actual);
    }

    @Test
    void testFindOneNotFound() {
        when(blogRepository.findOne(1L)).thenReturn(null);

        Blog actual = blogService.findById(1L);
        verify(blogRepository).findOne(1L);
        assertNull(actual);
    }

    @Test
    void saveBlog() {
        Blog blog = new Blog(1L, "asda", "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
        blogService.save(blog);
        verify(blogRepository).save(blog);
    }
}
