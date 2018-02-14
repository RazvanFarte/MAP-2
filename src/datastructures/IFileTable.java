package datastructures;

import models.FileDescriptor;

public interface IFileTable extends IDictionary<Integer, FileDescriptor> {
        public Integer getNewFileIndex();
}
