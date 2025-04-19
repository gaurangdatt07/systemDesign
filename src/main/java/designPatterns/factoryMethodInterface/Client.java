package designPatterns.factoryMethodInterface;

import designPatterns.factoryMethodInterface.concreteClasses.Course;

public class Client {
    public static void main(String[] args) {
        // the client will only call the factory for an object without actually knowing
        // the implementation of object creation

        Course lld = CourseFactory.getCourse("LLD");
        System.out.println("LLD Module : "+lld.moduleList);

        Course hld = CourseFactory.getCourse("HLD");
        System.out.println("HLD Modules: "+hld.moduleList);

    }
}
