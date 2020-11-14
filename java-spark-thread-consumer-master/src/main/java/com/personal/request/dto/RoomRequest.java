package com.personal.request.dto;

/**
 * Created by angel on 7/7/17.
 */
public class RoomRequest {
    private String count;
    private String adults;
    private String children;
    private AgesRequest[] childrenages;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getAdults() {
        return adults;
    }

    public void setAdults(String adults) {
        this.adults = adults;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public AgesRequest[] getChildrenages() {
        return childrenages;
    }

    public void setChildrenages(AgesRequest[] childrenages) {
        this.childrenages = childrenages;
    }
}
