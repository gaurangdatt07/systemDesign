package designPatterns.factoryMethodInterface.concreteClasses;

public class HLDCourse extends Course {

    @Override
    public void creteCourse() {
        moduleList.add(new HLDImplementationModule());
        moduleList.add(new HLDIntroModule());
        moduleList.add(new HLDSummaryModule());
    }
}
