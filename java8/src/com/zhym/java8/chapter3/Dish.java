package com.zhym.java8.chapter3;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/14 14:53
 */
public class Dish {

    private final String name;

    private final Boolean vegetarian;

    private final int calories;

    private final Type type;

    public Dish(String name, Boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String
    toString() {
        return name;
    }

    public enum Type{
        MEAT, FISH, OTHER
    }
}
