package ra.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.security.jwt.entity.User;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, String> {
    // lấy thông tin người dùng qua username

    Optional<User> findByEmailOrPhone(String email, String phone);
    Optional<User> findByEmail(String email);
}
