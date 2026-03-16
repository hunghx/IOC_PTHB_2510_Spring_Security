package ra.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.security.jwt.entity.Users;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<Users, String> {
    // lấy thông tin người dùng qua username

    Optional<Users> findByEmailOrPhone(String email, String phone);
    Optional<Users> findByEmail(String email);
}
