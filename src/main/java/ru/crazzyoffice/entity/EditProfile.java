package ru.crazzyoffice.entity;

import java.io.Serializable;

public class EditProfile implements Serializable {
    private Integer employee_id;
    private String first;
    private String last;
    private Boolean autorised;

    public EditProfile() {
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public Boolean getAutorised() {
        return autorised;
    }

    public void setAutorised(Boolean autorised) {
        this.autorised = autorised;
    }

    @Override
    public String toString() {
        return "EditProfile{" +
                "employee_id=" + employee_id +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", autorised=" + autorised +
                '}';
    }
}
