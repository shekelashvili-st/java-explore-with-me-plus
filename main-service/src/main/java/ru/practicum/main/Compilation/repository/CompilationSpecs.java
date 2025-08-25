package ru.practicum.main.Compilation.repository;

import org.springframework.data.jpa.domain.Specification;
import ru.practicum.main.Compilation.entity.Compilation;

public class CompilationSpecs {

    public static Specification<Compilation> isPinned(boolean value) {
        return (root, query, builder) ->
                builder.equal(root.get("pinned"), value);
    }
}
