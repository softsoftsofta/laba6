package org.example;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        out.println("Введите номер задания: ");
        int n = in.nextInt();
        in.nextLine();
        switch(n) {
            case 13:
                MyClass example = new MyClass(10);
                MyInterface cachedExample11 = CacheUtil.cache(example);



                if (example.getClass().isAnnotationPresent(Cache.class)) {
                    Cache cacheAnnotation = example.getClass().getAnnotation(Cache.class);
                    System.out.println("Cache values: " + String.join(", ", cacheAnnotation.value()));


                    // Первый вызов метода getValue
                    System.out.println(cachedExample11.getValue()); // Метод вызывается, результат кэшируется

                    // Второй вызов метода getValue
                    System.out.println(cachedExample11.getValue()); // Возвращается кэшированный результат

                    // Изменение состояния
                    cachedExample11.incrementValue();

                    // Третий вызов метода getValue
                    System.out.println(cachedExample11.getValue()); // Метод вызывается снова, результат кэшируется

                }
                break;
            case 22:
                // Проверка аннотации @Default
                MyClass myClass22 = new MyClass(10);
                if (myClass22.getClass().isAnnotationPresent(Default.class)) {
                    Default DefaultAnnotation = myClass22.getClass().getAnnotation(Default.class);
                    out.println("Default value: " + DefaultAnnotation.value());
                } else out.println("Класс переменной " + myClass22 + " не проаннотирован @Default");
                break;
            case 23:
                // Проверка аннотации @ToString
                MyClass myClass23 = new MyClass(10);
                if (myClass23.getClass().isAnnotationPresent(ToString.class)) {
                    ToString toStringAnnotation = myClass23.getClass().getAnnotation(ToString.class);
                    out.println("ToString value: " + toStringAnnotation.value());
                } else out.println("Класс переменной " + myClass23 + " не проаннотирован @ToString");
                break;
            case 24:
                // Проверка аннотации @Validate
                MyClass myClass24 = new MyClass(10);
                if (myClass24.getClass().isAnnotationPresent(Validate.class)) {
                    Validate ValidateAnnotation = myClass24.getClass().getAnnotation(Validate.class);
                    out.println("Validate value: " + Arrays.toString(ValidateAnnotation.value()));
                } else out.println("Класс переменной " + myClass24 + " не проаннотирован @Validate");
                break;
            case 25:
                // Проверка аннотации @Two
                MyClass myClass25 = new MyClass(10);
                if (myClass25.getClass().isAnnotationPresent(Two.class)) {
                    Two twoAnnotation = myClass25.getClass().getAnnotation(Two.class);
                    out.println("First value: " + twoAnnotation.first());
                    out.println("Second value: " + twoAnnotation.second());
                } else out.println("Класс переменной " + myClass25 + " не проаннотирован @Two");
                break;
            case 26:
                // Проверка аннотации @Validate
                MyClass myClass26 = new MyClass(10);
                if (myClass26.getClass().isAnnotationPresent(Validate.class)) {
                    Cache CacheAnnotation = myClass26.getClass().getAnnotation(Cache.class);
                    out.println("Cache value: " + Arrays.toString(CacheAnnotation.value()));
                } else out.println("Класс переменной " + myClass26 + " не проаннотирован @Cache");
                break;
            case 33:
                MyClass example33 = new MyClass(10);
                MyClass example33_2 = new MyClass(20);

                Object[] cachedObjects = CacheUtil.cache(example33, example33_2);

                MyInterface cachedExample33 = (MyInterface) cachedObjects[0];
                MyInterface cachedExample33_2 = (MyInterface) cachedObjects[1];

                System.out.println("Initial value: " + cachedExample33.getValue());
                System.out.println(cachedExample33.getValue());
                cachedExample33.incrementValue();
                System.out.println("After increment: " + cachedExample33.getValue());

                System.out.println("Initial value: " + cachedExample33_2.getValue());
                System.out.println(cachedExample33_2.getValue());
                cachedExample33_2.incrementValue();
                System.out.println("After increment: " + cachedExample33_2.getValue());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + n);
        }
    }
}