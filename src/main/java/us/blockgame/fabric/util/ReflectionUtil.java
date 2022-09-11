package us.blockgame.fabric.util;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class ReflectionUtil {

    @SneakyThrows
    public static Object getDeclaredObject(String declaredField, Class clazz, Object instance) {
        Field f = clazz.getDeclaredField(declaredField);
        f.setAccessible(true);
        return f.get(instance);
    }
}
