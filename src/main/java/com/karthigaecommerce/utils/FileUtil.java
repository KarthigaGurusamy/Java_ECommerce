package com.karthigaecommerce.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {

    private static File credentialsFile;

    public static File getCredentialsFile()
    {
        if(credentialsFile==null)
        {
            credentialsFile = new File("src/main/java/com/karthigaecommerce/asserts/credentials.csv");
        }

        return credentialsFile;
    }

    public static boolean getCredentialsFolder(final String path)
    {
        try {
            if(Files.list(Path.of(path)).count()>0)
            {
                return true;
            }
            else
            {
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

