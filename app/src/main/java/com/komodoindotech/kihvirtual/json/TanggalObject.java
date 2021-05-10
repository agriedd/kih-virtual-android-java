package com.komodoindotech.kihvirtual.json;

import java.util.List;

public class TanggalObject {

    private String id;

    private String label;
    private String value;
    private Boolean disabled;
    private List<PilihanObject> childrens;

    public TanggalObject(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public List<PilihanObject> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<PilihanObject> childrens) {
        this.childrens = childrens;
    }
}
