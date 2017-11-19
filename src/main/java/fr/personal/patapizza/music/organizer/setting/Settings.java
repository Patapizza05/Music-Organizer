package fr.personal.patapizza.music.organizer.setting;

import fr.personal.patapizza.music.organizer.option.SongFormatEnum;
import fr.personal.patapizza.music.organizer.option.TagEnum;
import fr.personal.patapizza.music.organizer.option.TagFormatEnum;

import java.io.File;

public class Settings {

    public static final String MUSIC_PATH = SensitiveSettings.MUSIC_PATH;

    public static final String FILE_SEPARATOR = File.separator;
    /**
     * Allowed song extensions
     */
    public static final SongFormatEnum[] ALLOWED_AUDIO_FORMATS = new SongFormatEnum[]
            {
                    SongFormatEnum.MP3,
            };

    public static class MusicMover {
        /**
         * Destination filename pattern
         * For example :
         * Source :         [MUSIC_PATH]/example.mp3
         * Destination :    [MUSIC_PATH]/[Artist]/[Album]/example.mp3
         */
        public static final TagFormatEnum[] FILENAME_PATTERN_TAGS = new TagFormatEnum[]
                {
                        TagFormatEnum.ARTIST,
                        TagFormatEnum.ALBUM_YEAR
                };

        /**
         * Warning : True : not tested
         */
        public static final boolean RECURSIVE = false;
    }

    public static class MusicTagChecker {
        /**
         * Tags needed to pass the OK Test
         */
        public static final TagEnum[] NECESSARY_TAGS = new TagEnum[]{TagEnum.ID3V2, TagEnum.ARTIST, TagEnum.ALBUM, TagEnum.TITLE, TagEnum.ALBUM_IMAGE};

        /**
         * Test the sub-folders too
         */
        public static final boolean RECURSIVE = true;

        public static final String FILE_TAG_CHECKED = ".mo-tag-checked";
        public static final String FILE_TAG_INCORRECT = "mo-tag-incorrect";
    }

    public static class MusicLogger {
        public static boolean VERBOSE_SUCCESS = false;
        public static boolean VERBOSE_WARNING = false;
        public static boolean VERBOSE_ERROR = true;
    }


}
