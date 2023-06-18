package traverse;

public record FileDataDto(
		String name,
        String path,
        String created,
        String modified,
        DangerLevel dangerLevel,
        int dangerRate,
        String verifierMessage) {
}