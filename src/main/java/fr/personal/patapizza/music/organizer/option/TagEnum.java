package fr.personal.patapizza.music.organizer.option;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import fr.personal.patapizza.music.organizer.entities.Song;

import java.util.function.Function;

public enum TagEnum {

    ID3V2(ID3v2.class, Mp3File::getId3v2Tag),

    //ID3v1
    ARTIST(String.class, Song::getArtist, s -> s.getArtist(s.getId3v2Tag())),
    TITLE(String.class, Song::getTitle, s -> s.getTitle(s.getId3v2Tag())),
    ALBUM(String.class, Song::getAlbum, s -> s.getAlbum(s.getId3v2Tag())),
    YEAR(String.class, Song::getYear, s -> s.getYear(s.getId3v2Tag())),
    GENRE(Integer.class, Song::getGenre, s -> s.getGenre(s.getId3v2Tag())),
    COMMENT(String.class, Song::getComment, s -> s.getComment(s.getId3v2Tag())),

    //ID3v2
    LYRICS(String.class, Song::getLyrics),
    COMPOSER(String.class, Song::getComposer),
    PUBLISHER(String.class, Song::getPublisher),
    ORIGINAL_ARTIST(String.class, Song::getOriginalArtist),
    ALBUM_ARTIST(String.class, Song::getAlbumArtist),
    COPYRIGHT(String.class, Song::getCopyright),
    URL(String.class, Song::getUrl),
    ENCODER(String.class, Song::getEncoder),
    ALBUM_IMAGE(String.class, Song::getAlbumImageData);

    private final Class clazz;
    private final Function<Song, Object> getter;
    private final Function<Song, Object> getterId3v2;

    TagEnum(Class clazz, Function<Song, Object> getter, Function<Song, Object> getterId3v2) {
        this.clazz = clazz;
        this.getter = getter;
        this.getterId3v2 = getterId3v2;
    }

    TagEnum(Class clazz, Function<Song, Object> getter) {
        this(clazz, getter, getter);
    }

    public Class getClazz() {
        return clazz;
    }

    public boolean hasAttribute(Song song) {
        return getter.apply(song) != null;
    }

    public boolean hasAttribute(Song song, boolean isId3v2) {
        if (!isId3v2) {
            return hasAttribute(song);
        }
        return getterId3v2.apply(song) != null;
    }

}
