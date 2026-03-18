package ra.security.jwt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;
import ra.security.jwt.entity.Users;

import java.util.Date;

@Getter
@Setter
@Builder
public class JwtResponse {
    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;
    private Date expired;
//    @JsonIgnoreProperties({})
    private Users user;
}
