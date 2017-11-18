package fr.personal.patapizza.music.organizer.main;

import fr.personal.patapizza.music.organizer.controller.MusicOrganizer;
import fr.personal.patapizza.music.organizer.option.FilenameFormat;
import fr.personal.patapizza.music.organizer.setting.Settings;

public class Main {


    public static void main(String[] args) {

    }

    private static void organize() {
        MusicOrganizer musicOrganizer = new MusicOrganizer(
                Settings.MUSIC_PATH,
                Settings.RECURSIVE,
                new FilenameFormat(Settings.FILENAME_PATTERN_TAGS),
                Settings.ALLOWED_AUDIO_FORMATS);

        musicOrganizer.organize();
    }

}
