package com.utp.edu.pe.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.utp.edu.pe.bean.HeaderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PetLifeUtil {

    private static final Logger LOG = LoggerFactory.getLogger(PetLifeUtil.class);

    public static HeaderRequest setHeaders(HeaderRequest headerRequest, String idTransaccion, String userId, String msgid,
                                           String accept, String aplicacion, String timestamp) {

        headerRequest.setIdTransaccion(idTransaccion);
        headerRequest.setUserId(userId);

        headerRequest.setMsgid(msgid);
        headerRequest.setAccept(accept);
        headerRequest.setTimestamp(timestamp);
        headerRequest.setAplicacion(aplicacion);

        return headerRequest;
    }

    public static String printPrettyJSONString(Object o) throws JsonProcessingException {
        return new ObjectMapper().setDateFormat(getLocalFormat())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).writerWithDefaultPrettyPrinter()
                .writeValueAsString(o);
    }

    public static DateFormat getLocalFormat() {
        DateFormat dateFormat = new SimpleDateFormat(Constantes.FORMATO_FECHA_CABECERA);
        dateFormat.setTimeZone(TimeZone.getDefault());
        return dateFormat;
    }

    public static Calendar toCalendar(final String iso8601string) {
        Calendar calendar = GregorianCalendar.getInstance();
        String s = iso8601string.replace("Z", "+00:00");
        try {
            s = s.substring(0, 22) + s.substring(23); // to get rid of the ":"
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ROOT).parse(s);
            calendar.setTime(date);
        } catch (IndexOutOfBoundsException e) {
            LOG.error("Ocurrio un error al recorrer la cadena de Fecha", e);
            calendar = null;
        } catch (ParseException e) {
            LOG.error("Ocurrio un error al convertir a Date la cadena de la fecha", e);
            calendar = null;
        }
        return calendar;
    }

}
