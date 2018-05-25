package spreadsheet_engine.core;

public interface Command {
    void runCommand(String[] token) throws Exception;
}
