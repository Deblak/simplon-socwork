package co.simplon.socwork.dtos;

//public record AuthInfo(String token, AccountInfo accountInfo) {

public record AuthInfo(String token) {
//    @Override
//    public String toString() {
//	return String.format("{token=[PROTECTED]}, accountInfo=%s}", accountInfo);
//    }

    @Override
    public String toString() {
	return String.format("{token=[PROTECTED]}, accountInfo=%s}");
    }
}
