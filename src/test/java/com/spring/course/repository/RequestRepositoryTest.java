/*
package com.spring.course.repository;

import com.spring.course.domain.User;
import com.spring.course.domain.enums.Role;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void AsaveTest(){
        User user = User.builder()
                .id(null)
                .name("Felipe")
                .email("femigueldepaula@gmail.com")
                .password("123")
                .role(Role.ADMINISTRATOR)
                .requests(null)
                .stages(null)
                .build();

        User createduser = userRepository.save(user);

        assertThat(createduser.getId()).isEqualTo(1L);
    }

    @Test
    public void updateTest(){
        User user = User.builder()
                .id(1L)
                .name("Felipe Miguel")
                .email("femigueldepaula@gmail.com")
                .password("123")
                .role(Role.ADMINISTRATOR)
                .requests(null)
                .stages(null)
                .build();

        User updatedUser = userRepository.save(user);

        assertThat(updatedUser.getName()).isEqualTo("Felipe Miguel");
    }

    @Test
    public void getByIdTest(){
        Optional<User> result = userRepository.findById(1L);
        User user = result.get();

        assertThat(user.getPassword()).isEqualTo("123");
    }

    @Test
    public void listTest(){
        List<User> users = userRepository.findAll();

        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    public void loginTest(){
        Optional<User> result = userRepository.login("femigueldepaula@gmail.com", "123");
        User loggedUser = result.get();

        assertThat(loggedUser.getId()).isEqualTo(1L);
    }
}*/
