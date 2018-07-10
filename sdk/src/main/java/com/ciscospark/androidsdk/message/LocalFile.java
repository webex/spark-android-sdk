package com.ciscospark.androidsdk.message;

import java.io.File;

/**
 * A data type represents a local file.
 * @since 1.4.0
 */
public class LocalFile {

    /**
     * A data type represents a local file thumbnail.
     * @since 1.4.0
     */
    public static class Thumbnail {
        public String path;
        public int width;
        public int height;
        public long size;
        public String mimeType;
    }

    public String path;
    public String name;
    public long size;
    public String mimeType;
    public MessageClient.ProgressHandler progressHandler;
    public Thumbnail thumbnail;

    public LocalFile(File file) {
        _file = file;
    }

    public File getFile() {
        return _file;
    }

    private File _file;
}
