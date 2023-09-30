package com.example.ProyectoIntegradorMakaia.Models;


import com.example.ProyectoIntegradorMakaia.Utils.AuthorityName;
import com.example.ProyectoIntegradorMakaia.Utils.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName name;
}
