package ru.adm123.classloader;

import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * @author Dmitry Ushakov 02.06.2021
 *
 * Загрузчик класса из указанной папки
 */
public class ClsLoader extends ClassLoader {

    private final String CLS_LOADER_NAME = "ClsLoader";
    private final String byteCodeFilePath;

    public ClsLoader(@NotNull String byteCodeFilePath, @NotNull ClassLoader parent) {
        super(parent);
        this.byteCodeFilePath = byteCodeFilePath;
    }

    /**
     * Логика загрузчика, отвечающая за поиск и чтение байт-кода. Остальное наследуем.
     * @param name имя фвйла с байт-кодом (class-файла) без расширения
     * @return загруженный класс
     * @throws ClassNotFoundException при любой ошибке
     */
    @Override
    protected Class<?> findClass(@NotNull String name) throws ClassNotFoundException {
        File byteCodeFile = new File(byteCodeFilePath + File.separator + name + ".class");
        byte[] bytes = new byte[(int) byteCodeFile.length()];
        try (FileInputStream is = new FileInputStream(byteCodeFile)) {
            int offset = 0;
            int numRead;
            while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length)) >= 0) {
                offset += numRead;
            }
            return defineClass(this.getClass().getPackage().getName() + "." + name, bytes, 0, bytes.length);
        } catch (Exception ignored) {
            throw new ClassNotFoundException();
        }
    }

    @Override
    public String getName() {
        return CLS_LOADER_NAME;
    }

}
