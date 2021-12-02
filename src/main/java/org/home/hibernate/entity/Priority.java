package org.home.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "priority", schema = "todolist", catalog = "test_hibernate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable(value = true)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)

public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String color;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Override
    public String toString() {
        return "Priority{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", color='" + color + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Priority priority = (Priority) o;
        return id.equals(priority.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}