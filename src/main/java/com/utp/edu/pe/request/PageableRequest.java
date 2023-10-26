package com.utp.edu.pe.request;

public class PageableRequest {
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
        this.size = (this.size == 0) ? 5 : this.size;
    }

    public String getOrderParameter() {
        return orderParameter;
    }

    public void setOrderParameter(String orderParameter) {
        this.orderParameter = orderParameter;
    }

    public String getTypeOrder() {
        return typeOrder;
    }

    public void setTypeOrder(String typeOrder) {
        this.typeOrder = (typeOrder == null || typeOrder.isEmpty()) ? "DESC" : typeOrder;
    }
}
