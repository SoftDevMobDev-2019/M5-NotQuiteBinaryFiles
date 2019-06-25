package au.edu.swin.sdmd.notquitebinaryfiles;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This stores the name of the file being used for storage, as well as abstracts out the putting and
 * getting of data.
 */

public class HistoryFile {
    static String filename = "decimal_history";

    HistoryFile() {

    }

    static void appendInput(Context context, String sDecimal) {
        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(sDecimal.getBytes());
            outputStream.write("\n".getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static FileInputStream getFile(Context context) {
        FileInputStream fileInput = null;
        try {
            fileInput = context.openFileInput(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileInput;
    }

    static List<String> getFileContents(FileInputStream fis) {
        List<String> numbers = new ArrayList<>();

        if(fis != null) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String line = br.readLine();
                while (line != null) {
                    numbers.add(line);
                    line = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Collections.reverse(numbers);
        return numbers;

    }

    static void deleteFile(Context context) {
        context.deleteFile(filename);
    }

}
