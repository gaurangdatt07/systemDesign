package designPatterns.factoryMethodInterface.concreteClasses;

public class LLDCourse extends Course {


    @Override
    public void creteCourse() {
        moduleList.add(new LLDImplementationModule());
        moduleList.add(new LLDIntroModule());
        moduleList.add(new LLDSummaryModule());
    }
}
