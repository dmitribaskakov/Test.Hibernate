package org.home.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "stat", schema = "todolist", catalog = "test_hibernate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable(value = true)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)

public class Stat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "completed_total", updatable = false)
    private Long completedTotal;

    @Column(name = "uncompleted_total", updatable = false)
    private Long uncompletedTotal;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Override
    public String toString() {
        return "Stat{" +
                "id=" + id +
                ", completedTotal=" + completedTotal +
                ", uncompletedTotal=" + uncompletedTotal +
                ", user=" + user +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stat stat = (Stat) o;
        return id.equals(stat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}