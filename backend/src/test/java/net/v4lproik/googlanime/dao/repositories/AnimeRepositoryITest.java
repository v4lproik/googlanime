package net.v4lproik.googlanime.dao.repositories;

import net.v4lproik.googlanime.client.mysql.DatabaseTestConfiguration;
import net.v4lproik.googlanime.client.mysql.SqlDatabaseInitializer;
import net.v4lproik.googlanime.dao.api.AnimeDao;
import net.v4lproik.googlanime.dao.api.MangaDao;
import net.v4lproik.googlanime.service.api.AnimeServiceWrite;
import net.v4lproik.googlanime.service.api.entities.AnimeModel;
import net.v4lproik.googlanime.service.api.utils.TransformAnimeMapper;
import net.v4lproik.googlanime.service.api.utils.TransformMangaMapper;
import com.github.v4lproik.myanimelist.api.impl.AnimeMangaInformation;
import com.github.v4lproik.myanimelist.api.models.TypeEnum;
import com.github.v4lproik.myanimelist.entities.Anime;
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
import java.io.IOException;

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
    private AnimeMangaInformation myAnimeList;

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

//    @Test
//    public void test_saveAnimeWithDependencies_shouldBeInsertedWithItsDependencies() throws Exception{
//        // Given
//        ImportOptions options = new ImportOptions(2904, "anime", true);
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
//
//                        if (mangaRes.getId() != null){
//
//                            if (mangaDao.findById(mangaRes.getId()) == null){
//                                AnimeIdModel entryId = mangaMapper.transformEntryToEntryId(mangaRes);
//                                mangaDao.save(entryId);
//                            }
//
//                            mangaDao.saveOrUpdate(mangaRes);
//                        }
//                    }
//                    break;
//
//                case ANIME:
//                    AnimeModel animeRes = animeMapper.transformMyAnimeListAnimeDependencyToDAO((MyAnimeListAnimeDependency) entry);
//
//                    if (animeRes.getTitle() != null) {
//
//                        if (animeRes.getId() != null){
//
//                            if (animeDao.findById(animeRes.getId()) == null){
//                                AnimeIdModel entryId = animeMapper.transformEntryToEntryId(animeRes);
//                                animeDao.save(entryId);
//                            }
//
//                            animeDao.saveOrUpdate(animeRes);
//                        }
//                    }
//                    break;
//
//                default:
//                    throw new IllegalArgumentException();
//            }
//        }
//    }

    @Test
    public void test_saveAnime_shouldBeInserted() throws Exception {

        // Given
        AnimeModel response = getAnime();

        // If more details needed
//        final Integer idCharacter = response.getCharacters().get(2).getId();
//        final MyAnimeListCharacter character = response.getCharacters().get(2);
//        final String urlCharacter = myAnimeList.createCharacterURL(idCharacter);
//        input = new File("src/test/resource/lelouch-lamperouge.character");
//        doc = Jsoup.parse(input, "UTF-8", url);
//
//        myAnimeList.scrapCharacter(doc, urlCharacter, character);
//
//        AnimeModel anime = animeMapper.transformMyAnimeListAnimeToDAO((MyAnimeListAnime) response);

        animeDao.saveOrUpdate(response);
    }

    @Test
    public void test_saveAnimeTwice_shouldBeUpdated() throws Exception {

        // Given
        AnimeModel animeRes = getAnime();

        // When
        animeDao.saveOrUpdate(animeRes);
        animeRes.setTitle(animeRes.getTitle() + "_UPDATED");
        animeDao.saveOrUpdate(animeRes);

        AnimeModel animeFind = animeDao.findById(animeRes.getId());

        String title = animeRes.getTitle();

        // Then
        assertEquals(title, animeFind.getTitle());

    }

    @Test
    public void deleteAnime_shouldBeOK() throws Exception{

        // Given
        AnimeModel response = getAnime();


        // If more details needed
//        final Integer idCharacter = response.getCharacters().get(2).getId();
//        final MyAnimeListCharacter character = response.getCharacters().get(2);
//        final String urlCharacter = myAnimeList.createCharacterURL(idCharacter);
//        input = new File("src/test/resource/lelouch-lamperouge.character");
//        doc = Jsoup.parse(input, "UTF-8", url);
//
//        response.getCharacters().set(2, myAnimeList.scrapCharacter(doc, urlCharacter, character));
//
//        AnimeModel anime = animeMapper.transformMyAnimeListAnimeToDAO((MyAnimeListAnime) response);

        animeDao.saveOrUpdate(response);

        animeDao.deleteById(response.getId());
    }

    private AnimeModel getAnime() throws IOException{
        // Given
        TypeEnum type = TypeEnum.ANIME;
        Integer id = 2904;
        String url = myAnimeList.createEntryURL(id, type);

        Document doc = null;
        File input = new File("src/test/resource/code-geass-r2.anime");
        doc = Jsoup.parse(input, "UTF-8", url);

        doReturn(doc).when(myAnimeList).getResultFromJSoup(url, type.toString());

        return animeMapper.transformMyAnimeListAnimeToDAO((Anime) myAnimeList.crawl(id, type));
    }
}