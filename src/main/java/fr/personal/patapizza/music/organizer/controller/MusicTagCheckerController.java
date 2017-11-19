package fr.personal.patapizza.music.organizer.controller;

import fr.personal.patapizza.music.organizer.entities.Song;
import fr.personal.patapizza.music.organizer.log.MusicLogger;
import fr.personal.patapizza.music.organizer.option.TagEnum;
import fr.personal.patapizza.music.organizer.service.MusicScannerService;
import fr.personal.patapizza.music.organizer.setting.Settings;
import fr.personal.patapizza.music.organizer.util.MoUtils;

import java.io.File;
import java.util.List;

public class MusicTagCheckerController {

    private File musicFolder;
    private boolean recursive;
    private List<TagEnum> necessaryTags;
    private MusicScannerService musicScannerService;
    private MusicLogger musicLogger = new MusicLogger();

    public MusicTagCheckerController(File musicFolder, boolean recursive, List<TagEnum> necessaryTags, MusicScannerService musicScannerService) {
        this.musicFolder = musicFolder;
        this.recursive = recursive;
        this.necessaryTags = necessaryTags;
        this.musicScannerService = musicScannerService;
    }

    public void checkTags() {
        checkTags(musicFolder);
    }

    private void checkTags(File folder) {
        List<Song> songs = musicScannerService.getSongs(folder, false);

        if (songs != null && !songs.isEmpty()) {
            if (songs.stream().allMatch(s -> s.isFilled(necessaryTags))) {
                if (createTagCheckedFile(folder)) {
                    musicLogger.success("MusicTagCheckerController", "checkTags", "Fichiers audios taggés correctement - dossier %s", folder.getPath());
                } else {
                    musicLogger.error(null, "MusicTagCheckerController", "checkTags", "Fichiers audios taggés correctement - Erreur lors de la création du fichier - dossier %s", folder.getPath());
                }
            } else {
                if (deleteTagCheckedFile(folder)) {
                    musicLogger.error(null, "MusicTagCheckerController", "checkTags", "Tags incorrects - dossier %s", folder.getPath());
                } else {
                    musicLogger.error(null, "MusicTagCheckerController", "checkTags", "Tags incorrects - Erreur lors de la suppression du fichier - dossier %s", folder.getPath());
                }
            }
        }

        checkSubFolderTags(folder);
    }

    private void checkSubFolderTags(File parentFolder) {
        if (recursive) {
            List<File> subFolders = musicScannerService.getSubFolders(parentFolder);
            if (subFolders != null) {
                for (File folder : subFolders) {
                    checkTags(folder);
                }
            }
        }
    }

    private boolean createTagCheckedFile(File folder) {
        File f = buildTagCheckedFile(folder);
        try {
            return f.exists() || f.createNewFile();
        } catch (Exception ex) {
            return false;
        }
    }

    private boolean deleteTagCheckedFile(File folder) {
        File f = buildTagCheckedFile(folder);
        try {
            return !f.exists() || f.delete();
        } catch (Exception ex) {
            return false;
        }
    }

    private File buildTagCheckedFile(File folder) {
        String filePath = MoUtils.buildAbsoluteFileName(folder.getPath(), Settings.MusicTagChecker.FILE_TAG_CHECKED);
        // Use relative path for Unix systems
        return new File(filePath);
    }
}
