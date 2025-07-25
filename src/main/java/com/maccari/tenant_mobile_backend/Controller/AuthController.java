package com.maccari.tenant_mobile_backend.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maccari.tenant_mobile_backend.Model.LoginRequest;
import com.maccari.tenant_mobile_backend.Model.User;
import com.maccari.tenant_mobile_backend.Util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private User user;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (user.getUsername().equals(request.getUsername())
                && user.getPassword().equals(request.getPassword())) {
            String token = jwtUtil.generateToken(request.getUsername());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas!");
        }
    }

    @GetMapping("/validation")
    public ResponseEntity<Boolean> validation(@RequestHeader String authorization){

        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);

            try {
                jwtUtil.getUsernameFromToken(token);
                return ResponseEntity.ok(true);
                
            } catch (Exception e) {
                return ResponseEntity.ok(false);
            }

        }
        return ResponseEntity.badRequest().body(null);
    }

}
