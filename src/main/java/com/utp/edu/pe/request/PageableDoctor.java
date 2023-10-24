package com.utp.edu.pe.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageableDoctor {
    private int page;
    private int size;
    private String orderParameter;
    private String typeOrder;
}
