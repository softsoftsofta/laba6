package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // Целью может быть только МЕТОД
@Retention(RetentionPolicy.RUNTIME) // Доступна во время исполнения программы
public @interface Invoke {
    // Не имеет свойств - нет никаких методов внутри аннотации
}
