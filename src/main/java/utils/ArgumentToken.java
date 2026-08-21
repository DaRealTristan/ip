package utils;

public enum ArgumentToken {
    BY("/by"),
    FROM("/from"),
    TO("/to");

    private final String token;

    ArgumentToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}