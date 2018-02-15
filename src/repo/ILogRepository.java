package repo;

import models.statements.ProgramState;

import java.io.IOException;

public interface ILogRepository extends IRepository<ProgramState> {
    public void logProgramStates() throws IOException;
}

