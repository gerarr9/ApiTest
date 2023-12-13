package pojo;

import java.util.Date;

public class UserJobCreate extends UserJob{
    private String id;
    private Date createdAt;
    public UserJobCreate() {
    }

    public UserJobCreate(String id, Date createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public UserJobCreate(String name, String job, String id, Date createdAt) {
        super(name, job);
        this.id = id;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
