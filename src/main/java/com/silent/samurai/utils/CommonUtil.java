package com.silent.samurai.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class CommonUtil {

    public static String toModifierString(int modifier) {
        StringBuilder sb = new StringBuilder();

        if (Modifier.isPublic(modifier)) {
            sb.append("public ");
        } else if (Modifier.isPrivate(modifier)) {
            sb.append("private ");
        } else if (Modifier.isProtected(modifier)) {
            sb.append("protected ");
        }

        if (Modifier.isStatic(modifier)) {
            sb.append("static ");
        }

        if (Modifier.isFinal(modifier)) {
            sb.append("final ");
        }

        return sb.toString();

    }

    public static boolean isInstanceField(Field field) {
        return !Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers());
    }

    public static String package2path(String packageName) {
        return packageName.replace(".", "/");
    }

    public static boolean isDirectoryAndExists(String path) {
        File tmpDir = new File(path);
        return tmpDir.exists() && tmpDir.isDirectory();
    }

    public static String getClassNameWithOutExtension(String fileName) {
        return fileName.substring(0, fileName.length() - 6);
    }

}
