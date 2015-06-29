package net.v4lproik.googlanime.client.mysql;

public enum DatabaseEnum {
    MYSQL(0);

    DatabaseEnum(int id) {
        this.id = id;
    }

    private int id;

    public static DatabaseEnum containsValue(String value) {
        try {
            return DatabaseEnum.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e){
            return null;
        }
    }
}
