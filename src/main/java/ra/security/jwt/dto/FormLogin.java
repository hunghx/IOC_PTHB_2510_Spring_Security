package ra.security.jwt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormLogin {
    private String email;
    private String password;
}
