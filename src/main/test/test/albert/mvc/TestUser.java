package albert.mvc;

import albert.cui.controller.UserController;
import albert.cui.entity.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author albert.cui
 * @date 2019/2/24 15:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml",
        "classpath:spring-jdbc.xml",
        "classpath:spring-mvc.xml"})
public class TestUser {
    @Autowired
    private WebApplicationContext context;
    MockMvc mvc;
    @Before
    public void before() {
        //针对单个controller进行测试
       mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
        //针对多个controller进行测试
        //mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /**
     * post方式，有请求参数
     */
    @Test
    public void testController() {
        try {
            ResultActions actions = this.mvc.perform(post("/user/test").param("id","2")
            .param("name","choosl.liu"));
            Employee e = (Employee) actions.andReturn().getModelAndView().getModel().get("employee");
            System.out.println(e);
           actions.andExpect(status().isOk());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGetUser() {
        try {
            ResultActions actions = this.mvc.perform(get("/user/get/1"));
            MvcResult mvcResult = actions.andReturn();
            MockHttpServletResponse response = mvcResult.getResponse();
            String content = response.getContentAsString();
            System.out.println(content);
            System.out.println(status());
            System.out.println(content().toString());
            actions.andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
