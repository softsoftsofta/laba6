package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CacheUtilTest {

    // Тестовый интерфейс
    public interface TestInterface {
        @Invoke
        int getValue();
        void setValue(int value);
        int getCount();
        int getParamValue(int param);
    }

    // Тестовый класс с аннотацией @Cache
    @Cache({"getValue", "getCount"})
    public static class TestClass implements TestInterface {
        private int value = 0;
        private int count = 0;

        public TestClass() {
        }

        public TestClass(int initValue, int initCount) {
            this.value = initValue;
            this.count = initCount;
        }

        @Override
        public int getValue() {
            System.out.println("call getValue()");
            return value;
        }

        @Override
        public void setValue(int value) {
            System.out.println("call setValue()");
            this.value = value;
        }

        @Override
        public int getCount() {
            System.out.println("call getCount()");
            return count++;
        }

        @Override
        public int getParamValue(int param) {
            System.out.println("call getParamValue()");
            return param;
        }


        @Override
        public String toString() {
            return "TestClass[" + value + "," + count + "]";
        }
    }

    // Тестовый класс без аннотации @Cache
    public static class TestClass2 implements TestInterface {
        private int value = 0;
        private int count = 0;

        public TestClass2() {
        }

        public TestClass2(int initValue, int initCount) {
            this.value = initValue;
            this.count = initCount;
        }

        @Override
        public int getValue() {
            System.out.println("call getValue()");
            return value;
        }

        @Override
        public void setValue(int value) {
            System.out.println("call setValue()");
            this.value = value;
        }

        @Override
        public int getCount() {
            System.out.println("call getCount()");
            return count++;
        }

        @Override
        public int getParamValue(int param) {
            System.out.println("call getParamValue()");
            return param;
        }


        @Override
        public String toString() {
            return "TestClass[" + value + "," + count + "]";
        }
    }

    // тест на подачу в метод cache пустого массива объектов (null)
    @Test
    public void testCacheWithNullArray() {
        Object[] cachedObjects = CacheUtil.cache(null);
        assertEquals(0, cachedObjects.length);
    }

    // тест на подачу в метод cache пустого массива объектов (empty)
    @Test
    public void testCacheWithEmptyArray() {
        Object[] cachedObjects = CacheUtil.cache(new Object[0]);
        assertEquals(0, cachedObjects.length);
    }

    //  тест на подачу в метод cache объекта класса, проаннотированного @Cache
    @Test
    public void testCacheWithCacheAnnotation() {
        TestClass obj1 = new TestClass();
        TestInterface cachedObject = CacheUtil.cache(obj1);

        assertNotSame(obj1, cachedObject);
    }

    //  тест на подачу в метод cache объекта класса, непроаннотированного @Cache
    @Test
    public void testCacheWithNoCacheAnnotation() {
        TestClass2 obj2 = new TestClass2();
        Object[] cachedObjects = CacheUtil.cache(obj2, 0);

        assertSame(obj2, cachedObjects[0]);
    }
}
