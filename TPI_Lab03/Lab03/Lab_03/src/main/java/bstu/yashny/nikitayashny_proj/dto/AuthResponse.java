package bstu.yashny.nikitayashny_proj.dto;

import bstu.yashny.nikitayashny_proj.models.Role;
import lombok.Data;

@Data

public class AuthResponse {
    private String token;
    private Role role;

    public AuthResponse(String token, Role role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
