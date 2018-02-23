package models;

import java.io.BufferedReader;

public class FileDescriptor {

    private String filename;
    private BufferedReader reader;

    public FileDescriptor(String filename, BufferedReader reader) {
        this.filename = filename;

        //TODO We assume that a file can only be a text file that contains only non-zero positive integers, one integer per line
        this.reader = reader;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public String toString() {
        return "FileDescriptor{" +
                "filename='" + filename + '\'' +
                '}';
    }
}
