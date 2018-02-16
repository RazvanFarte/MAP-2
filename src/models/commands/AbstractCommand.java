package models.commands;

public abstract class AbstractCommand {

    private String key;
    private String description;

    public AbstractCommand(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public abstract void execute();
}
