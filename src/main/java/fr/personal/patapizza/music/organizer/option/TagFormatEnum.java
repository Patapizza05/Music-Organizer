package fr.personal.patapizza.music.organizer.option;

import fr.personal.patapizza.music.organizer.util.MoUtils;
import fr.personal.patapizza.music.organizer.entities.Song;

public enum TagFormatEnum {

    ARTIST, ALBUM, ALBUM_YEAR;

    public String getAttributeFrom(Song song) {
        switch (this) {
            case ARTIST:
                return song.getArtist();
            case ALBUM:
                return song.getAlbum();
            case ALBUM_YEAR:
                return formatAlbumYear(song);
            default:
                return null;
        }
    }

    private String formatAlbumYear(Song song) {
        return formatAlbumYear(song.getAlbum(), song.getYear());
    }

    private String formatAlbumYear(String album, String year) {
        if (MoUtils.isEmpty(album)) {
            return null;
        }
        if (MoUtils.isEmpty(year)) {
            return album;
        }
        return String.format("%s (%s)", album, year);
    }
}
