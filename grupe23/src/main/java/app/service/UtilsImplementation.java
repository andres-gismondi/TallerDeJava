package app.service;

public class UtilsImplementation {

    public final static String TOKEN = "12345";
    public final static String ADMIN = "admin";
    public final static String PERMISSON_DENIED = "No posee los permisos suficientes";
    public final static String ACCESS_DENIED = "Token de acceso incorrecto";
    public final static String SUCCESS = "Operacion Correcta";

    public static String[] parts(String word) {
        return word.split("-");
    }

    public static Long getIdFromAuthorizationToken(String word) {
        return Long.parseLong(parts(word)[0]);
    }


}
