package nubish.utils;

public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    FIND("find"),
    UNKNOWN("");

    private final String keyword;

    Command(String keyword) {
        this.keyword = keyword;
    }

    public static Command fromKeyword(String keyword) {
        for (Command command : values()) {
            if (command.keyword.equals(keyword)) {
                return command;
            }
        }
        return UNKNOWN;
    }
}