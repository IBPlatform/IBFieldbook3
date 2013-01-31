
package ibfb.domain.core;

public class Trial {
    String name;
    int instances;

    public Trial() {
    }

    public Trial(String name, int instances) {
        this.name = name;
        this.instances = instances;
    }

    public int getInstances() {
        return instances;
    }

    public void setInstances(int instances) {
        this.instances = instances;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
