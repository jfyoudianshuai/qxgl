package com.duyi.qxgl.domain;

import java.io.Serializable;
import java.util.List;

public class Fn implements Serializable{
    private Integer fid;
    private String fname;
    private String fhref;
    private Integer frag;
    private Integer pid;
    private String yl1;
    private String yl2;
    private List<Fn> list;

    public List<Fn> getList() {
        return list;
    }

    public void setList(List<Fn> list) {
        this.list = list;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFhref() {
        return fhref;
    }

    public void setFhref(String fhref) {
        this.fhref = fhref;
    }

    public Integer getFrag() {
        return frag;
    }

    public void setFrag(Integer frag) {
        this.frag = frag;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getYl1() {
        return yl1;
    }

    public void setYl1(String yl1) {
        this.yl1 = yl1;
    }

    public String getYl2() {
        return yl2;
    }

    public void setYl2(String yl2) {
        this.yl2 = yl2;
    }

    public Fn() {
    }

    public Fn(Integer fid, String fname, String fhref, Integer frag, Integer pid, String yl1, String yl2,List<Fn> list) {
        this.fid = fid;
        this.fname = fname;
        this.fhref = fhref;
        this.frag = frag;
        this.pid = pid;
        this.yl1 = yl1;
        this.yl2 = yl2;
        this.list=list;
    }
}
