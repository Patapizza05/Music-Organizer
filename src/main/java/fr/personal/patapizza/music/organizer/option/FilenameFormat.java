package fr.personal.patapizza.music.organizer.option;

import fr.personal.patapizza.music.organizer.util.MoUtils;
import fr.personal.patapizza.music.organizer.entities.Song;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilenameFormat {

    private static final String FORBIDDEN_CHARACTERS_REGEX = "[\\\\/:*?\"<>|]";

    private List<TagFormatEnum> tags;

    public FilenameFormat(TagFormatEnum... tags) {
        this.tags = tags != null ? Arrays.asList(tags) : new ArrayList<>(0);
    }

    public List<TagFormatEnum> getTags() {
        return tags;
    }

    public String buildDestinationFolderName(Song song) {
        StringBuilder builder = new StringBuilder();
        for(TagFormatEnum tag : tags) {
            String folder = tag.getAttributeFrom(song);
            if (MoUtils.isNotEmpty(folder)) {
                folder = folder.replaceAll(FORBIDDEN_CHARACTERS_REGEX, "_");
                builder.append(MoUtils.addEndDelimiter(folder));
            }
            else {
                return builder.toString();
            }
        }
        return builder.toString();
    }
}
