package fr.personal.patapizza.music.organizer.main;

import fr.personal.patapizza.music.organizer.controller.MusicOrganizerController;
import fr.personal.patapizza.music.organizer.controller.MusicTagCheckerController;
import fr.personal.patapizza.music.organizer.log.MusicLogger;
import fr.personal.patapizza.music.organizer.option.FilenameFormat;
import fr.personal.patapizza.music.organizer.service.MusicScannerService;
import fr.personal.patapizza.music.organizer.setting.Settings;

import java.io.File;
import java.util.Arrays;

public class Main {

    private static File musicFolder = new File(Settings.MUSIC_PATH);

    private static MusicLogger musicLogger = new MusicLogger();

    private static MusicScannerService musicScannerService = new MusicScannerService(Arrays.asList(Settings.ALLOWED_AUDIO_FORMATS), musicLogger);

    public static void main(String[] args) {
        checkTags();
    }

    private static void organize() {
        MusicOrganizerController musicOrganizer = new MusicOrganizerController(
                musicFolder,
                Settings.MusicMover.RECURSIVE,
                new FilenameFormat(Settings.MusicMover.FILENAME_PATTERN_TAGS),
                musicScannerService,
                musicLogger);

        musicOrganizer.organize();
    }


    private static void checkTags() {
        MusicTagCheckerController musicTagChecker = new MusicTagCheckerController(
                musicFolder,
                Settings.MusicTagChecker.RECURSIVE,
                Arrays.asList(Settings.MusicTagChecker.NECESSARY_TAGS),
                musicScannerService,
                musicLogger
        );

        musicTagChecker.checkTags();
    }

}
