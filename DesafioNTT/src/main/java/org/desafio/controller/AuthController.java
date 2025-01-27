//package org.desafio.controller;
//import org.desafio.model.Pessoa;
//import org.desafio.service.PessoaService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private PessoaService pessoaService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody Pessoa loginRequest) {
//        Pessoa pessoa = pessoaService.findByEmail(loginRequest.getEmail());
//        if (pessoa != null && passwordEncoder.matches(loginRequest.getSenha(), pessoa.getSenha())) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(401).body("Invalid email or password");
//        }
//    }
//}
