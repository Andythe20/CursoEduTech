package com.example.Cursos.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Cursos.Model.DTO.UserDTO;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.users.url}")
    private String usersApiUrl;

    public UserDTO getUserById(int idUsuario) {
        String url = usersApiUrl + idUsuario;
        UserDTO user = restTemplate.getForObject(url, UserDTO.class);
        return user;
    }
}
