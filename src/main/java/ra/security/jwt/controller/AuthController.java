package ra.security.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    // dăng kí
    @PostMapping("/register")
    public ResponseEntity<?> register(){
        // mật khẩu phải mã hóa
//        passwordEncoder.encode() //mã hóa mật khẩu
    }
}
