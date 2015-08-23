package net.v4lproik.googlanime.service.api.myanimelist;

import net.v4lproik.googlanime.service.api.common.ImportOptions;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListEntry;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class MyAnimeListUTest {

    @Spy
    private MyAnimeList myAnimeList;

    @Test
    public void testCrawlById_withGoodOptionsManga_shouldBeOK() throws Exception {
        // Given
        ImportOptions options = new ImportOptions(11, "manga", false);
        String type = options.getType();
        final Integer id = options.getId();
        String url = myAnimeList.createEntryURL(id, type);

        File input = new File("src/test/resource/naruto.manga");
        Document doc = Jsoup.parse(input, "UTF-8", url);

        doReturn(doc).when(myAnimeList).getResultFromJSoup(url, type);

        // When
        MyAnimeListEntry response = myAnimeList.crawlById(options);

        //Then
        assertEquals("Naruto", response.getTitle());
        assertEquals("manga", response.getType());
        assertEquals("http://cdn.myanimelist.net/images/manga/3/117681l.jpg", response.getPosterImage());
    }

    @Test
    public void testCrawlById_withGoodOptionsAnime_shouldBeOK() throws Exception {
        // Given
        ImportOptions options = new ImportOptions(2904, "anime", false);
        String type = options.getType();
        Integer id = options.getId();
        String url = myAnimeList.createEntryURL(id, type);

        File input = new File("src/test/resource/code-geass-r2.anime");
        Document doc = Jsoup.parse(input, "UTF-8", url);

        doReturn(doc).when(myAnimeList).getResultFromJSoup(url, type);

        // When
        MyAnimeListEntry response = myAnimeList.crawlById(options);


        //Then
        assertEquals("Code Geass: Hangyaku no Lelouch R2", response.getTitle());
        assertEquals("2904", response.getId().toString());
        assertEquals("anime", response.getType());
        assertEquals("Code Geass: Lelouch of the Rebellion R2", response.getEnglishTitle());
        assertEquals("コードギアス 反逆のルルーシュ 続編", response.getJapaneseTitle());
        assertEquals(true, response.getSynopsis().startsWith("A year has passed since \"The Black Rebellion\" and the remaining Black Knights have vanished into the shadows, their leader and"));
        assertEquals("Apr 6, 2008", response.getStartedAiringDate());
        assertEquals("Sep 28, 2008", response.getFinishedAiringDate());
        assertEquals("#112", response.getRank());
    }

    @Test(expected = IOException.class)
    public void testCrawlById_withGoodOptions_withNoDataToCrawl_shouldThrowIOException() throws Exception {
        // Given
        ImportOptions options = new ImportOptions(5081, "anime", false);
        String type = options.getType();
        final Integer id = options.getId();
        String url = myAnimeList.createEntryURL(id, type);

        doReturn(null).when(myAnimeList).getResultFromJSoup(url, type);

        // When
        MyAnimeListEntry response = myAnimeList.crawlById(options);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrawlById_withMissingOptions_shouldThrowIllegalArgumentException() throws Exception {
        // Given
        ImportOptions options = new ImportOptions(11, null, false);
        String type = options.getType();
        final Integer id = options.getId();

        // When
        MyAnimeListEntry response = myAnimeList.crawlById(options);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrawlById_withEmptyOptions_shouldThrowIllegalArgumentException() throws Exception {
        // Given
        ImportOptions options = new ImportOptions(11, "", false);
        String type = options.getType();
        final String id = options.getId().toString();

        // When
        MyAnimeListEntry response = myAnimeList.crawlById(options);
    }

    @Test
    public void test_crawlCharacter_shouldBeOK() throws Exception {
        // Given
        ImportOptions options = new ImportOptions(11, "manga", false);
        String type = options.getType();
        final Integer id = options.getId();
        String url = myAnimeList.createEntryURL(id, type);

        File input = new File("src/test/resource/naruto.manga");
        Document doc = Jsoup.parse(input, "UTF-8", url);

        doReturn(doc).when(myAnimeList).getResultFromJSoup(url, type);

        // When
        MyAnimeListEntry response = myAnimeList.crawlById(options);


        input = new File("src/test/resource/naruto-uzumaki.character");
        doc = Jsoup.parse(input, "UTF-8", url);


        Integer idCharacter = response.getCharacters().get(0).getId();
        url = myAnimeList.createCharacterURL(idCharacter);

        myAnimeList.scrapCharacter(doc, url, response.getCharacters().get(0));

        //Then
        assertEquals("Naruto", response.getTitle());
        assertEquals("http://cdn.myanimelist.net/images/manga/3/117681l.jpg", response.getPosterImage());
        assertEquals("うずまきナルト", response.getCharacters().get(0).getJapaneseName());
    }
}