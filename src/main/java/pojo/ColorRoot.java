package pojo;

import com.google.gson.annotations.SerializedName;

public class ColorRoot {
    @SerializedName("data")
    private ColorData data;
    private Support support;
    public ColorRoot(){}

    public ColorRoot(ColorData data, Support support) {
        this.data = data;
        this.support = support;
    }

    public ColorData getData() {
        return data;
    }

    public void setData(ColorData data) {
        this.data = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }
}
