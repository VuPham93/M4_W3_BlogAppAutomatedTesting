import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import blogapp.service.blog.BlogService;
import blogapp.service.blog.IBlogService;
import blogapp.service.category.CategoryService;
import blogapp.service.category.ICategoryService;

import javax.sql.DataSource;

@Configuration
@ComponentScan("blogapp")
public class BlogControllerTestConfig {
    @Bean
    public IBlogService blogService() {
        return Mockito.mock(BlogService.class);
    }

    @Bean
    public ICategoryService categoryService() {
        return Mockito.mock(CategoryService.class);
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("blogApp")
                .build();
    }
}
