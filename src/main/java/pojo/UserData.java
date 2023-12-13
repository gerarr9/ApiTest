package pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserData {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    @SerializedName("data")
    private List<User> users;
    private Support support;

    public UserData() {
    }

    public UserData(int page, int per_page, int total, int total_pages, List<User> users, Support support) {
        this.page = page;
        this.per_page = per_page;
        this.total = total;
        this.total_pages = total_pages;
        this.users = users;
        this.support = support;
    }

    public int getPage() {
        return page;
    }

    public int getPer_page() {
        return per_page;
    }

    public int getTotal() {
        return total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<User> getUsers() {
        return users;
    }

    public Support getSupport() {
        return support;
    }
}
