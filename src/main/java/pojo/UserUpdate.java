package pojo;

import java.util.Date;

public class UserUpdate extends  UserJob{
    private Date updatedAt;
    private UserUpdate(){}

    public UserUpdate(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserUpdate(String name, String job, Date updatedAt) {
        super(name, job);
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
