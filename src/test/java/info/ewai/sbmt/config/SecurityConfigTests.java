package info.ewai.sbmt.config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    /**
     * Login tests
     */
    @Test
    public void test_login_user() throws Exception {
        this.mvc.perform(formLogin().user("sbt").password("sbt"))
            .andExpect(authenticated().withRoles("USER"));
    }

    @Test
    public void test_login_admin() throws Exception {
        this.mvc.perform(formLogin().user("admin").password("admin"))
            .andExpect(authenticated()); //.withRoles("ADMIN", "ACTUATOR"));
    }

    @Test
    public void test_login_invalid() throws Exception {
        this.mvc.perform(formLogin().user("sbt").password("invalid"))
            .andExpect(unauthenticated());
    }

    @Test
    public void testPost_csrf() throws Exception {
        this.mvc.perform(post("/").with(csrf()));
    }

    /**
     * Logout tests
     */
    @Test
    public void test_logout() throws Exception {
        MvcResult result = this.mvc.perform(logout())
                .andExpect(status().is3xxRedirection()).andReturn();
        assertThat("/", equalTo(result.getResponse().getHeader("Location")));
    }
}
