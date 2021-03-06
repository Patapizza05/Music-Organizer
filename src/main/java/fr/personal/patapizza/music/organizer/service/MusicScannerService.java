package fr.personal.patapizza.music.organizer.service;

import fr.personal.patapizza.music.organizer.entities.Song;
import fr.personal.patapizza.music.organizer.log.MusicLogger;
import fr.personal.patapizza.music.organizer.option.SongFormatEnum;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MusicScannerService {

    private final List<SongFormatEnum> formats;
    private final MusicLogger musicLogger;

    public MusicScannerService(List<SongFormatEnum> formats, MusicLogger musicLogger) {
        this.formats = formats;
        this.musicLogger = musicLogger;
    }

    public List<Song> getSongs(File folder, boolean recursive) {
        List<Song> songs = new LinkedList<>();
        File[] items = folder.listFiles();
        if (items != null) {
            for (File item : items) {
                if (isMusicFile(item)) {
                    try {
                        Song song = toSong(item);
                        if (song != null) {
                            songs.add(song);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (recursive && item.isDirectory()) {
                    songs.addAll(getSongs(item, recursive));
                }
            }
        }
        return songs;
    }

    public List<File> getSubFolders(File folder) {
        if (folder != null) {
            File[] items = folder.listFiles();
            if (items != null) {
                return Arrays.stream(items).filter(File::isDirectory).collect(Collectors.toList());
            }
        }
        return null;
    }

    private boolean isMusicFile(File file) {
        return file.isFile() && formats.stream().anyMatch(format -> file.getPath().endsWith(format.getExtension()));
    }

    private Song toSong(File file) {
        try {
            return new Song(file.getPath());
        }
        catch(Exception ex) {
            musicLogger.error(ex,"MusicScannerService", "toSong", "%s n'est pas un fichier audio", file.getPath());
        }
        return null;
    }
}
