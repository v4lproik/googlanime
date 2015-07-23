package net.v4lproik.googlanime.dao.repositories;

import net.v4lproik.googlanime.dao.api.AnimeDao;
import net.v4lproik.googlanime.service.api.entities.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AnimeRepository extends AbstractRepositoryHelper implements AnimeDao{

    private static final Logger log = LoggerFactory.getLogger(AnimeRepository.class);
    public final SessionFactory sessionFactory;

    @Autowired
    public AnimeRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long save(AnimeModel anime) {
        Transaction tx = currentSession().beginTransaction();

        Object idSave = currentSession().save(anime);

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
    public void update(AnimeModel anime){
        Transaction tx=currentSession().beginTransaction();

        currentSession().update(anime);

        currentSession().flush();
        tx.commit();
    }

    @Override
    public void saveOrUpdate(AnimeModel anime) {
        Transaction tx;
        long id = anime.getId();

        // -----------------------------------------------------------------------------------------
        // Save ManyToMany/OneToMany Entities

        tx=currentSession().beginTransaction();

        if (anime.getAuthors() != null) {
            for (AuthorModel author : anime.getAuthors()) {
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

        if (anime.getCharacters() != null) {
            for (CharacterModel character : anime.getCharacters()) {
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

        if (anime.getProducers() != null) {
            for (ProducerModel producer : anime.getProducers()) {
                Integer idProducer = producer.getId();

                if (producer.getId() == null) {
                    ProducerModel producerDB = (ProducerModel) getBySimpleCondition(currentSession(), ProducerModel.class, "name", producer.getName());
                    if (producerDB == null) {
                        idProducer = (Integer) currentSession().save(producer);
                        producer.setId(idProducer);
                    } else {
                        producer.setId(producerDB.getId());
                    }
                }
            }
        }


        if (anime.getGenres() != null) {
            for (GenreModel genre : anime.getGenres()) {
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

        if (anime.getTags() != null) {
            for (TagModel tag : anime.getTags()) {
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

        if (anime.getSynonyms() != null) {
            for (SynonymModel synonym : anime.getSynonyms()) {
                Integer idSynonym = synonym.getId();

                if (synonym.getId() != null) {
                    currentSession().update(synonym);
                } else {

                    Map<String, Object> conditions = new HashMap<String, Object>() {{
                        put("title", synonym.getTitle());
                        put("entry", synonym.getEntry());
                    }};
                    SynonymModel synonymDB = (SynonymModel) getBySimpleConditionObject(currentSession(), SynonymModel.class, conditions);
                    if (synonymDB == null) {
                        idSynonym = (Integer) currentSession().save(synonym);
                        synonym.setId(idSynonym);
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

        currentSession().saveOrUpdate(anime);

        currentSession().flush();
        tx.commit();


        // -----------------------------------------------------------------------------------------
        // Save Alternatives, SidesStories etc

// -----------------------------------------------------------------------------------------
        // Save Alternatives, SidesStories etc

        if (anime.getSequels() != null){
            for (AnimeIdModel sequel:anime.getSequels()){
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

        if (anime.getAlternativeVersions() != null){
            for (AnimeIdModel alter:anime.getAlternativeVersions()){
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

        if (anime.getPrequels() != null){
            for (AnimeIdModel prequel:anime.getPrequels()){
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

        if (anime.getSpinoff() != null){
            for (AnimeIdModel spinOff:anime.getSpinoff()){
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

        if (anime.getSideStories() != null){
            for (AnimeIdModel sideStory:anime.getSideStories()){
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

        if (anime.getOthers() != null){
            for (AnimeIdModel other:anime.getOthers()){
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

        if (anime.getSummaries() != null){
            for (AnimeIdModel summary:anime.getSummaries()){
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

        if (anime.getAdaptations() != null){
            for (AnimeIdModel adaptation:anime.getAdaptations()) {
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

        if (anime.getAuthors() != null) {
            for (AuthorModel author : anime.getAuthors()) {
                Integer idAuthor = author.getId();

                if (author.getJobs() != null){
                    for (String job:author.getJobs()){
                        currentSession().saveOrUpdate(new AnimeJobAuthor(id, idAuthor, job));
                    }
                }
            }
        }

        if (anime.getCharacters() != null) {
            for (CharacterModel character : anime.getCharacters()) {
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
    public AnimeModel findById(Long id){

        Transaction tx=currentSession().beginTransaction();

        Criteria criteria = currentSession().createCriteria(AnimeModel.class);
        criteria.add(Restrictions.eq("id", id));
        AnimeModel animeRes = (AnimeModel) criteria.uniqueResult();

        currentSession().flush();
        tx.commit();

        return animeRes;
    }

    @Override
    public AnimeModel find(AnimeModel anime){
        final Long id = anime.getId();
        final String type = anime.getType();
        final String showType = anime.getShowType();
        final String title = anime.getTitle();

        Transaction tx=currentSession().beginTransaction();
        Criteria criteria = currentSession().createCriteria(AnimeModel.class);
        if (id != null) criteria.add(Restrictions.eq("id", id));
        if (type != null) criteria.add(Restrictions.eq("type", type));
        if (showType != null) criteria.add(Restrictions.eq("showType", showType));
        if (title != null) criteria.add(Restrictions.eq("title", title));

        AnimeModel animeRes = (AnimeModel) criteria.uniqueResult();
        tx.commit();

        return animeRes;
    }

    @Override
    public void delete(AnimeModel anime) {
        Transaction tx=currentSession().beginTransaction();

        currentSession().delete(anime);

        currentSession().flush();
        tx.commit();
    }

    @Override
    public void deleteById(Long id) {
        Transaction tx=currentSession().beginTransaction();

        try{
            AnimeModel anime = (AnimeModel)currentSession().load(AnimeModel.class, id);

            currentSession().delete(anime);

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

