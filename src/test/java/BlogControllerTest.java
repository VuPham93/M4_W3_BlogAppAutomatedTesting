import blogapp.controller.BlogController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit.jupiter.SpringJUnitJupiterConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import blogapp.service.blog.IBlogService;
import blogapp.service.category.ICategoryService;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@SpringJUnitJupiterConfig(BlogControllerTestConfig.class)
public class BlogControllerTest {
    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    private MockMvc mockMvc;

    @InjectMocks
    private BlogController blogController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.
                standaloneSetup(blogController).
                setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).
                build();
    }

    @Test
    void getListTest() throws Exception {
        mockMvc.perform(get("/blogs/list"))
                .andExpect((status().isOk()));
    }

    @Test
    void viewListTest() throws Exception {
        mockMvc.perform(get("/blogs/"))
                .andExpect((status().is(200)))
                .andExpect(view().name("blog/list"));
    }

    @Test
    void searchBlogTest() throws Exception {
        mockMvc.perform(get("/blogs/search/1313131313131313"))
                .andExpect((status().isOk()));
    }
}
