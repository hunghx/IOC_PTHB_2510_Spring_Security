package ra.security.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ra.security.jwt.dto.FormLogin;
import ra.security.jwt.dto.FormRegister;
import ra.security.jwt.dto.JwtResponse;
import ra.security.jwt.service.IAuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private IAuthenticationService authenticationService;
    // dăng kí
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody FormRegister dto){
        // mật khẩu phải mã hóa
//        passwordEncoder.encode() //mã hóa mật khẩu
        authenticationService.register(dto);
        return new ResponseEntity<>("Đăng kí thành công", HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> register(@RequestBody FormLogin dto){
        // mật khẩu phải mã hóa
        JwtResponse r = authenticationService.login(dto);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestParam String refreshToken){
        // mật khẩu phải mã hóa
//        JwtResponse r = authenticationService.login(dto);
        return new ResponseEntity<>("Tạo token thành công", HttpStatus.OK);
    }
}
