package fr.personal.patapizza.music.organizer.log;

public class MusicLogger extends MusicStat {

    public void success(String clazz, String method, String comment, Object... args) {
        log("Success", clazz, method, comment, args);
        this.incrementSuccess();
    }

    public void error(Exception ex, String clazz, String method, String comment, Object... args) {
        log("Error", clazz, method, comment, args);
        this.incrementError();
    }

    private void log(String type, String clazz, String method, String comment, Object[] args) {
        if (args != null && args.length > 0) {
            comment = String.format(comment, args);
        }
        log(type, clazz, method, comment);
    }

    private void log(String type, String clazz, String method, String comment) {
        System.out.println(String.format("[%s][%s][%s][%s]", type, clazz, method, comment));
    }
}