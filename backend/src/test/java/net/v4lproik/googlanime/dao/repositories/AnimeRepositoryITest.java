package net.v4lproik.googlanime.dao.repositories;

import net.v4lproik.googlanime.client.mysql.DatabaseTestConfiguration;
import net.v4lproik.googlanime.client.mysql.SqlDatabaseInitializer;
import net.v4lproik.googlanime.dao.api.AnimeDAO;
import net.v4lproik.googlanime.service.api.AnimeServiceWrite;
import net.v4lproik.googlanime.service.api.common.ImportOptions;
import net.v4lproik.googlanime.service.api.entities.AnimeModel;
import net.v4lproik.googlanime.service.api.myanimelist.MyAnimeList;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAnime;
import net.v4lproik.googlanime.service.api.utils.TransformAnimeMapper;
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
    AnimeDAO animeDAO;
    AnimeServiceWrite service;

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

        animeDAO = new AnimeRepository(sessionFactoryConfig);

        TransformAnimeMapper animeMapper = new TransformAnimeMapper();

    }

    @Test
    public void test_saveMysql_withGoodGenre_shouldBeInserted() throws Exception {

        //Given
//        ImportOptions options = new ImportOptions(24, "manga", true);
//        String type = options.getType();
//        final Integer id = options.getId();
//
//        // When
//        List<MyAnimeListAnimeDependency> response = myAnimeList.crawlByIdList(options);


        // Then
//        for (MyAnimeListAnimeDependency anime : response) {
//
//            TransformAnimeMapper mapper = new TransformAnimeMapper();
//
//            AnimeModel animeRes = mapper.transformMyAnimeListAnimeDependencyToDAO(anime);
//
//            if (anime.getTitle() != null) {
//                animeDAO.saveOrUpdate(animeRes);
//            }
//        }


        // Given
        ImportOptions options = new ImportOptions(2904, "anime", false);
        String type = options.getType();
        Integer id = options.getId();
        String url = myAnimeList.createURL(id, type);

        File input = new File("src/test/resource/code-geass-r2.anime");
        Document doc = Jsoup.parse(input, "UTF-8", url);

        doReturn(doc).when(myAnimeList).getResultFromJSoup(url, type);

        // When
        MyAnimeListAnime response = myAnimeList.crawlById(options);

        TransformAnimeMapper mapper = new TransformAnimeMapper();

        AnimeModel animeRes = mapper.transformMyAnimeListAnimeToDAO(response);

        if (response.getTitle() != null) {
            animeDAO.saveOrUpdate(animeRes);
        }

    }
}