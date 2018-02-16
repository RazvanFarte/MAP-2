package models.commands;

public class ExitCommand extends AbstractCommand {

    public ExitCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
