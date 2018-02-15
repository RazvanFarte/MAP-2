package models.statements;

import datastructures.exceptions.DuplicateFileDescriptor;
import models.FileDescriptor;
import models.statements.exceptions.StatementException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFileStatement implements IStatement {

    private String fileDescriptorVariable;
    private String fileName;

    public OpenRFileStatement(String fileDescriptorVariable, String fileName) {
        this.fileDescriptorVariable = fileDescriptorVariable;
        this.fileName = fileName;
    }

    public String getFileDescriptorVariable() {
        return fileDescriptorVariable;
    }

    public void setFileDescriptorVariable(String fileDescriptorVariable) {
        this.fileDescriptorVariable = fileDescriptorVariable;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {

        for(FileDescriptor fileDescriptor: programState.getFileDescriptors().values())
            if(this.fileName.equals(fileDescriptor.getFilename()))
                throw new DuplicateFileDescriptor("File already opened");

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(this.fileName));
        } catch (FileNotFoundException e) {
            throw new StatementException("File not found");
        }

        FileDescriptor currentFile = new FileDescriptor(this.fileDescriptorVariable, reader);
        Integer fileIndex = programState.getFileDescriptors().getNewFileIndex();

        programState.getFileDescriptors().put(fileIndex, currentFile);

        programState.getSymbolTable().put(this.fileDescriptorVariable, fileIndex);

        return programState;
    }


}
