//package com.example.ProyectoIntegradorMakaia.Utils;
//
//import com.example.ProyectoIntegradorMakaia.Models.Authority;
//import com.example.ProyectoIntegradorMakaia.Models.user.User;
//import com.example.ProyectoIntegradorMakaia.Repositories.AuthorityRepository;
//import com.example.ProyectoIntegradorMakaia.Models.user.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class Runner implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//    private final AuthorityRepository authorityRepository;
//
//    public Runner(UserRepository userRepository, AuthorityRepository authorityRepository) {
//        this.userRepository = userRepository;
//        this.authorityRepository = authorityRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        if (this.authorityRepository.count() == 0) {
//            this.authorityRepository.saveAll(List.of(
//                    new Authority(AuthorityName.ADMIN),
//                    new Authority(AuthorityName.READ),
//                    new Authority(AuthorityName.WRITE)
//            ));
//
//            if (this.userRepository.count() == 0) {
//                var encoders = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//
//                this.userRepository.saveAll(List.of(
//                        new User("admin", encoders.encode("admin123"), List.of(this.authorityRepository.findByName(AuthorityName.ADMIN).get())),
//                        new User("read", encoders.encode("read123"), List.of(this.authorityRepository.findByName(AuthorityName.READ).get())),
//                        new User("write", encoders.encode("write123"), List.of(this.authorityRepository.findByName(AuthorityName.WRITE).get()))
//                ));
//            }
//
//        }
//
//    }
//}