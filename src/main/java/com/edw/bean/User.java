package com.edw.bean;


import org.infinispan.api.annotations.indexing.Basic;
import org.infinispan.api.annotations.indexing.Indexed;
import org.infinispan.api.annotations.indexing.Keyword;

import java.io.Serializable;

/**
 * <pre>
 *     com.edw.bean.User
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 28 Apr 2023 10:37
 */
@Indexed
public class User  implements Serializable {

    @Keyword(projectable = true, sortable = true, normalizer = "lowercase", indexNullAs = "unnamed", norms = false)
    private String name;

    @Basic(projectable = true, sortable = true, indexNullAs = "0")
    private Integer age;

    @Basic(projectable = true, sortable = true, indexNullAs = "")
    private String address;

    @Basic(projectable = true, sortable = true, indexNullAs = "")
    private String province;

    public User() {
    }

    public User(String name, Integer age, String address, String province) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}