/**
 * Object implementation of business data
 */

package com.example.finalprojectcs160;

import androidx.annotation.NonNull;

public class Business {
    private int b_id;
    private String name;
    private String desc;
    private String address;
    private boolean[] causes;
    private String img;
    private boolean img_islink;
    private String[] search_tags;


    public Business (int b_id, String name, String desc, String address, boolean[] causes, String img, boolean img_islink, String[] search_tags) {
        this.b_id = b_id;
        this.name = name;
        this.desc = desc;
        this.address = address;
        this.causes = causes;
        this.img = img;
        this.img_islink = img_islink;
        this.search_tags = search_tags;
    }

    public int getId() {
        return b_id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getAddress() {
        return address;
    }

    public boolean[] getCauses() {
        return causes;
    }

    public String getImg() {
        return img;
    }

    public boolean isImg_islink() {
        return img_islink;
    }

    public String[] getTags() {
        return search_tags;
    };

    @NonNull
    @Override
    public String toString() {
        return this.b_id + " - " + this.name;
    }
}