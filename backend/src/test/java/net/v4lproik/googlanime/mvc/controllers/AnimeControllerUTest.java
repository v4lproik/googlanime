package net.v4lproik.googlanime.mvc.controllers;

import net.v4lproik.googlanime.Config;
import org.elasticsearch.client.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*  :application-context.xml",
        "classpath*  :dispatcher-config.xml"
})
@WebAppConfiguration
public class AnimeControllerUTest {

    @Mock
    private Client client;

    @InjectMocks
    private AnimeController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        client = new Config().client();
    }

    @Test
    public void testGetAnimeFromMALWithNoDependency_withData_shouldReturnAnime() throws Exception {
        // Given
        String query = "11eyes";

        mockMvc.perform(get("/animes/")
                        .param("n", query))
        .andExpect(status().isOk());
    }
}