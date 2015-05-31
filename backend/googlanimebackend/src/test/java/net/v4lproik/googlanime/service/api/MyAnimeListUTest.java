package net.v4lproik.googlanime.service.api;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class MyAnimeListUTest {

    @Spy
    private MyAnimeList myAnimeList;

    @Test
    public void testCrawlById() throws Exception {
        // Given
        ImportOptions options = new ImportOptions(11, "manga", false);
        String type = options.getType();
        final String id = options.getId().toString();
        final String url = MyAnimeList.DOMAIN + type + "/" + id;

        File input = new File("src/test/resource/naruto-manga.html");
        Document doc = Jsoup.parse(input, "UTF-8", url);

        doReturn(doc).when(myAnimeList).getResultFromJSoup(url, type);

        // When
        MyAnimeListAnime response = myAnimeList.crawlById(options);

        //Then
        assertEquals("Naruto", response.getTitle());
    }
}