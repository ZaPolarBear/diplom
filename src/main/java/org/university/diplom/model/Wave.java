package org.university.diplom.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.cache.annotation.Cacheable;
import org.university.diplom.constants.FunctionType;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "waves")
public class Wave {
    @Id @GeneratedValue private UUID id;

    @Column(nullable = false)
    private XYSeriesCollection dataset;

    @Column(nullable = false)
    private FunctionType functionType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wave wave = (Wave) o;
        return Objects.equals(id, wave.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
