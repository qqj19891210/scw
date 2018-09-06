package com.smart.scw.manager.bean;

import java.io.Serializable;
import java.util.Objects;

public class TRole implements Serializable {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "TRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TRole tRole = (TRole) o;
        return Objects.equals(id, tRole.id) &&
                Objects.equals(name, tRole.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}