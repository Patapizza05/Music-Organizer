package fr.personal.patapizza.music.organizer.controller;

import fr.personal.patapizza.music.organizer.entities.Song;
import fr.personal.patapizza.music.organizer.option.FilenameFormat;
import fr.personal.patapizza.music.organizer.service.MusicMoverService;
import fr.personal.patapizza.music.organizer.service.MusicScannerService;

import java.io.File;
import java.util.List;

public class MusicOrganizerController {

    private final File musicFolder;
    private final boolean recursive;
    private final MusicMoverService musicMoverService;
    private MusicScannerService musicScannerService;

    public MusicOrganizerController(File folder, boolean recursive, FilenameFormat filenameFormat, MusicScannerService musicScannerService) {
        this.musicFolder = folder;
        this.recursive = recursive;
        this.musicMoverService = new MusicMoverService(filenameFormat);
        this.musicScannerService = musicScannerService;
    }

    public void organize() {
        List<Song> songs = musicScannerService.getSongs(musicFolder, recursive);
        musicMoverService.moveSongs(songs, musicFolder);
    }

}
