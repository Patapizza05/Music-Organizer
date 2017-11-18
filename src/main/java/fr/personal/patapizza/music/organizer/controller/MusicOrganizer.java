package fr.personal.patapizza.music.organizer.controller;

import fr.personal.patapizza.music.organizer.option.FilenameFormat;
import fr.personal.patapizza.music.organizer.entities.Song;
import fr.personal.patapizza.music.organizer.option.SongFormatEnum;
import fr.personal.patapizza.music.organizer.service.MusicMover;
import fr.personal.patapizza.music.organizer.service.MusicScanner;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MusicOrganizer {

    private final File musicFolder;
    private final boolean recursive;
    private final MusicMover musicMover;
    private MusicScanner musicScanner;

    public MusicOrganizer(String folder, boolean recursive, FilenameFormat filenameFormat, SongFormatEnum... formats) {
        this(folder, recursive, filenameFormat, Arrays.asList(formats));
    }

    public MusicOrganizer(String folder, boolean recursive, FilenameFormat filenameFormat, List<SongFormatEnum> formats) {
        this.musicFolder = new File(folder);
        this.recursive = recursive;
        this.musicScanner = new MusicScanner(musicFolder, formats);
        this.musicMover = new MusicMover(filenameFormat);
    }

    public void organize() {
        List<Song> songs = musicScanner.getSongs(recursive);
        musicMover.moveSongs(songs, musicFolder);
    }

}
