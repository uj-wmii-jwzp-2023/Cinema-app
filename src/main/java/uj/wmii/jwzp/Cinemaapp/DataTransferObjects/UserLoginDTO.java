package uj.wmii.jwzp.Cinemaapp.DataTransferObjects;

public class UserLoginDTO {
    private String email;
    private String password;


    public UserLoginDTO() {}

    public UserLoginDTO(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
