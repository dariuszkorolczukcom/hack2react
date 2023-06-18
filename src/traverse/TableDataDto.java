package traverse;

public record TableDataDto(
		String name,
        String path,
        String created,
        String modified,
        String dangerLevel,
        Integer dangerRate,
        String dangerMessage) {
    public TableDataDto(FileDataDto dto) {
        this(dto.name(), dto.path(), dto.created(), dto.modified(), dto.dangerLevel().toString(), dto.dangerRate(), dto.verifierMessage());
    }
}
