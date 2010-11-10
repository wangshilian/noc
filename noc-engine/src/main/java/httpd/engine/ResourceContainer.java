package httpd.engine;

import help.PrintObejct;
import httpd.ClassPathLoader;
import httpd.FileSystemLoader;
import httpd.MultiLoader;

import java.io.File;
import java.io.IOException;

import org.simpleframework.http.Address;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.resource.Resource;

import frame.Engine;

public class ResourceContainer implements Container {
    private final Engine<Address, Resource> engine;

    public ResourceContainer() {
        this(new ResourceEngine(new MultiLoader(new FileSystemLoader(new File("htdocs")), new ClassPathLoader(
                ResourceEngine.class.getClassLoader(), "htdocs"))));
    }

    public ResourceContainer(Engine<Address, Resource> engine) {
        this.engine = engine;
    }

    @Override
    public void handle(Request req, Response resp) {
        try {
            PrintObejct.print(Address.class, req.getAddress());
            engine.resolve(req.getAddress()).handle(req, resp);
        } catch (RuntimeException e) {
            e.printStackTrace();
            resp.setCode(404);
            try {
                resp.close();
            } catch (IOException e1) {
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setCode(404);
            try {
                resp.close();
            } catch (IOException e1) {
            }
        } catch (Throwable e) {
            e.printStackTrace();
            resp.setCode(404);
            try {
                resp.close();
            } catch (IOException e1) {
            }
        }
    }
}
