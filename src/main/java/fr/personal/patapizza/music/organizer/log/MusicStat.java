package fr.personal.patapizza.music.organizer.log;

public class MusicStat {

    private int success = 0;
    private int warning = 0;
    private int error = 0;

    public int getSuccess() {
        return success;
    }

    public int getWarning() {
        return warning;
    }

    public int getError() {
        return error;
    }

    public void incrementSuccess() {
        success++;
    }

    public void incrementWarning() {
        warning++;
    }

    public void incrementError() {
        error++;
    }
}
