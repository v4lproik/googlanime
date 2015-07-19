package net.v4lproik.googlanime.service.api.myanimelist.models;

public class MyAnimeListEntryDependencyFactory {

    public MyAnimeListEntryDependency getEntity(String type, Integer id){

        MyAnimeListEntryDependency myAnimeListEntryDependency;

        EntryTypeEnum typeEnum = EntryTypeEnum.fromValue(type);

        if (typeEnum == null){
            throw new EnumConstantNotPresentException(EntryTypeEnum.class, type);
        }

        switch (typeEnum){
            case MANGA:
                myAnimeListEntryDependency = new MyAnimeListMangaDependency(id);
                break;

            case ANIME:
                myAnimeListEntryDependency = new MyAnimeListAnimeDependency(id);
                break;

            default:
                throw new IllegalArgumentException();
        }

        myAnimeListEntryDependency.setType(type);
        return myAnimeListEntryDependency;
    }
}
