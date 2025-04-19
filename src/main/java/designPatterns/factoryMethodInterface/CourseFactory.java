package designPatterns.factoryMethodInterface;

import designPatterns.factoryMethodInterface.concreteClasses.Course;
import designPatterns.factoryMethodInterface.concreteClasses.HLDCourse;
import designPatterns.factoryMethodInterface.concreteClasses.LLDCourse;

public class CourseFactory {
    /**
     * the Factory is responsible for creating the object that the client demands
     * but it also does not handle the object creation logic.. it just decides during run time
     * which subclass object to initiate
     *
     *  the object class creation are handled by concrete classes
     */
    public static Course getCourse(String courseType){
        switch (courseType){
            case "LLD":
                return new LLDCourse();
            case "HLD":
                return new HLDCourse();
                default:{
                    return null;
                }

        }
    }
}
