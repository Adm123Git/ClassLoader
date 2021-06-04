package ru.adm123.classloader;

/**
 * @author Dmitry Ushakov 02.06.2021
 *
 * Байткод этого класса будет загружаться загрузчиком
 *
 */
public class TestClass {

    public void printSuccess() {
        System.out.println("=============================");
        System.out.println("I'M LOADED");
        System.out.println("THANKS " + this.getClass().getClassLoader().getName() + " FOR IT");
        System.out.println("=============================");
    }

}
