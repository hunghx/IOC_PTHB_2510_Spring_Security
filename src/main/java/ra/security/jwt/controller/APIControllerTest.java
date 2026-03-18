package ra.security.jwt.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.security.jwt.security.principle.UserDetailCustom;

@RestController
@RequestMapping("/api/v1")
public class APIControllerTest {
    @GetMapping("/admin/test")
    public String testAdmin(@AuthenticationPrincipal UserDetailCustom userDetailCustom){

        return "Test thành công với vai trò Admin";
    }
    @GetMapping("/user/test")
    public String testUser(){
        return "Test thành công với vai trò User";
    }
    @GetMapping("/admin-user/test")
    public String testAdminOrUser(){
        return "Test thành công với vai trò Admin hoặc User";
    }
}
