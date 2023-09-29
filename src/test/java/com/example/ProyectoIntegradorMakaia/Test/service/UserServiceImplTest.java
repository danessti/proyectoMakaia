package com.example.ProyectoIntegradorMakaia.Test.service;


import com.example.ProyectoIntegradorMakaia.Repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    /*
    @Mock
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp(){
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void create_user(){
        UserDto userInput = new UserDto("usr1","sebas@hotmail.com","2323");
        User userMock = new User(userInput);

        Mockito.when(userRepository.create(Mockito.any(UserDto.class))).thenReturn(userMock);

        UserDto userInput2 = new UserDto("user2","sebas@hotmail.com","sfgsg");

        User userResult = userService.create(userInput2);


        Assertions.assertTrue(userResult.getEmail().equals("sebas@hotmail.com"));


    }*/

}
