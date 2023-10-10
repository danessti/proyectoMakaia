package com.example.ProyectoIntegradorMakaia.Test.controller;

import com.example.ProyectoIntegradorMakaia.Repositories.UserClientRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@TestMethodOrder(MethodOrderer.class)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UserClientControllerTest {

    @Mock
    private UserClientRepository userService;

    @InjectMocks
    private UserClientRepository userController;

}