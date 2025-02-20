import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class ProvinceClient {
    public static void main(String[] args) {
        try {
            //Get reference to rmi registry server
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");
            //Lookup server object
            IRemoteProvince rp = (IRemoteProvince) registry.lookup("Province");

            // Solicitar y leer el nombre de la ciudad desde teclado
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el nombre de la ciudad: ");
            String cityName = scanner.nextLine();

            System.out.print("Ingrese el nombre corto (3 caracteres): ");
            String shortName = scanner.nextLine();

            // Insertar la ciudad en la base de datos
            Province newCity = new Province(0, shortName, cityName);
            int result = rp.save(newCity);

            if (result > 0) {
                System.out.println("Ciudad agregada exitosamente.");
            } else {
                System.out.println("Error al agregar la ciudad.");
            }

            // Desplegar la lista de ciudades existentes
            System.out.println("Lista de ciudades existentes:");
            ArrayList<Province> arrProv = rp.findAll();
            for (Province p : arrProv) {
                System.out.println(p.toString());
            }

            scanner.close();

            /*
            //Save province
            Province mid = new Province(1, "MID", "Mérida");
            Province ens = new Province(2, "ENS", "Ensenada");
            Province cdmx = new Province(3, "CMX", "Ciudad de México");
            Province cam = new Province(4, "CAM", "Ciudad de Campeches");
            Province mty = new Province(5, "MTY", "Monterrey");

            //Save province
            System.out.println("Saving provinces...");
            rp.save(mid);
            rp.save(ens);
            rp.save(cdmx);
            rp.save(cam);
            rp.save(mty);
            */

            //Update province
            /* System.out.println("Update Campeches to Campeche");
            Province updatedCAM = new Province(4, "CAM", "Ciudad de Campeche");
            int iRet = rp.update(updatedCAM); */

            //Display all provinces
            /* System.out.println("Display all provinces");
            ArrayList<Province> arrProv = rp.findAll();
            for (Province p : arrProv) {
                System.out.println(p.toString());
            } */

            //Delete Campeche
            /* System.out.println("Delete CAM");
            rp.delete(cam); */

            //Display province starts by "Ciu"
            /* System.out.println("Display province starts by \"Ciu\"");
            arrProv = rp.findByName("Ciu");
            for (Province p : arrProv) {
                System.out.println(p.toString());
            } */

            //Delete all provinces
            /* System.out.println("Delete all provinces");
            rp.deleteAll(); */
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
