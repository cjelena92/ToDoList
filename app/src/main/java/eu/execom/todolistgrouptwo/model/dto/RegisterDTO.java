package eu.execom.todolistgrouptwo.model.dto;

/**
 * Created by Jelena on 6/12/2016.
 */

public class RegisterDTO {

    private String email;

    private String password;

    private String confirmPassword;

    public RegisterDTO(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "RegisterDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
