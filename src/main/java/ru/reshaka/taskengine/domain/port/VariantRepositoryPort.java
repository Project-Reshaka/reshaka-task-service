package ru.reshaka.taskengine.domain.port;

import ru.reshaka.taskengine.domain.model.Variant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VariantRepositoryPort {
    Optional<Variant> findById(Long variantId);
    List<Variant> findBySubject(Long subjectId);
    Variant save(Variant variant);
    void delete(Long variantId);
}
