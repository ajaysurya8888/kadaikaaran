package com.kadaikaaran.kadaikaaran.controller;


import com.kadaikaaran.kadaikaaran.dao.Agent;
import com.kadaikaaran.kadaikaaran.dao.LoginRequest;
import com.kadaikaaran.kadaikaaran.service.AgentDetailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agent")
class AgentController {

    private AgentDetailService agentDetailService;
    private  AuthenticationManager authenticationManager;

    @Autowired
    public AgentController(AgentDetailService agentDetailService, AuthenticationManager authenticationManager) {
        this.agentDetailService = agentDetailService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<String> AddAgent(@RequestBody Agent agent){
        agentDetailService.addAgent(agent);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        agentDetailService.loginAgent(loginRequest.getIdentifier(), loginRequest.getPassword(), request);
        return ResponseEntity.ok("Login successful!");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> login( HttpServletRequest request) {
        agentDetailService.logoutAgent(request);
        return ResponseEntity.ok("Login successful!");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/working")
    public ResponseEntity<String> working() {
        return ResponseEntity.ok("working");
    }

}