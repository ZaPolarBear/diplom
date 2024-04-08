package org.university.diplom.model;

import jakarta.persistence.*;
import lombok.*;
import org.university.diplom.constants.FunctionType;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "function_entity")
public class FunctionEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private FunctionType functionType;

    @Column(nullable = false)
    private String function;

    @Column(nullable = false)
    private UUID imageName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FunctionEntity functionEntity = (FunctionEntity) o;
        return Objects.equals(id, functionEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
