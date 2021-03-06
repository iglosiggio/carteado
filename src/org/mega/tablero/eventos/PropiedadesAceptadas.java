package org.mega.tablero.eventos;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface PropiedadesAceptadas {
    String[] propiedades() default {};
    Class<?> desde() default Object.class;
    Class<?> dato() default Object.class;
}
