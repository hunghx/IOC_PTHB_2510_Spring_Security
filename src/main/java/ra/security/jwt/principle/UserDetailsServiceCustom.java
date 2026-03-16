package ra.security.jwt.principle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.security.jwt.entity.User;
import ra.security.jwt.repository.IUserRepository;

import java.util.List;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // gọi tới DB để chỉ định cách xác thực thông qua username
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usename ko tồn tại"));
        List<SimpleGrantedAuthority> list = user.getRoleSet().stream().map(
                role-> new SimpleGrantedAuthority(role.getRoleName().name())
        ).toList();
        return UserDetailCustom.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(list)
                .build();
    }
}
