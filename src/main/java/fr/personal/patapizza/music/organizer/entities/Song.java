package fr.personal.patapizza.music.organizer.entities;

import com.mpatric.mp3agic.*;
import fr.personal.patapizza.music.organizer.option.TagEnum;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Song extends Mp3File {

    private final String path;

    public Song(String path) throws InvalidDataException, IOException, UnsupportedTagException {
        super(path);
        this.path = path;
    }

    public String getArtist() {
        return getAttribute(this::getArtist);
    }

    public String getTitle() {
        return getAttribute(this::getTitle);
    }

    public String getAlbum() {
        return getAttribute(this::getAlbum);
    }

    public String getYear() {
        String year = getAttribute(this::getYear);
        if (year.length() != 4) {
            if (year.startsWith("1") || year.startsWith("2")) {
                year = year.substring(0, 4);
            }
        }
        return year;
    }

    public int getGenre() {
        return getAttribute(this::getGenre);
    }

    public String getComment() {
        return getAttribute(this::getComment);
    }

    public String getLyrics() {
        return getAttribute2(ID3v2::getLyrics);
    }

    public String getComposer() {
        return getAttribute2(ID3v2::getComposer);
    }

    public String getPublisher() {
        return getAttribute2(ID3v2::getPublisher);
    }

    public String getOriginalArtist() {
        return getAttribute2(ID3v2::getOriginalArtist);
    }

    public String getAlbumArtist() {
        return getAttribute2(ID3v2::getAlbumArtist);
    }

    public String getCopyright() {
        return getAttribute2(ID3v2::getCopyright);
    }

    public String getUrl() {
        return getAttribute2(ID3v2::getUrl);
    }

    public String getEncoder() {
        return getAttribute2(ID3v2::getEncoder);
    }

    public byte[] getAlbumImageData() {
        return getAttribute2(ID3v2::getAlbumImage);
    }

    public String getAlbumImageMimeType() {
        return getAttribute2(ID3v2::getAlbumImageMimeType);
    }

    public String getArtist(ID3v1 tagSystem) {
        return getAttribute(tagSystem, ID3v1::getArtist);
    }

    public String getTitle(ID3v1 tagSystem) {
        return getAttribute(tagSystem, ID3v1::getTitle);
    }

    public String getYear(ID3v1 tagSystem) {
        return getAttribute(tagSystem, ID3v1::getYear);
    }

    public String getAlbum(ID3v1 tagSystem) {
        return getAttribute(tagSystem, ID3v1::getAlbum);
    }

    public int getGenre(ID3v1 tagSystem) {
        return getAttribute(tagSystem, ID3v1::getGenre);
    }

    public String getComment(ID3v1 tagSystem) {
        return getAttribute(tagSystem, ID3v1::getComment);
    }

    private <T> T getAttribute(ID3v1 tagSystem, Function<ID3v1, T> getter) {
        if (tagSystem != null) {
            return getter.apply(tagSystem);
        } else {
            return getAttribute(getter);
        }
    }

    private <T> T getAttribute2(Function<ID3v2, T> getter) {
        try {
            ID3v2 tagSystem = getId3v2Tag();
            if (tagSystem != null) {
                return getter.apply(tagSystem);
            }
            return null;
        }
        catch(NullPointerException ex) {
            System.out.println(String.format("Erreur de la récupération d'un attribut pour la chanson %s", this.getFilename()));
            ex.printStackTrace();
        }
        return null;
    }

    private <T> T getAttribute(Function<ID3v1, T> getter) {
        try {
            T attribute = getter.apply(getId3v2Tag());
            if (attribute == null) {
                attribute = getter.apply(getId3v1Tag());
            }
            return attribute;
        }
        catch(NullPointerException ex) {
            System.out.println(String.format("Erreur de la récupération d'un attribut pour la chanson %s", this.getFilename()));
            ex.printStackTrace();
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

    public boolean isFilled(List<TagEnum> tags) {
        ID3v2 tagSystem = tags.stream().anyMatch(t -> t == TagEnum.ID3V2) ? getId3v2Tag() : null;
        tags = tags.stream().filter(t -> t != TagEnum.ID3V2).collect(Collectors.toList());
        if (tagSystem != null) {
            return isFilledId3v2(tags);
        }
        return tags.stream().allMatch(t -> t.hasAttribute(this));
    }


    private boolean isFilledId3v2(List<TagEnum> tags) {
        return tags.stream().allMatch(t -> t.hasAttribute(this, true));
    }

}
