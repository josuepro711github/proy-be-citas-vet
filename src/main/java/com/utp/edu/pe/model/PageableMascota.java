package com.utp.edu.pe.model;

import com.fasterxml.jackson.annotation.JsonSetter;

public class PageableMascota {
    private int page;
    private int size;
    private String orderParameter;


    private String typeOrder;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size == 0) {
            this.size = 1;
        }else{
            this.size = size;
        }
    }

    public String getOrderParameter() {
        return orderParameter;
    }

    public void setOrderParameter(String orderParameter) {
        this.orderParameter = orderParameter;
//        if (orderParameter == null || orderParameter.isEmpty()) {
//            this.orderParameter = "alias";
//        }else{
//
//        }
    }

    public String getTypeOrder() {
        return typeOrder;
    }


    public void setTypeOrder(String typeOrder) {
        if (typeOrder == null) {
            this.typeOrder = "DESC";
        }else{
            this.typeOrder = typeOrder;
        }
    }

}
