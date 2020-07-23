import blogapp.repository.IBlogRepository;
import blogapp.service.blog.BlogService;
import blogapp.service.blog.IBlogService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

public class BlogServiceTestConfig {
    @Bean
    public IBlogRepository blogRepository() {
        return Mockito.mock(IBlogRepository.class);
    }

    @Bean
    public IBlogService blogService() {
        return new BlogService();
    }
}
