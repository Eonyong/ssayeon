package a204.ssayeon.api.controller;

import a204.ssayeon.api.service.BalanceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BalanceController.class)
class BalanceControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    BalanceService balanceService;
//
//    @Autowired
//    private BalanceController balanceController;
//
//    // test for balanceController is injected
//    @Test
//    public void contextLoads() throws Exception{
//        assertThat(balanceController).isNotNull();
//    }
//
//    @Test
//    public void helloTest() throws Exception {
//        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/hello");
//        mockMvc.perform(builder)
//                .andExpect(status().isOk())
//                .andExpect(content().string("hello"))
//                .andDo(print());
//    }
//
//    @Test
//    public void apiTest() throws Exception {
//        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/hello");
//        mockMvc.perform(builder)
//                .andExpect(status().isOk())
//                .andExpect(content().string("hello"))
//                .andDo(print());
//    }
}