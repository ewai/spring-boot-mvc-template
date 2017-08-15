package info.ewai.sbmt.web;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
public class SimpleControllerTests {

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
     * Top page tests
     */
    @Test
    public void testGet_topPage_hasNotAuth() throws Exception {
        MvcResult result = this.mvc.perform(get("/"))
                .andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();

        assertThat(content, containsString(">ログイン</a>"));
        assertThat(content, containsString(">書籍検索</a>"));
        assertThat(content, not(containsString(">書籍登録</a>"))); // has not auth
    }

    @Test
    public void testGet_topPage_hasAuthUser() throws Exception {
        MvcResult result = this.mvc.perform(get("/").with(user("sbt").roles("USER")))
                .andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();

        assertThat(content, not(containsString(">ログイン</a>"))); // authenticated
        assertThat(content, containsString(">書籍検索</a>"));
        assertThat(content, not(containsString(">書籍登録</a>"))); // has not auth
    }

    @Test
    public void testGet_topPage_hasAuthAdmin() throws Exception {
        MvcResult result = this.mvc.perform(get("/").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();

        assertThat(content, not(containsString(">ログイン</a>"))); // authenticated
        assertThat(content, containsString(">書籍検索</a>"));
        assertThat(content, containsString(">書籍登録</a>")); // has auth
    }

    /**
     * Login tests
     */
    @Test
    public void testGet_login() throws Exception {
        MvcResult result = this.mvc.perform(get("/login"))
                .andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();

        assertThat(content, containsString(">ログインID<"));
        assertThat(content, containsString(">パスワード<"));
        assertThat(content, not(containsString(">ログアウト<")));
    }
}
