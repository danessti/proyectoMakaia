package com.example.ProyectoIntegradorMakaia.Models;

import com.example.ProyectoIntegradorMakaia.Utils.RoleName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false)
    private RoleName roleName;

    @OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<UserClient> userClients =new ArrayList<>();

}