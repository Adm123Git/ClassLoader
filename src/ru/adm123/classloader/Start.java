package ru.adm123.classloader;

import java.io.File;
import java.lang.reflect.Method;

/**
 * @author Dmitry Ushakov 02.06.2021
 */
public class Start {

    private static final String BYTE_CODE_FILE_PATH = System.getProperty("user.dir")
            + File.separator
            + "out"
            + File.separator
            + "production"
            + File.separator
            + "classLoader"
            + File.separator
            + "ru"
            + File.separator
            + "adm123"
            + File.separator
            + "classloader";

    public static void main(String[] args) throws Exception {
        ClsLoader clsLoader = new ClsLoader(BYTE_CODE_FILE_PATH, ClassLoader.getSystemClassLoader());
        Class<?> loadedClass = clsLoader.findClass("TestClass");
        Method printMethod = loadedClass.getMethod("printSuccess");
        printMethod.invoke(loadedClass.getConstructor().newInstance());
    }

}
