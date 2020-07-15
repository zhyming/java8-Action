package com.zhym.java8.chapter2;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/14 0:36
 */
public class NApple {
    private String name;
    private String color;
    private Long weight;

    public NApple(String name, String color, Long weight) {
        this.name = name;
        this.color = color;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "NApple{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
