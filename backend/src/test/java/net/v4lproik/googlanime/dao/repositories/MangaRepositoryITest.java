package net.v4lproik.googlanime.dao.repositories;

import net.v4lproik.googlanime.client.mysql.DatabaseTestConfiguration;
import net.v4lproik.googlanime.client.mysql.SqlDatabaseInitializer;
import net.v4lproik.googlanime.dao.api.MangaDao;
import net.v4lproik.googlanime.service.api.common.ImportOptions;
import net.v4lproik.googlanime.service.api.entities.MangaModel;
import net.v4lproik.googlanime.service.api.myanimelist.MyAnimeList;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListEntry;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListManga;
import net.v4lproik.googlanime.service.api.utils.TransformMangaMapper;
import org.hibernate.SessionFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {
                DatabaseTestConfiguration.class,
        })
@ImportResource("classpath*  : application-context.xml")
@WebAppConfiguration
@TransactionConfiguration
public class MangaRepositoryITest {

    @Autowired
    SqlDatabaseInitializer databaseInitializer;

    @Autowired
    SessionFactory sessionFactoryConfig;

    MangaDao mangaDao;

    TransformMangaMapper mapper;

    @Spy
    private MyAnimeList myAnimeList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        try {
            databaseInitializer.createDatabase();
        }catch (Exception e){
            // Something went wrong while importing the database schema
        }

        mangaDao = new MangaRepository(sessionFactoryConfig);

        mapper = new TransformMangaMapper();

    }

    @Test
    public void test_saveManga_shouldBeInserted() throws Exception {

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

        MangaModel mangaRes = mapper.transformMyAnimeListMangaToDAO((MyAnimeListManga) response);

        mangaDao.saveOrUpdate(mangaRes);

    }

    @Test
    public void test_saveMangaTwice_shouldBeUpdated() throws Exception {

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

        MangaModel mangaRes = mapper.transformMyAnimeListMangaToDAO((MyAnimeListManga) response);

        mangaDao.saveOrUpdate(mangaRes);
        mangaRes.setTitle(mangaRes.getTitle() + "_UPDATED");
        mangaDao.saveOrUpdate(mangaRes);

        MangaModel mangaFind = mangaDao.findById(mangaRes.getId());

        String title = mangaRes.getTitle();

        // Then
        assertEquals(title, mangaFind.getTitle());

    }

    @Test
    public void init_DB() throws Exception {


    }

    @After
    public void rollBack() throws Exception{
        mangaDao.deleteById(new Long(11));
    }
}