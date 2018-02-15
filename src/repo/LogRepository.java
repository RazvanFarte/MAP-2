package repo;

import models.statements.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogRepository extends MemoryRepository<ProgramState> implements ILogRepository {

    private String logFile;

    public LogRepository(String logFile) {
        super();
        this.logFile = logFile;
    }

    public String getLogFile() {
        return logFile;
    }

    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    @Override
    public void logProgramStates() throws IOException {
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(this.logFile, true)));

        printWriter.append(this.getCurrentEntity().toString());

        printWriter.close();
    }
}

