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
        this.size = (size == 0) ? 5 : size;
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
        this.typeOrder = (typeOrder == null || typeOrder.isEmpty()) ? "ASC" : typeOrder.toUpperCase();
    }

    @Override
    public String toString() {
        return "PageableRequest{" +
                "page=" + page +
                ", size=" + size +
                ", orderParameter='" + orderParameter + '\'' +
                ", typeOrder='" + typeOrder + '\'' +
                '}';
    }
}
