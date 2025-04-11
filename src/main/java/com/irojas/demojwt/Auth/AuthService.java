package com.irojas.demojwt.Auth;

import com.irojas.demojwt.User.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.irojas.demojwt.Jwt.JwtService;
import com.irojas.demojwt.User.RoleRepository;
import com.irojas.demojwt.User.User;
import com.irojas.demojwt.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .role(user.getRole().getNombre())  // Incluir el rol en la respuesta
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        Role role = roleRepository.findById(request.getRole()).orElseThrow(() -> new RuntimeException("Role not found"));
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .country(request.getCountry())
                .role(role)  // Asignar el rol desde la base de datos
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .role(user.getRole().getNombre())  // Incluir el rol en la respuesta
                .build();
    }
}
