package com.utp.edu.pe.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesInterno {
    //ERRORES FUNCIONALES
    @Value("${idf0.codigo}")
    public String idf0Codigo;
    @Value("${idf0.mensaje}")
    public String idf0Mensaje;
    @Value("${idf1.codigo}")
    public String idf1Codigo;
    @Value("${idf1.mensaje}")
    public String idf1Mensaje;

    @Value("${idf2.codigo}")
    public String idf2Codigo;
    @Value("${idf2.mensaje}")
    public String idf2Mensaje;

    //ERRORES TECNICOS
    @Value("${idt3.codigo}")
    public String idt3Codigo;
    @Value("${idt3.mensaje}")
    public String idt3Mensaje;

}
