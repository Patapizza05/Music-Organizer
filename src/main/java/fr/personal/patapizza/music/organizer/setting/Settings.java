package fr.personal.patapizza.music.organizer.setting;

import fr.personal.patapizza.music.organizer.option.SongFormatEnum;
import fr.personal.patapizza.music.organizer.option.TagEnum;

public class Settings {

    public static final String MUSIC_PATH = "C:/Users/USERNAME/Music";

    /**
     * Destination filename pattern
     * For example :
     * Source :         [MUSIC_PATH]/example.mp3
     * Destination :    [MUSIC_PATH]/[Artist]/[Album]/example.mp3
     */
    public static final TagEnum[] FILENAME_PATTERN_TAGS = new TagEnum[]
            {
                    TagEnum.ARTIST,
                    TagEnum.ALBUM_YEAR
            };

    /**
     * Allowed song extensions
     */
    public static final SongFormatEnum[] ALLOWED_AUDIO_FORMATS = new SongFormatEnum[]
            {
                    SongFormatEnum.MP3,
                    SongFormatEnum.M4A,
                    SongFormatEnum.FLAC,
                    SongFormatEnum.WAV
            };

    /*
     * Warning : True : not tested
     */
    public static final boolean RECURSIVE = false;

}
