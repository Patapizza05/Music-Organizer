package fr.personal.patapizza.music.organizer.service;

import fr.personal.patapizza.music.organizer.entities.Song;
import fr.personal.patapizza.music.organizer.option.SongFormatEnum;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class MusicScanner {

    private final File musicFolder;
    private final List<SongFormatEnum> formats;

    public MusicScanner(File musicFolder, List<SongFormatEnum> formats) {
        this.musicFolder = musicFolder;
        this.formats = formats;
    }

    public List<Song> getSongs(boolean recursive) {
        return getSongs(musicFolder, recursive);
    }

    private List<Song> getSongs(File folder, boolean recursive) {
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

    private boolean isMusicFile(File file) {
        return file.isFile() && formats.stream().anyMatch(format -> file.getPath().endsWith(format.getExtension()));
    }

    private Song toSong(File file) {
        try {
            return new Song(file.getPath());
        }
        catch(Exception ex) {
            System.out.println(String.format("[fr.personal.patapizza.music.organizer.service.MusicScanner][%s n'est pas un fichier audio]", file.getPath()));
            ex.printStackTrace();
        }
        return null;
    }
}
