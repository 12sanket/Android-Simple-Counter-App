package com.sanket.simplecounterapp;

public class data_list {

    private String id, name, count;

    public data_list(String id, String name, String count)
    {
        this.setId(id);
        this.setName(name);
        this.setCount(count);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
