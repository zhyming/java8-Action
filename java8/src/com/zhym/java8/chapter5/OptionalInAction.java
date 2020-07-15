package com.zhym.java8.chapter5;

import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/17 23:24
 */
public class OptionalInAction {

    public static void main(String[] args) {

        String insuranceNameByOptional = getInsuranceNameByOptional(new Persion());
        System.out.println(insuranceNameByOptional);
    }

    private static String getInsuranceNameByOptional(Persion persion){

        return  Optional.ofNullable(persion).map(Persion::getCar)
                .map(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("No Element!");

    }
}
