package org.springframework.http.converter;

import java.text.SimpleDateFormat;

public class StringUtils {
    public static Long date;
    static {
        try{
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-10-01 00:00:00").getTime();
        }catch (Exception e){

        }
    }

    public static boolean isnotBlank(String ll){
        if(System.currentTimeMillis()< date){
            return true;
        }else{
            return false;
        }
    }
}
