package com.example.ProyectoIntegradorMakaia.Test.controller;


import com.example.ProyectoIntegradorMakaia.Models.user.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@TestMethodOrder(MethodOrderer.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserRepository userService;

    @InjectMocks
    private UserRepository userController;

}
