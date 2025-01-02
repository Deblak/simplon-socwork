package co.simplon.socwork.dtos;

public record AccountLogIn(String username, String password) {
    @Override
    public String toString() {
	return String.format("{username=%s, password=[PROTECTED]}", username);
    }

}
