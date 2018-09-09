package cn.com.enums;

public class EnumUtil {
    //���صĶ���ʵ��CodeEnum�ӿ�    
    public static <T extends CodeEnum> T getByCode(Class<T> enumClass, Integer code) {
        for (T each : enumClass.getEnumConstants()) {
            if(each.getCode()==code){
                return each;
            }
        }
        return null;
    }
}