package com.silent.samurai.utils;

import com.google.common.base.Charsets;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ClassWriterUtils {

    public static String writeClass(String className, String clazz, String rootPath, String packageName) throws IOException {
        String packagePath = CommonUtil.package2path(packageName);
        String absolutePath = rootPath + "/src/main/java/" + packagePath + "/" + className + ".java";
        File file = new File(absolutePath);
        FileUtils.writeStringToFile(file, clazz, Charsets.UTF_8);
        return absolutePath;
    }

}
