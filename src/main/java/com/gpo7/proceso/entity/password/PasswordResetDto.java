package com.gpo7.proceso.entity.password;

import javax.validation.constraints.NotEmpty;

import com.gpo7.proceso.constraint.FieldMatch;


@FieldMatch(first = "password", second = "confirmPassword", message = "Los campos de contraseña deben coincidir")
public class PasswordResetDto {
	@NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String token;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
