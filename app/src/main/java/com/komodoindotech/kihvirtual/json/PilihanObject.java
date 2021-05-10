package com.komodoindotech.kihvirtual.json;

import java.util.List;

public class PilihanObject {
    private String id;
    private String label;
    private Boolean value = false;
    private Boolean disabled = false;
    private List<PilihanObject> childrens;

    public PilihanObject(String label) {
        this.label = label;
    }

    public PilihanObject(String label, Boolean value) {
        this.label = label;
        this.value = value;
    }

    public PilihanObject(String id, String label, Boolean value) {
        this.id = id;
        this.label = label;
        this.value = value;
    }

    public PilihanObject(String id, String label, Boolean value, List<PilihanObject> childrens) {
        this.id = id;
        this.label = label;
        this.value = value;
        this.childrens = childrens;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
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

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public List<PilihanObject> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<PilihanObject> childrens) {
        this.childrens = childrens;
    }
}
