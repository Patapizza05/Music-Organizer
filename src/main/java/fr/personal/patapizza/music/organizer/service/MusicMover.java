package fr.personal.patapizza.music.organizer.service;

import fr.personal.patapizza.music.organizer.option.FilenameFormat;
import fr.personal.patapizza.music.organizer.util.MoUtils;
import fr.personal.patapizza.music.organizer.entities.Song;
import fr.personal.patapizza.music.organizer.option.TagEnum;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MusicMover {

    private final FilenameFormat destinationFormat;

    public MusicMover(TagEnum... tags) {
        this(new FilenameFormat(tags));
    }

    public MusicMover(FilenameFormat destinationFormat) {
        this.destinationFormat = destinationFormat;
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
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("[fr.personal.patapizza.music.organizer.service.MusicMover][Erreur pour la chanson : " + (song != null ? song.getFilename() : null) + "]");
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
