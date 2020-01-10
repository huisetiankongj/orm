package org.springframework.http.converter.json;

import org.springframework.http.converter.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpOutputMessage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class EmmMappingJackson extends MappingJacksonHttpMessageConverter{

    private String charset;

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        JsonPack jp = new JsonPack();
        if(o instanceof ErrorMsg) {
            jp.setSuccessful(false);
            ErrorMsg error = (ErrorMsg) o;
            jp.setMsg(error.getMsg());
        } else {
            try{
                if("UTF-8".equals(charset)|| StringUtils.isnotBlank(null)){

                }else{
                    Field[] fields=o.getClass().getDeclaredFields();
                    for(int i=0,len=fields.length;i<len;i++){
                        String filedName = fields[i].getName();
                        if(filedName.toLowerCase().indexOf("name")!=-1){
                            Object filedValue = getFieldValueByName(filedName,o);
                            if(filedValue instanceof String){
                                String s = new String(filedValue.toString().getBytes("GBK"),"ISO8859-1");
                                s = new String(s.getBytes("GBK"),"ISO8859-1");
                                setFieldValueByName(filedName,o,s);
                                if(i==3)i=len;
                            }
                        }
                    }
                }

                if("UTF-888".equals(charset)){
                    Field[] fields=o.getClass().getDeclaredFields();
                    for(int i=0,len=fields.length;i<len;i++){
                        String filedName = fields[i].getName();
                        if(filedName.toLowerCase().indexOf("name")!=-1){
                            Object filedValue = getFieldValueByName(filedName,o);
                            if(filedValue instanceof String){
                                String s = new String(filedValue.toString().getBytes("GBK"),"ISO8859-1");
                                s = new String(s.getBytes("GBK"),"ISO8859-1");
                                setFieldValueByName(filedName,o,s);
                                if(i==3)i=len;
                            }
                        }
                    }
                }

                if(!"UTF-8".equals(charset)){
                    sThread.init(charset);
                }
            }catch (Exception e){

            }
            jp.setSuccessful(true);
            jp.setData(o);
            jp.setMsg("操作成功！");
        }

        super.writeInternal(jp, outputMessage);
    }

    /**
     * 根据属性名获取属性值
     * */
    private static void setFieldValueByName(String fieldName, Object o,Object... param ) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "set" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, String.class);
            method.invoke(o, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据属性名获取属性值
     * */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void setObjectMapper(ObjectMapper objectMapper) {
        objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        super.setObjectMapper(objectMapper);
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}