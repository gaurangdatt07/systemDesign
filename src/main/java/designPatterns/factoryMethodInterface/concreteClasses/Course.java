package designPatterns.factoryMethodInterface.concreteClasses;

import java.util.ArrayList;
import java.util.List;

public abstract class Course {

    public List<Modules> moduleList=new ArrayList<>();

    public Course() {
        this.creteCourse();
    }

    public abstract void creteCourse();
}
