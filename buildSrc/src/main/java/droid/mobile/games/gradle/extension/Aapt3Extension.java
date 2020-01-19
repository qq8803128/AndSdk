package droid.mobile.games.gradle.extension;

import java.util.ArrayList;
import java.util.List;

public class Aapt3Extension {
    public List<String> resourcesPackage = new ArrayList<>();

    public List<String> getResourcesPackage() {
        return resourcesPackage;
    }

    public void setResourcesPackage(List<String> resourcesPackage) {
        this.resourcesPackage = resourcesPackage;
    }
}
