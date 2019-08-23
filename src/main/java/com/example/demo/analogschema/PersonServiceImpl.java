package com.example.demo.analogschema;

import com.example.demo.annotation.MyAspectAnnotation;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoaderListener;

/**
 * @Created by chenhe
 * @Date 2019-08-16 10:23
 * @Description TODO
 */
@Service
public class PersonServiceImpl implements PersonService,  c{

    @Override
    @MyAspectAnnotation
    public void say() {
//        com.example.demo.analogschema.PersonServiceImpl$$EnhancerBySpringCGLIB$$3e3d9873
//        ContextLoaderListener
    }

    public static void main(String[] args) {
        PersonServiceImpl service = new PersonServiceImpl();
        Class<?>[] ifcs =   service.getClass().getInterfaces();
        System.out.println(service.getClass().getSuperclass().getName());


        for (Class c : ifcs) {
            System.out.println(c.getName());
        }
    }
}
interface c{}
