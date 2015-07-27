package net.v4lproik.googlanime.service.api.common;

public enum EntryTypeEnum {
    MANGA,
    ANIME;

    public static EntryTypeEnum fromValue(String value){
        return EntryTypeEnum.valueOf(value.toUpperCase()) != null ? EntryTypeEnum.valueOf(value.toUpperCase()) : null;
    }
}