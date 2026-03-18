package ra.security.jwt.service;

import ra.security.jwt.dto.FormLogin;
import ra.security.jwt.dto.FormRegister;
import ra.security.jwt.dto.JwtResponse;
import ra.security.jwt.entity.Users;

public interface IAuthenticationService {
    void register(FormRegister dto);
    JwtResponse login(FormLogin dto);
}
