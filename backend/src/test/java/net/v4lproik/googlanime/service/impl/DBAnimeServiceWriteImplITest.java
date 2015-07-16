package net.v4lproik.googlanime.service.impl;

import net.v4lproik.googlanime.client.mysql.DatabaseTestConfiguration;
import net.v4lproik.googlanime.client.mysql.SqlDatabaseInitializer;
import net.v4lproik.googlanime.dao.api.AnimeDAO;
import net.v4lproik.googlanime.dao.repositories.AnimeRepository;
import net.v4lproik.googlanime.service.api.AnimeServiceWrite;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAnimeDependency;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAnimeDependencyId;
import net.v4lproik.googlanime.service.api.utils.TransformAnimeMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {
                DatabaseTestConfiguration.class,
        })
@ImportResource("classpath*  : application-context.xml")
@WebAppConfiguration
public class DBAnimeServiceWriteImplITest {

    @Autowired
    SqlDatabaseInitializer databaseInitializer;

    AnimeServiceWrite service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        try {
            databaseInitializer.createDatabase();
        }catch (Exception e){
            // Something went wrong while importing the database schema
        }

        AnimeDAO animeDAO = new AnimeRepository();
        TransformAnimeMapper animeMapper = new TransformAnimeMapper();

        service = new DBAnimeServiceWriteImpl(animeDAO, animeMapper);
    }

    @Test
    public void test_saveMysql_withGoodGenre_shouldBeInserted() {
        MyAnimeListAnimeDependency anime = new MyAnimeListAnimeDependency(2);
        anime.setTitle("main anime");
        anime.setEnglishTitle("main anime english title");
        anime.setJapaneseTitle("main anime japanese title");
        anime.setType("anime");

        MyAnimeListAnimeDependencyId adaptation = new MyAnimeListAnimeDependencyId(4);
        adaptation.setEnglishTitle("adaptation english title");
        adaptation.setType("manga");
        List<MyAnimeListAnimeDependencyId> adaptations = new ArrayList<>();
        adaptations.add(adaptation);

        anime.setAdaptations(adaptations);

        service.saveAnime(anime);

    }
}