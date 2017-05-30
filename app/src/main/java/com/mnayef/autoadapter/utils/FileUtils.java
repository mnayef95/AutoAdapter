package com.mnayef.autoadapter.utils;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mohamed Hamdan on 2017-May-30.
 * mohamed.nayef95@gmail.com
 */
public class FileUtils {

    private static String image = null;
    private static boolean isExist = false;

    public static String getFirstImage(String ext) {
        isExist = false;
        File directory = Environment.getExternalStorageDirectory();
        return getAllFilesOfDir(directory, ext);
    }

    private static String getAllFilesOfDir(File directory, String ext) {
        List<File> files = new ArrayList<>(Arrays.asList(directory.listFiles()));
        for (File file : files) {
            if (!isExist) {
                if (file != null) {
                    if (file.isDirectory()) {
                        getAllFilesOfDir(file, ext);
                    } else {
                        String name = file.getName();
                        if (name.contains(ext)) {
                            image = file.getAbsolutePath();
                            break;
                        }
                    }
                }
            }
        }
        return image;
    }
}
