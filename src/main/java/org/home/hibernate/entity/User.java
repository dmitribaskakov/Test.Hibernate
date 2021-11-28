package org.home.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_data", schema = "todolist", catalog = "test_hibernate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, length = -1)
    private String email;

    @Column(name = "username", nullable = false, length = -1)
    private String username;

    @Column(name = "userpassword", nullable = false, length = -1)
    private String password;
}
