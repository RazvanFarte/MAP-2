package datastructures;

import models.FileDescriptor;

public class FileTable extends Dictionary<Integer, FileDescriptor> implements IFileTable{

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
}
