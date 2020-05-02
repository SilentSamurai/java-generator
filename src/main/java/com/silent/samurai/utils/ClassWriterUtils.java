package com.silent.samurai.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class ClassWriterUtils {

    public static void writeClass(String className, String clazz, String rootPath, String packageName) throws IOException {
        String packagePath = CommonUtil.package2path(packageName);
        String absolutePath = rootPath + "/src/main/java/" + packagePath + "/" + className + ".java";
        File file = new File(absolutePath);
        FileUtils.writeStringToFile(file, clazz, Charset.defaultCharset());
    }

}
