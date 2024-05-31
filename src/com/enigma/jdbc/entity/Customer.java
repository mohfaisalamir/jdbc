package com.enigma.jdbc.entity;

public class Customer {
    private int id;
    private String name;
    private String phone;
    private boolean isMember;

    public Customer(int id, String name, String phone, boolean isMember) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.isMember = isMember;
    }
    public Customer() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    @Override
    public String toString() {
        return /*"Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", isMember=" + isMember +
                '}';*/
                "=".repeat(20)+"\nNama    : " + name + "\nPhone : " + phone+"\nMember : " + isMember;
    }
}
