package net.v4lproik.googlanime.dao.repositories;

import net.v4lproik.googlanime.client.mysql.DatabaseTestConfiguration;
import net.v4lproik.googlanime.client.mysql.SqlDatabaseInitializer;
import net.v4lproik.googlanime.dao.api.AnimeDao;
import net.v4lproik.googlanime.dao.api.MangaDao;
import net.v4lproik.googlanime.service.api.AnimeServiceWrite;
import net.v4lproik.googlanime.service.api.common.ImportOptions;
import net.v4lproik.googlanime.service.api.entities.AnimeModel;
import net.v4lproik.googlanime.service.api.myanimelist.MyAnimeList;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAnime;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListEntry;
import net.v4lproik.googlanime.service.api.utils.TransformAnimeMapper;
import net.v4lproik.googlanime.service.api.utils.TransformMangaMapper;
import org.hibernate.SessionFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
public class AnimeRepositoryITest {

    @Autowired
    SqlDatabaseInitializer databaseInitializer;

    @Autowired
    SessionFactory sessionFactoryConfig;

    AnimeDao animeDao;

    MangaDao mangaDao;

    AnimeServiceWrite service;

    TransformAnimeMapper animeMapper;

    TransformMangaMapper mangaMapper;

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

        animeDao = new AnimeRepository(sessionFactoryConfig);

        mangaDao = new MangaRepository(sessionFactoryConfig);

        animeMapper = new TransformAnimeMapper();

        mangaMapper = new TransformMangaMapper();
    }

    @Test
    public void test_saveAnimeWithDependencies_shouldBeInsertedWithItsDependencies() throws Exception{
//        // Given
//        ImportOptions options = new ImportOptions(8425, "anime", true);
//        String type = options.getType();
//        final Integer id = options.getId();
//
//        // When
//        Set<MyAnimeListEntryDependency> response = myAnimeList.crawlByIdList(options);
//
//        // Then
//        for (MyAnimeListEntryDependency entry : response) {
//
//            final String type2 = entry.getType();
//
//            net.v4lproik.googlanime.service.api.common.EntryTypeEnum typeEnum = net.v4lproik.googlanime.service.api.common.EntryTypeEnum.fromValue(type2);
//
//            if (typeEnum == null){
//                throw new EnumConstantNotPresentException(net.v4lproik.googlanime.service.api.myanimelist.models.EntryTypeEnum.class, type);
//            }
//
//            switch (typeEnum){
//                case MANGA:
//                    MangaModel mangaRes = mangaMapper.transformMyAnimeListMangaDependencyToDAO((MyAnimeListMangaDependency) entry);
//
//                    if (mangaRes.getTitle() != null) {
//                        mangaDao.saveOrUpdate(mangaRes);
//                    }
//                    break;
//
//                case ANIME:
//                    AnimeModel animeRes = animeMapper.transformMyAnimeListAnimeDependencyToDAO((MyAnimeListAnimeDependency) entry);
//
//                    if (animeRes.getTitle() != null) {
//                        animeDao.saveOrUpdate(animeRes);
//                    }
//                    break;
//
//                default:
//                    throw new IllegalArgumentException();
//            }
//        }
    }

    @Test
    public void test_saveAnime_shouldBeInserted() throws Exception {

        // Given
        ImportOptions options = new ImportOptions(2904, "anime", false);
        String type = options.getType();
        Integer id = options.getId();
        String url = myAnimeList.createURL(id, type);

        File input = new File("src/test/resource/code-geass-r2.anime");
        Document doc = Jsoup.parse(input, "UTF-8", url);

        doReturn(doc).when(myAnimeList).getResultFromJSoup(url, type);

        // When
        MyAnimeListEntry response = myAnimeList.crawlById(options);

        AnimeModel animeRes = animeMapper.transformMyAnimeListAnimeToDAO((MyAnimeListAnime) response);

        animeDao.saveOrUpdate(animeRes);

    }

    @Test
    public void test_saveAnimeTwice_shouldBeUpdated() throws Exception {

        // Given
        ImportOptions options = new ImportOptions(2904, "anime", false);
        String type = options.getType();
        Integer id = options.getId();
        String url = myAnimeList.createURL(id, type);

        File input = new File("src/test/resource/code-geass-r2.anime");
        Document doc = Jsoup.parse(input, "UTF-8", url);

        doReturn(doc).when(myAnimeList).getResultFromJSoup(url, type);

        // When
        MyAnimeListEntry response = myAnimeList.crawlById(options);

        AnimeModel animeRes = animeMapper.transformMyAnimeListAnimeToDAO((MyAnimeListAnime) response);

        animeDao.saveOrUpdate(animeRes);
        animeRes.setTitle(animeRes.getTitle() + "_UPDATED");
        animeDao.saveOrUpdate(animeRes);

        AnimeModel animeFind = animeDao.find(animeRes.getId());

        String title = animeRes.getTitle();

        // Then
        assertEquals(title, animeFind.getTitle());

    }
}