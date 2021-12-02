package org.home.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_data", schema = "todolist", catalog = "test_hibernate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable(value = true)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)

public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String username;

    @Column(name = "userpassword", nullable = false, length = -1)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Category> categories;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Priority> priorities;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Activity activity;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Stat stat;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
