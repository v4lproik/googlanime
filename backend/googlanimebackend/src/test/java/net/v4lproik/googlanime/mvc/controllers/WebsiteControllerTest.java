package net.v4lproik.googlanime.mvc.controllers;

import com.jayway.jsonpath.JsonPath;
import net.v4lproik.googlanime.mvc.models.Website;
import net.v4lproik.googlanime.mvc.models.WebsiteFactory;
import net.v4lproik.googlanime.service.api.ImportOptions;
import net.v4lproik.googlanime.service.api.MyAnimeListAnime;
import net.v4lproik.googlanime.service.api.WebsiteAbstract;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*  :application-context.xml",
        "classpath*  :dispatcher-config.xml"
})
@WebAppConfiguration
public class WebsiteControllerTest {

    @Mock
    WebsiteAbstract websiteAbstract;

    @Mock
    WebsiteFactory websiteFactory;

    @InjectMocks
    private WebsiteController websiteController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(websiteController).build();
    }

    @Test
    public void testGetAnimeFromMALWithNoDependency_withData_shouldReturnAnime() throws Exception {
        // Given
        final String from = "mal";
        final Integer id = 20;
        final String type = "anime";
        final Boolean dependency = false;
        MyAnimeListAnime anime = new MyAnimeListAnime(id);
        anime.setTitle("Naruto");

        when(websiteFactory.getWebsite(Website.valueOf(from.toUpperCase()))).thenReturn(websiteAbstract);
        when(websiteAbstract.crawlById(new ImportOptions(anyInt(), type, dependency))).thenReturn(anime);


        mockMvc.perform(get("/websites/import")
                        .param("from", from)
                        .param("type", type)
                        .param("id", id.toString())
                        .param("dependency", dependency.toString())
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.animes.title", is("Naruto")));
    }

    @Test
    public void testGetAnimeFromUnknown_shouldReturnNOK() throws Exception {
        // Given
        final String from = "unknown";
        final Integer id = 20;
        final String type = "anime";
        final Boolean dependency = false;
        MyAnimeListAnime anime = new MyAnimeListAnime(id);
        anime.setTitle("Naruto");

        MvcResult result = mockMvc.perform(get("/websites/import")
                        .param("from", from)
                        .param("type", type)
                        .param("id", id.toString())
                        .param("dependency", dependency.toString())
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.error", notNullValue()))
        .andReturn();

        String content = result.getResponse().getContentAsString();
        String errorMsg = JsonPath.read(content, "$.error");
        assertTrue(errorMsg.startsWith("Website enum"));
    }


    //first list function tests

    @Test
    public void testGetAnimeFromMALWithNoDependency_withNoDependencyOnUrl_withData_shouldReturnAnime() throws Exception {
        // Given
        final String from = "mal";
        final Integer id = 20;
        final String type = "anime";
        MyAnimeListAnime anime = new MyAnimeListAnime(id);
        anime.setTitle("Naruto");

        when(websiteFactory.getWebsite(Website.valueOf(from.toUpperCase()))).thenReturn(websiteAbstract);
        when(websiteAbstract.crawlById(new ImportOptions(anyInt(), type, false))).thenReturn(anime);


        mockMvc.perform(get("/websites/import")
                        .param("from", from)
                        .param("type", type)
                        .param("id", id.toString())
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.animes.title", is("Naruto")));
    }

    @Test
    public void testGetAnimeFromUnknown_withNoDependencyOnUrl_shouldReturnNOK() throws Exception {
        // Given
        final String from = "unknown";
        final Integer id = 20;
        final String type = "anime";
        MyAnimeListAnime anime = new MyAnimeListAnime(id);
        anime.setTitle("Naruto");

        MvcResult result = mockMvc.perform(get("/websites/import")
                        .param("from", from)
                        .param("type", type)
                        .param("id", id.toString())
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error", notNullValue()))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        String errorMsg = JsonPath.read(content, "$.error");
        assertTrue(errorMsg.startsWith("Website enum"));
    }
}