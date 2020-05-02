package com.silent.samurai.utils;

import org.apache.log4j.Logger;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Set;

public class FieldUtil {

    static final Logger logger = Logger.getLogger(FieldUtil.class);

//    public static List<Field> fieldsByConstructor(Class<?> clazz) {
//        Field[] fields = getFields(clazz).toArray(new Field[0]);
//        Constructor<?>[] accessibleConstructors = clazz.getConstructors();
//        for (Constructor<?> constructor : accessibleConstructors) {
//            if (isFieldListMatching(constructor, fields)) {
//                rearrangeByConstructor(constructor, fields);
//            }
//        }
//        return Arrays.asList(fields);
//    }
//
//    public static void rearrangeByConstructor(Constructor<?> constructor, Field[] fields) {
//        int i = 0, j;
//        for (Parameter parameter : constructor.getParameters()) {
//            for (j = i; j < fields.length; j++) {
//                if (fields[j].getType().getSimpleName().equals(parameter.getType().getSimpleName())) {
//                    break;
//                }
//            }
//            ArrayUtils.swap(fields, i++, j);
//        }
//    }
//
//    public static boolean isFieldListMatching(Constructor<?> constructor, Field[] fields) {
//        if (constructor.getParameterCount() != fields.length)
//            return false;
//        Set<String> hashSet = new HashSet<>();
//        for (Parameter parameter : constructor.getParameters()) {
//            hashSet.add(parameter.getType().getSimpleName());
//        }
//
//        logger.info(hashSet);
//        for (Field field : fields) {
//            if (!hashSet.contains(field.getType().getSimpleName())) {
//                return false;
//            }
//        }
//        return true;
//    }

    public static Set<Field> getFields(Class<?> clazz) {
        return ReflectionUtils.getFields(clazz, CommonUtil::isInstanceField);
    }


}
