package com.btanabe.adaptivewebscraper.utilities;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Created by Brian on 8/7/16.
 */
public class ClasspathToFileSystemResource {

    /**
     * I don't know how to get Hibernate to play nice with files in the classpath.  To get around this I'm copying that
     * classpath file to a temp file.  This smells slightly bad but until I find a better way of doing it, this method
     * will have to due.  Other ideas I came up with are:
     *
     * Creating the Hibernate "Context" in spring.  This context can have its URL set programatically (as well as other)
     * fields and can be autowired into the Application or DatabaseInterface class.
     *
     * @param resource
     * @return
     * @throws Exception
     */
    public static File classpathToTempFileSystemFile(Resource resource) throws Exception {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        File tempFile = File.createTempFile("file", ".tmp");
        FileUtils.write(tempFile, buffer.lines().collect(Collectors.joining("\n")));

        return tempFile;
    }
}
