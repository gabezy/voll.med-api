package med.voll.api.domain.user;

public record DetailUserDto(String id, String username) {
    public DetailUserDto(User user) {
        this(user.getId(), user.getUsername());
    }
}
