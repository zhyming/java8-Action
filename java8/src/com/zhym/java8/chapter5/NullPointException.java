package com.zhym.java8.chapter5;

import org.omg.SendingContext.RunTime;

import java.util.Optional;

/**
 * TODO
 *
 * @Author zhym
 * @Date 2020/6/17 22:31
 */
public class NullPointException {

    public static void main(String[] args) {
        //String insuranceName = getInsuranceName(new Persion());
        Optional<Insurance> optionalInsurance = Optional.empty();



        Optional<Insurance> optionalInsurance1 = Optional.of(new Insurance());

       /* Optional<Insurance> objectOptional = Optional.ofNullable(null);

        objectOptional.orElseGet(Insurance::new);

        objectOptional.orElse(new Insurance());

        objectOptional.orElseThrow(RuntimeException::new);

        objectOptional.orElseThrow(() -> new RuntimeException("NOT ELEMENT!"));*/

        //Insurance insurance = optionalInsurance1.filter(i -> i.getName() == null).get();
        // System.out.println(insurance);

        Optional<String> s = optionalInsurance1.map(Insurance::getName);
        System.out.println(s.orElse("empty value!"));
        System.out.println(s.isPresent());
        s.ifPresent(System.out::println);




    }

    private static String getInsuranceNameByMultExit(Persion persion){
        String defaultValue = "UNKNOWN";
        if (persion == null){
            return defaultValue;
        }
        Car car = persion.getCar();
        if (car == null){
            return defaultValue;
        }
        Insurance insurance = car.getInsurance();
        if (insurance == null){
            return defaultValue;
        }
        return insurance.getName();
    }

    private static String getInsuranceNameByDeepDoubts(Persion persion){

        if (persion != null){
            Car car = persion.getCar();
            if (car != null){
                Insurance insurance = car.getInsurance();
                if (insurance != null){
                    return insurance.getName();
                }
            }
        }

        return null;
    }

    private static String getInsuranceName(Persion persion){

        return persion.getCar().getInsurance().getName();
    }

    private static String getInsuranceNameByOptional(Persion persion){

        return  Optional.of(persion).map(Persion::getCar).map(Car::getInsurance).map(Insurance::getName).get();
    }

    private static String getName(Insurance insurance){

        return Optional.ofNullable(insurance).map(Insurance::getName).orElse("unKnown");
    }


}
