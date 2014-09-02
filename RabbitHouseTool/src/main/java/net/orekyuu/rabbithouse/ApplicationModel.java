package net.orekyuu.rabbithouse;


public class ApplicationModel {

    public static ApplicationModel model = new ApplicationModel();

    private ProjectModel project;

    private ApplicationModel() {
    }

    public static ApplicationModel getInstance() {
        return model;
    }

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }
}
