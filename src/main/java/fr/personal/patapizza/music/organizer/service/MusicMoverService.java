package fr.personal.patapizza.music.organizer.service;

import fr.personal.patapizza.music.organizer.entities.Song;
import fr.personal.patapizza.music.organizer.log.MusicLogger;
import fr.personal.patapizza.music.organizer.option.FilenameFormat;
import fr.personal.patapizza.music.organizer.util.MoUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MusicMoverService {

    private final FilenameFormat destinationFormat;
    private MusicLogger musicLogger;

    public MusicMoverService(FilenameFormat destinationFormat, MusicLogger musicLogger) {
        this.destinationFormat = destinationFormat;
        this.musicLogger = musicLogger;
    }

    public void moveSongs(List<Song> songs, File destinationFolder) {
        for(Song song : songs) {
            moveSong(song, destinationFolder);
        }
    }

    public void moveSong(Song song, File destinationFolder) {
        try {
            String songFolder = destinationFormat.buildDestinationFolderName(song);
            if (MoUtils.isNotEmpty(songFolder)) {
                String destFolder = MoUtils.buildFolder(destinationFolder.getPath(), songFolder);
                createFolders(destFolder);
                String destFile = MoUtils.buildAbsoluteFileName(destFolder, song.getFilenameWithoutFolder());
                move(song, destFile);
                musicLogger.success("MusicMoverService", "moveSong", "%s", song.getFilename());
            }
        } catch(Exception ex) {
            musicLogger.error(ex, "MusicMoverService", "moveSong", "%s", song != null ? song.getFilename() : null);
        }
    }

    private void move(Song song, String destFile) throws IOException {
        System.out.println(String.format("[%s] -> [%s]", song.getFilename(), destFile));
        move(song.getPath(), Paths.get(destFile));
    }

    private void move(Path sourcePath, Path destPath) throws IOException {
            Files.move(sourcePath, destPath);
    }

    private boolean createFolders(String newFolder) {
        return new File(newFolder).mkdirs();
    }

}
