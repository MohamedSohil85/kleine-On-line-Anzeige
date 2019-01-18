package de.hs.da.hskleinanzeigen.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIT {

    @Autowired
    UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getExistingUser() throws Exception {
        String payload = "{\n" +
                "    \"id\": 1,\n" +
                "    \"email\": \"mail0@mustermail.de\",\n" +
                "    \"password\": \"dummy_pass_0\",\n" +
                "    \"first_name\": \"User0\",\n" +
                "    \"last_name\": \"Synthesis0\",\n" +
                "    \"phone\": \"01760\",\n" +
                "    \"location\": \"Musterstadt\",\n" +
                "    \"created\": \"2019-01-18T00:09:05.490+0000\"\n" +
                "}";
        Integer newUserId = 1; // Input ID to search
        String uri = "/api/users/" + newUserId;
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(payload));
    }

    @Test
    public void createNewUser() throws Exception {
        String json = "{\n" +
                "   \"email\": \"email@domain.com\",\n" +
                "   \"password\": \"verystrongpassword\",\n" +
                "   \"first_name\": \"Max\",\n" +
                "   \"last_name\": \"Mustermann\",\n" +
                "   \"phone\": \"01761234\",\n" +
                "   \"location\": \"Musterstadt\"\n" +
                "}";
        String uri = "/api/users";
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());

    }
}