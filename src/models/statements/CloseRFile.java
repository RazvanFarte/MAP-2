package models.statements;

import models.statements.exceptions.StatementException;

import java.io.IOException;

public class CloseRFile implements IStatement {

    private String fileDescriptor;

    public CloseRFile(String fileDescriptor) {
        this.fileDescriptor = fileDescriptor;
    }

    public String getFileDescriptor() {
        return fileDescriptor;
    }

    public void setFileDescriptor(String fileDescriptor) {
        this.fileDescriptor = fileDescriptor;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {

        if(!programState.getSymbolTable().containsKey(getFileDescriptor()))
            throw new StatementException("Variable " + getFileDescriptor()+ " does not exist");

        Integer fd = programState.getSymbolTable().get(getFileDescriptor());

        if(!programState.getFileDescriptors().containsKey(fd))
            throw new StatementException("No file descriptor with following id: " + fd);

        try {
            programState.getFileDescriptors().get(fd).getReader().close();
        } catch (IOException e) {
            throw new StatementException(e.getMessage());
        }

        programState.getFileDescriptors().remove(fd);

        return programState;
    }

    @Override
    public String toString() {
        return "CloseRFile{" +
                "fileDescriptor='" + fileDescriptor + '\'' +
                '}';
    }
}
