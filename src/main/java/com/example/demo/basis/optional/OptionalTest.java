package com.example.demo.basis.optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Created by chenhe
 * @Date 2019-08-26 09:56
 * @Description optional对null的控制
 */
public class OptionalTest {

    public static void main(String[] args) {
        Car car = null;
//        Optional<Car> optCar = Optional.of(car);

        Map<String, String> map = new HashMap<>();
        Optional<Object> value = Optional.ofNullable(map.get("key"));
        if (value.isPresent()){

            System.out.println(value.get());
        }else{
            System.out.println("null 值");
        }


    }

    public String getCarInsuranceName(Person person) {
        //1 缺少null判断 空指针异常
//        return person.getCar().getInsurance().getName();

        // 2 深度质疑 深度递归if  过于激进的防御式检查
//        if(null != person){
//            Car car = person.getCar();
//            if(null != car){
//                Insurance insurance = car.getInsurance();
//                if(null != insurance){
//                    return insurance.getName();
//                }
//            }
//        }
//        return "NULL";

        //3 过多的退出语句
//        if(null == person){
//            return "NULL";
//        }
//        Car car = person.getCar();
//        if(null == car){
//            return "NULL";
//        }
//        Insurance insurance = car.getInsurance();
//        if(null == insurance){
//            return "NULL";
//        }
//        return insurance.getName();


        return "";
    }


}
class Person {
    private Car car;
    public Car getCar() { return car; }
}
class Car {
    private Insurance insurance;
    public Insurance getInsurance() { return insurance; }
}
class Insurance {
    private String name;
    public String getName() { return name; }
}





