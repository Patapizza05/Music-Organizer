package fr.personal.patapizza.music.organizer.option;

public enum SongFormatEnum {

    MP3("mp3"), FLAC("flac"), WAV("wav"), M4A("m4a");

    private String name;
    private String extension;

    SongFormatEnum(String name) {
        this.name = name;
        this.extension = "." + name;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }
}
