package org.example;

@Cache(value = {"FirstValue", "SecondValue"})
@Two(first = "FirstValue", second = "SecondValue")
@Default(value = MyClass.class)
@Validate(value = {MyClass.class})
@ToString
public class MyClass implements MyInterface {
    private int value;

    public MyClass(int value) {
        this.value = value;
    }

    @Override
    public void incrementValue() {
        value += 1;
    }

    @Override
    public int getValue() {
        System.out.println("Ещё не кэш");
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}