package fr.personal.patapizza.music.organizer.util;

import fr.personal.patapizza.music.organizer.setting.Settings;

public class MoUtils {

    public static boolean isEmpty(String album) {
        return !isNotEmpty(album);
    }

    public static boolean isNotEmpty(String text) {
        return text != null && !text.isEmpty();
    }

    public static String buildFolder(String rootFolder, String folder) {
        return addEndDelimiter(rootFolder) + addEndDelimiter(folder);
    }

    public static String buildAbsoluteFileName(String folder, String fileName) {
        return addEndDelimiter(folder) + fileName;
    }

    public static String addEndDelimiter(String folder) {
        if (!folder.endsWith("/") && !folder.endsWith("\\") && !folder.endsWith(Settings.FILE_SEPARATOR)) {
            folder += Settings.FILE_SEPARATOR;
        }
        return folder;
    }


}
