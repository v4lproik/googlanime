package net.v4lproik.googlanime.service.impl;

import net.v4lproik.googlanime.client.mysql.DatabaseTestConfiguration;
import net.v4lproik.googlanime.client.mysql.SqlDatabaseInitializer;
import net.v4lproik.googlanime.dao.api.AnimeDAO;
import net.v4lproik.googlanime.dao.repositories.AnimeRepository;
import net.v4lproik.googlanime.service.api.AnimeServiceWrite;
import net.v4lproik.googlanime.service.api.models.AnimeModel;
import net.v4lproik.googlanime.service.api.models.GenreModel;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {
                DatabaseTestConfiguration.class,
        })
@ImportResource("classpath*  : application-context.xml")
public class DBAnimeServiceWriteImplITest {

    @Autowired
    SqlDatabaseInitializer databaseInitializer;

    @Autowired
    SessionFactory sessionFactoryConfig;

    AnimeServiceWrite service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        try {
            databaseInitializer.createDatabase();
        }catch (Exception e){
            // Something went wrong while importing the database schema
        }

        AnimeDAO animeDAO = new AnimeRepository(AnimeModel.class, sessionFactoryConfig);
        service = new DBAnimeServiceWriteImpl(animeDAO);
    }

    @Test
    public void test_saveMysql_withGoodGenre_shouldBeInserted() {
        AnimeModel adaptation = new AnimeModel(new Long(2));
        adaptation.setTitle("adaptation");
        adaptation.setEnglishTitle("adaptation english title");
        adaptation.setJapaneseTitle("adaptation japanese title");
        adaptation.setType("horror");
        List<AnimeModel> adaptations = new ArrayList<>();
        adaptations.add(adaptation);

        GenreModel genre = new GenreModel("anime");
        List<GenreModel> genres = new ArrayList<GenreModel>();
        genres.add(genre);

        AnimeModel anime = new AnimeModel(new Long(2));
        anime.setTitle("title");
        anime.setEnglishTitle("english title");
        anime.setJapaneseTitle("japanese title");
        anime.setType("horror");
        anime.setGenres(genres);

        service.saveAnime(anime);
        service.delete(anime);

    }
}