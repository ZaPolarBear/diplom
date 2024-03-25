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
@Table(name = "mechanical_wave")
public class MechanicalWave {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private FunctionType functionType;

    @Column(nullable = false)
    private double amplitude;

    @Column(nullable = false)
    private double wavelength;

    @Column(nullable = false)
    private double step;

    @Column(nullable = false)
    private UUID imageName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MechanicalWave mechanicalWave = (MechanicalWave) o;
        return Objects.equals(id, mechanicalWave.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
