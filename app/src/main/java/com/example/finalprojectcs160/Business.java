package com.example.finalprojectcs160;

public class Business {
    private int id;
    private String name;
    private String desc;
    private String address;
    private boolean[] causes;
    private String img;
    private boolean img_islink;


    public Business (int id, String name, String desc, String address, boolean[] causes, String img, boolean img_islink) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.address = address;
        this.causes = causes;
        this.img = img;
        this.img_islink = img_islink;
    }

    public int getId() {
        return id;
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
}