package ${groupId}.${artifactId}.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import ${groupId}.${artifactId}.resources.BookResource;

@ApplicationPath("/")
public class BookApplication extends Application{
    private Set<Object> singletons = new HashSet<Object>();
    
    public void RestEasyServices() {
        singletons.add(new BookResource());
    }
 
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
