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
    }

//    @Test
//    public void testCrawlById_withGoodOptionsAnimeXXXXXXXXXXXXXXXXXXXXXXXX_shouldBeOK() throws Exception {
//        // Given
//        ImportOptions options = new ImportOptions(24, "manga", true);
//        String type = options.getType();
//        final Integer id = options.getId();
//
//        // When
//        MyAnimeListAnime response = myAnimeList.crawlById(options);
//
//
//        System.out.println("------------RESPONSE------------");
//        System.out.println(response.toString());
//
//        //Then
//        assertEquals("Code Geass: Hangyaku no Lelouch R2", response.getTitle());
//    }

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