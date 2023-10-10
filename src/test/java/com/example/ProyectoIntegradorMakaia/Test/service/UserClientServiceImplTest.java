package com.example.ProyectoIntegradorMakaia.Test.service;

import com.example.ProyectoIntegradorMakaia.Repositories.UserClientRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UserClientServiceImplTest {

    @Mock
    private UserClientRepository userClientRepository;

}