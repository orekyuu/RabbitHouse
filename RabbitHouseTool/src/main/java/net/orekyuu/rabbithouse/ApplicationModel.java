package net.orekyuu.rabbithouse;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

class ApplicationModel {

    public static ApplicationModel model = new ApplicationModel();

    private ProjectModel project;
    private BooleanProperty isOpenProject;

    private ApplicationModel() {
        isOpenProject = new SimpleBooleanProperty(false);
    }

    public static ApplicationModel getInstance() {
        return model;
    }

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
        isOpenProject.set(project != null);
    }

    public BooleanProperty getIsOpenProject() {
        return isOpenProject;
    }
}
