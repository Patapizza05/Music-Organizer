package fr.personal.patapizza.music.organizer.entities;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Song extends Mp3File {

    private final String path;

    public Song(String path) throws InvalidDataException, IOException, UnsupportedTagException {
        super(path);
        this.path = path;
    }

    public String getArtist() {
        String artist = getArtist(getId3v2Tag());
        if (artist == null) {
            artist = getArtist(getId3v1Tag());
        }
        return artist;
    }

    public String getAlbum() {
        String album = getAlbum(getId3v2Tag());
        if (album == null) {
            album = getAlbum(getId3v1Tag());
        }
        return album;
    }

    public String getArtist(ID3v1 tagSystem) {
        if (tagSystem != null) {
            return tagSystem.getArtist();
        }
        return null;
    }

    public String getYear() {
        String year = getYear(getId3v2Tag());
        if (year == null) {
            year = getYear(getId3v1Tag());
        }
        if (year.length() != 4) {
            if (year.startsWith("1") || year.startsWith("2")) {
                year = year.substring(0, 4);
            }
        }
        return year;
    }

    private String getYear(ID3v1 tagSystem) {
        if (tagSystem != null) {
            return tagSystem.getYear();
        }
        return null;
    }

    private String getAlbum(ID3v1 tagSystem) {
        if (tagSystem != null) {
            return tagSystem.getAlbum();
        }
        return null;
    }

    public Path getPath() {
        return Paths.get(path);
    }

    public String getFilenameWithoutFolder() {
        String[] array = getFilename().split("[/\\\\]");
        return array[array.length - 1];
    }


}
