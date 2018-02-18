package models.statements;

import models.statements.exceptions.StatementException;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement {

    private String fileDescriptor;
    private String variableName;

    public ReadFile(String fileDescriptor, String variableName) {
        this.fileDescriptor = fileDescriptor;
        this.variableName = variableName;
    }

    public String getFileDescriptor() {
        return fileDescriptor;
    }

    public void setFileDescriptor(String fileDescriptor) {
        this.fileDescriptor = fileDescriptor;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {

        if(!programState.getSymbolTable().containsKey(getFileDescriptor()))
            throw new StatementException("Variable " + getFileDescriptor()+ " does not exist");

        Integer fd = programState.getSymbolTable().get(getFileDescriptor());

        if(!programState.getFileDescriptors().containsKey(fd))
            throw new StatementException("No file descriptor wit following id: " + fd);

        BufferedReader reader = programState.getFileDescriptors().get(fd).getReader();

        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new StatementException(e.getMessage());
        }
        int parsedValue;

        if(line.isEmpty())
            parsedValue = 0;
        else
            parsedValue = Integer.parseInt(line);

        programState.getSymbolTable().put(getVariableName(), parsedValue);

        return null;
    }

    @Override
    public String toString() {
        return "ReadFile{" +
                "fileDescriptor='" + fileDescriptor + '\'' +
                ", variableName='" + variableName + '\'' +
                '}';
    }
}
