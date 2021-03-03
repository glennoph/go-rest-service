package go.springboot.gorestservice;

        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.http.MediaType;
        import org.springframework.test.web.servlet.MockMvc;
        import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

        import java.awt.*;
        import java.util.regex.Matcher;


        import static org.hamcrest.Matchers.*;
        import static org.junit.jupiter.api.Assertions.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GreetingControllerTest {

    @Autowired
    private MockMvc mvc;


    @BeforeEach
    void setUp() {
        System.out.println("GreetingControllerTest");
    }

    @Test
    void greeting() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/greeting")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"content\":\"Hello, World\"}")) // ok
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //.andExpect(jsonPath("$", hasSize(0))) // fails
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("content", startsWith("Hello")))
                .andExpect(jsonPath("content", is("Hello, World")))
        ;
    }
}