package net.v4lproik.googlanime.dao.repositories;

import net.v4lproik.googlanime.dao.api.MangaDao;
import net.v4lproik.googlanime.service.api.entities.*;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MangaRepository extends AbstractRepository implements MangaDao {

    private static final Logger log = LoggerFactory.getLogger(MangaRepository.class);
    public final SessionFactory sessionFactory;

    @Autowired
    public MangaRepository(final SessionFactory sessionFactory) {
        super(MangaModel.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long save(MangaModel manga) {
        Transaction tx = currentSession().beginTransaction();

        Object idSave = currentSession().save(manga);

        currentSession().flush();
        tx.commit();

        return (Long) idSave;
    }

    @Override
    public Long save(AnimeIdModel manga) {
        Transaction tx = currentSession().beginTransaction();

        Object idSave = currentSession().save(manga);

        currentSession().flush();
        tx.commit();

        return (Long) idSave;
    }

    @Override
    public void saveOrUpdate(MangaModel manga) {
        Transaction tx;
        long id = manga.getId();

        // -----------------------------------------------------------------------------------------
        // Save ManyToMany/OneToMany Entities

        tx=currentSession().beginTransaction();

        if (manga.getAuthors() != null) {
            for (AuthorModel author : manga.getAuthors()) {
                Integer idAuthor = author.getId();

                if (author.getId() != null) {
                    currentSession().update(author);
                } else {
                    Map<String, String> conditions = new HashMap<String, String>() {{
                        put("firstName", author.getFirstName());
                        put("lastName", author.getLastName());
                    }};

                    AuthorModel authorDB = (AuthorModel) getBySimpleCondition(currentSession(), AuthorModel.class, conditions);

                    if (authorDB == null) {
                        idAuthor = (Integer) currentSession().save(author);
                        author.setId(idAuthor);
                    } else {
                        author.setId(authorDB.getId());
                    }
                }
            }
        }

        if (manga.getCharacters() != null) {
            for (CharacterModel character : manga.getCharacters()) {
                Integer idCharacter = character.getId();

                if (character.getId() != null) {
                    currentSession().update(character);
                } else {
                    Map<String, String> conditions = new HashMap<String, String>() {{
                        put("firstName", character.getFirstName());
                        put("lastName", character.getLastName());
                    }};

                    CharacterModel characterDB = (CharacterModel) getBySimpleCondition(currentSession(), CharacterModel.class, conditions);

                    if (characterDB == null) {
                        idCharacter = (Integer) currentSession().save(character);
                        character.setId(idCharacter);
                    } else {
                        character.setId(characterDB.getId());
                    }
                }
            }
        }

        if (manga.getGenres() != null) {
            for (GenreModel genre : manga.getGenres()) {
                Integer idGenre = genre.getId();

                if (genre.getId() != null) {
                    currentSession().update(genre);
                } else {
                    GenreModel genreDB = (GenreModel) getBySimpleCondition(currentSession(), GenreModel.class, "name", genre.getName());
                    if (genreDB == null) {
                        idGenre = (Integer) currentSession().save(genre);
                        genre.setId(idGenre);
                    } else {
                        genre.setId(genreDB.getId());
                    }
                }
            }
        }

        if (manga.getTags() != null) {
            for (TagModel tag : manga.getTags()) {
                Integer idTag = tag.getId();

                if (tag.getId() != null) {
                    currentSession().update(tag);
                } else {
                    TagModel tagDB = (TagModel) getBySimpleCondition(currentSession(), TagModel.class, "name", tag.getName());
                    if (tagDB == null) {
                        idTag = (Integer) currentSession().save(tag);
                        tag.setId(idTag);
                    } else {
                        tag.setId(tagDB.getId());
                    }
                }
            }
        }

        if (manga.getSynonyms() != null) {
            for (SynonymModel synonym : manga.getSynonyms()) {
                Integer idTag = synonym.getId();

                if (synonym.getId() != null) {
                    currentSession().update(synonym);
                } else {

                    Map<String, Object> conditions = new HashMap<String, Object>() {{
                        put("title", synonym.getTitle());
                        put("entry", synonym.getEntry());
                    }};
                    SynonymModel synonymDB = (SynonymModel) getBySimpleConditionObject(currentSession(), SynonymModel.class, conditions);
                    if (synonymDB == null) {
                        idTag = (Integer) currentSession().save(synonym);
                        synonym.setId(idTag);
                    } else {
                        synonym.setId(synonymDB.getId());
                    }
                }
            }
        }

        currentSession().flush();
        tx.commit();

        // -----------------------------------------------------------------------------------------
        // Save Manga

        tx=currentSession().beginTransaction();

        currentSession().saveOrUpdate(manga);

        currentSession().flush();
        tx.commit();


        // -----------------------------------------------------------------------------------------
        // Save Alternatives, SidesStories etc

        if (manga.getSequels() != null){
            for (AnimeIdModel sequel:manga.getSequels()){
                if (sequel.getId() != id){

                    if (findById(sequel.getId()) != null){

                        tx=currentSession().beginTransaction();

                        currentSession().update(sequel);

                        currentSession().flush();
                        tx.commit();
                    }else{

                        tx=currentSession().beginTransaction();

                        currentSession().merge(sequel);

                        currentSession().flush();
                        tx.commit();
                    }

                    tx=currentSession().beginTransaction();

                    currentSession().saveOrUpdate(new Sequels(id, sequel.getId()));

                    currentSession().flush();
                    tx.commit();
                }
            }
        }

        if (manga.getAlternativeVersions() != null){
            for (AnimeIdModel alter:manga.getAlternativeVersions()){
                if (alter.getId() != id) {

                    if (findById(alter.getId()) != null){

                        tx=currentSession().beginTransaction();

                        currentSession().update(alter);

                        currentSession().flush();
                        tx.commit();
                    }else{

                        tx=currentSession().beginTransaction();

                        currentSession().merge(alter);

                        currentSession().flush();
                        tx.commit();
                    }


                    tx=currentSession().beginTransaction();

                    currentSession().saveOrUpdate(new Sequels(id, alter.getId()));

                    currentSession().flush();
                    tx.commit();
                }
            }
        }

        if (manga.getPrequels() != null){
            for (AnimeIdModel prequel:manga.getPrequels()){
                if (prequel.getId() != id){

                    if (findById(prequel.getId()) != null){

                        tx=currentSession().beginTransaction();

                        currentSession().update(prequel);

                        currentSession().flush();
                        tx.commit();
                    }else{

                        tx=currentSession().beginTransaction();

                        currentSession().merge(prequel);

                        currentSession().flush();
                        tx.commit();
                    }


                    tx=currentSession().beginTransaction();

                    currentSession().saveOrUpdate(new Prequels(id, prequel.getId()));

                    currentSession().flush();
                    tx.commit();
                }
            }
        }

        if (manga.getSpinoff() != null){
            for (AnimeIdModel spinOff:manga.getSpinoff()){
                if (spinOff.getId() != id){

                    if (findById(spinOff.getId()) != null){

                        tx=currentSession().beginTransaction();

                        currentSession().update(spinOff);

                        currentSession().flush();
                        tx.commit();
                    }else{

                        tx=currentSession().beginTransaction();

                        currentSession().merge(spinOff);

                        currentSession().flush();
                        tx.commit();
                    }


                    tx=currentSession().beginTransaction();

                    currentSession().saveOrUpdate(new SpinOffs(id, spinOff.getId()));

                    currentSession().flush();
                    tx.commit();
                }
            }
        }

        if (manga.getSideStories() != null){
            for (AnimeIdModel sideStory:manga.getSideStories()){
                if (sideStory.getId() != id){

                    if (findById(sideStory.getId()) != null){

                        tx=currentSession().beginTransaction();

                        currentSession().update(sideStory);

                        currentSession().flush();
                        tx.commit();
                    }else{

                        tx=currentSession().beginTransaction();

                        currentSession().merge(sideStory);

                        currentSession().flush();
                        tx.commit();
                    }


                    tx=currentSession().beginTransaction();

                    currentSession().saveOrUpdate(new SideStories(id, sideStory.getId()));

                    currentSession().flush();
                    tx.commit();
                }
            }
        }

        if (manga.getOthers() != null){
            for (AnimeIdModel other:manga.getOthers()){
                if (other.getId() != id){

                    if (findById(other.getId()) != null){

                        tx=currentSession().beginTransaction();

                        currentSession().update(other);

                        currentSession().flush();
                        tx.commit();
                    }else{

                        tx=currentSession().beginTransaction();

                        currentSession().merge(other);

                        currentSession().flush();
                        tx.commit();
                    }


                    tx=currentSession().beginTransaction();

                    currentSession().saveOrUpdate(new Others(id, other.getId()));

                    currentSession().flush();
                    tx.commit();

                }
            }
        }

        if (manga.getSummaries() != null){
            for (AnimeIdModel summary:manga.getSummaries()){
                if (summary.getId() != id){

                    if (findById(summary.getId()) != null){

                        tx=currentSession().beginTransaction();

                        currentSession().update(summary);

                        currentSession().flush();
                        tx.commit();
                    }else{

                        tx=currentSession().beginTransaction();

                        currentSession().merge(summary);

                        currentSession().flush();
                        tx.commit();
                    }

                    tx=currentSession().beginTransaction();

                    currentSession().saveOrUpdate(new Summaries(id, summary.getId()));

                    currentSession().flush();
                    tx.commit();
                }
            }
        }

        if (manga.getAdaptations() != null){
            for (AnimeIdModel adaptation:manga.getAdaptations()) {
                if (adaptation.getId() != id){

                    if (findById(adaptation.getId()) != null){

                        tx=currentSession().beginTransaction();

                        currentSession().update(adaptation);

                        currentSession().flush();
                        tx.commit();

                    }else{

                        tx=currentSession().beginTransaction();

                        currentSession().merge(adaptation);

                        currentSession().flush();
                        tx.commit();
                    }



                    tx=currentSession().beginTransaction();

                    currentSession().saveOrUpdate(new Adaptations(id, adaptation.getId()));

                    currentSession().flush();
                    tx.commit();
                }
            }
        }


        // -----------------------------------------------------------------------------------------
        // Save ManyToMany Extra Columns

        tx=currentSession().beginTransaction();

        if (manga.getAuthors() != null) {
            for (AuthorModel author : manga.getAuthors()) {
                Integer idAuthor = author.getId();

                if (author.getJobs() != null){
                    for (String job:author.getJobs()){
                        currentSession().saveOrUpdate(new AnimeJobAuthor(id, idAuthor, job));
                    }
                }
            }
        }

        if (manga.getCharacters() != null) {
            for (CharacterModel character : manga.getCharacters()) {
                Integer idCharacter = character.getId();

                if (character.getRole() != null){
                    currentSession().saveOrUpdate(new AnimeRoleCharacter(id, idCharacter, character.getRole()));
                }
            }
        }

        currentSession().flush();
        tx.commit();
    }

    @Override
    public MangaModel findById(Long id){

        Transaction tx=currentSession().beginTransaction();
        Criteria criteria = currentSession().createCriteria(MangaModel.class);
        criteria.add(Restrictions.eq("id", id));
        MangaModel mangaRes = (MangaModel) criteria.uniqueResult();
        tx.commit();

        return mangaRes;
    }

    @Override
    public MangaModel find(MangaModel manga){
        final Long id = manga.getId();
        final String type = manga.getType();
        final String serialization = manga.getSerialization();
        final String title = manga.getTitle();

        Transaction tx=currentSession().beginTransaction();
        Criteria criteria = currentSession().createCriteria(AnimeModel.class);
        if (id != null) criteria.add(Restrictions.eq("id", id));
        if (type != null) criteria.add(Restrictions.eq("type", type));
        if (serialization != null) criteria.add(Restrictions.eq("serialization", serialization));
        if (title != null) criteria.add(Restrictions.eq("showType", serialization));

        MangaModel mangaRes = (MangaModel) criteria.uniqueResult();
        tx.commit();

        return mangaRes;
    }

    @Override
    public void delete(MangaModel manga) {
        Transaction tx=currentSession().beginTransaction();

        currentSession().delete(manga);

        currentSession().flush();
        tx.commit();
    }

    @Override
    public void deleteById(Long id) {
        Transaction tx=currentSession().beginTransaction();

        try{
            MangaModel manga = (MangaModel)currentSession().load(MangaModel.class, id);
            currentSession().delete(manga);

            currentSession().flush();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw ex;
        }
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

}

