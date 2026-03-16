package ra.security.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.security.jwt.dto.FormLogin;
import ra.security.jwt.dto.FormRegister;
import ra.security.jwt.entity.Role;
import ra.security.jwt.entity.RoleName;
import ra.security.jwt.entity.Users;
import ra.security.jwt.principle.UserDetailCustom;
import ra.security.jwt.repository.IRoleRepository;
import ra.security.jwt.repository.IUserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService{
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserRepository userRepository;
    @Override
    public void register(FormRegister dto) {
        // biến đổi dto -> entity
        Users entity = new Users();
        entity.setEmail(dto.getEmail());
        entity.setFullName(dto.getFullName());
        entity.setPassword(encoder.encode(dto.getPassword()));
        entity.setPhone(dto.getPhone());

        // biến đoie quyền
        Set<Role> roleSet = new HashSet<>();
        if(dto.getRoles()==null || dto.getRoles().isEmpty()){
            roleSet.add(roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow());
        }else {
            dto.getRoles().forEach(r->{
                switch (r.toLowerCase()){
                    case "admin":
                        roleSet.add(roleRepository.findByRoleName(RoleName.ROLE_ADMIN).orElseThrow());
                        break;
                    case "user":
                        roleSet.add(roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow());
                        break;
                    default:
                        throw new RuntimeException("ko hợp lệ");
                }
            });
        }
        entity.setRoleSet(roleSet);

        userRepository.save(entity);
    }

    @Override
    public Users login(FormLogin dto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto
                            .getEmail(),dto.getPassword())
            );
            // xác minh thznh công
            String u = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println("email" + u);

            // trả về token
            return userRepository.findByEmail(dto.getEmail()).orElseThrow();
        }catch (Exception e){
            return null;
        }
    }
}
