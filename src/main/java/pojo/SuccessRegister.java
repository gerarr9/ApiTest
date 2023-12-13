package pojo;

public class SuccessRegister extends Register{
    private Integer id;
    private String token;

    public SuccessRegister(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public SuccessRegister(String email, String password, Integer id, String token) {
        super(email, password);
        this.id = id;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
