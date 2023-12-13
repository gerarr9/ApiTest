package pojo;

import com.google.gson.annotations.SerializedName;

public class SungleUser {
    @SerializedName("data")
    private User user;
    private Support support;

    public SungleUser() {
    }

    public SungleUser(User user, Support support) {
        this.user = user;
        this.support = support;
    }

    public User getUser() {
        return user;
    }

    public Support getSupport() {
        return support;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSupport(Support support) {
        this.support = support;
    }
}
