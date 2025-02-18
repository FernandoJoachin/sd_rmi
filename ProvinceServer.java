import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ProvinceServer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            ProvinceObject po = new ProvinceObject();
            registry.rebind("Province", po);
            System.out.println("ProvinceServer is created!!!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
