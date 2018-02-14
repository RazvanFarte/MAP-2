package datastructures;

import datastructures.exceptions.DuplicateFileDescriptor;
import models.FileDescriptor;

import java.util.Map;

public class FileTable extends Dictionary<Integer, FileDescriptor> implements IFileTable {

    private static Integer fileIndexCounter = new Integer(0);

    public FileTable() {
        super();
    }

    @Override
    public Integer getNewFileIndex() {
        Integer result = fileIndexCounter;
        fileIndexCounter++;
        return result;
    }

    @Override
    public FileDescriptor put(Integer key, FileDescriptor value) {
        if(super.containsKey(key))
            throw new DuplicateFileDescriptor("File with identifier" + key + "already exists");
        return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends FileDescriptor> m) {
        super.putAll(m);
    }
}
