package pojo;

public class UserTimeResponse extends UserTime{
    private String updateAt;


    public UserTimeResponse(String name, String job, String updateAt) {
        super(name, job);
        this.updateAt = updateAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }
}
