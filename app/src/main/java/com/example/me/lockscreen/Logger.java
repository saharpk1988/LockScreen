package com.example.me.lockscreen;

import android.os.Environment;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public final class Logger {

    private Logger() {}


    public static void writeFile(String text) {

        File targetPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File logFile = new File(targetPath.getAbsolutePath() + "/", "logger.txt");
        if(!logFile.exists()) {
            try{
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter buffer = new BufferedWriter(new FileWriter(logFile, true));
            buffer.append(text);
            buffer.newLine();
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
