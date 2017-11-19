package fr.personal.patapizza.music.organizer.log;

import fr.personal.patapizza.music.organizer.setting.Settings;

public class MusicLogger extends MusicStat {

    private boolean logSuccess;
    private boolean logWarning;
    private boolean logError;

    public MusicLogger() {
        this(Settings.MusicLogger.VERBOSE_SUCCESS, Settings.MusicLogger.VERBOSE_WARNING, Settings.MusicLogger.VERBOSE_ERROR);
    }

    public MusicLogger(boolean logSuccess, boolean logWarning, boolean logError) {
        this.logSuccess = logSuccess;
        this.logWarning = logWarning;
        this.logError = logError;
    }

    public void success(String clazz, String method, String comment, Object... args) {
        if (logSuccess) {
            log("Success", clazz, method, comment, args);
        }
        this.incrementSuccess();
    }

    public void error(Exception ex, String clazz, String method, String comment, Object... args) {
        if (logError) {
            log("Error", clazz, method, comment, args);
        }
        this.incrementError();
    }

    private void log(String type, String clazz, String method, String comment, Object[] args) {
        if (args != null && args.length > 0) {
            comment = String.format(comment, args);
        }
        log(type, clazz, method, comment);
    }

    private void log(String type, String clazz, String method, String comment) {
        System.out.println(String.format("[%s]\t[%s]\t[%s]\t[%s]", type, clazz, method, comment));
    }

    public void logResult() {
        System.out.println("-------------- Statistics --------------");
        System.out.println(String.format("Success: %s", getSuccess()));
        System.out.println(String.format("Warning: %s", getWarning()));
        System.out.println(String.format("Error: %s", getError()));
        System.out.println("------------------------- --------------");
    }
}