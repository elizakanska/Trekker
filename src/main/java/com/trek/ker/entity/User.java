package com.trek.ker.entity;

import com.trek.ker.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "users", schema = "trekker")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

}
