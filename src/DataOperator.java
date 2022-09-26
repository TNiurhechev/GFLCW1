import model.Manufacturer;
import model.Souvenir;
import serialization.ManufacturerSerializer;
import serialization.SouvenirSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class DataOperator {
    private Scanner scanner;
    private ManufacturerSerializer ms;
    private SouvenirSerializer ss;
    private List<Manufacturer> manufacturers;
    private List<Souvenir> souvenirs;
    {
        scanner = new Scanner(System.in);
        ms = new ManufacturerSerializer();
        ss = new SouvenirSerializer();
        try {
            manufacturers = ms.read("manufacturers.txt");
            souvenirs = ss.read("souvenirs.txt");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void display() throws IOException, ClassNotFoundException {
        String choice = "";
        while(true)
        {
            System.out.println("Select an option:\n" +
                    "\"add\"(\"a\") to add an item\n" +//done
                    "\"delete\"(\"d\") to delete a manufacturer and its production\n" +//done
                    "\"edit\"(\"e\") to edit an item\n" +//done
                    "\"exit\"(\"x\") to exit\n" +//done
                    "\"save\"(\"s\") to save results\n" +//done
                    "\"update\"(\"u\") to update lists\n" +//done
                    "\"view\"(\"v\") to view an item\n" +//done
                    "\"view all\"(\"va\") to view all manufacturers and their items\n" +//done
                    "\"view by country\"(\"vc\") to view souvenirs from a specific country\n" +//done
                    "\"view by manufacturer\"(\"vm\") to view souvenirs from a specific manufacturer" +//done
                    "\"view by price\"(\"vp\") to view manufacturers whose prices are lower than value\n" +//done
                    "\"view by year\"(\"vy\") to view all souvenirs produced in a specific year\n" +//done
                    "\"view by years\"(\"vys\") to view souvenirs by years:");
            choice = scanner.next();
            switch(choice){
                case "add": case "a":
                    System.out.print("Enter \"m\" for manufacturer or \"s\" for souvenir:");
                    switch (scanner.nextLine()) {
                        case "m" -> {
                            System.out.print("Enter company's name:");
                            String mName = scanner.nextLine();
                            System.out.print("Enter company's country of origin:");
                            String mCountry = scanner.nextLine();
                            manufacturers.add(new Manufacturer(mName, mCountry));
                        }
                        case "s" -> {
                            System.out.print("Enter souvenir's name:");
                            String sName = scanner.nextLine();
                            System.out.print("Enter manufacturer's name:");
                            String sMName = scanner.nextLine();
                            System.out.print("Enter manufacturer's country of origin:");
                            String sCountry = scanner.nextLine();
                            System.out.print("Enter production year:");
                            int year = scanner.nextInt();
                            System.out.print("Enter production month:");
                            int month = scanner.nextInt();
                            System.out.print("Enter production day:");
                            int day = scanner.nextInt();
                            System.out.print("Enter price:");
                            int price = scanner.nextInt();
                            souvenirs.add(new Souvenir(sName, new Manufacturer(sMName, sCountry),
                                    LocalDate.of(year, month, day), price));
                        }
                        default -> System.out.println("No such option exists!");
                    }
                    break;
                case "delete": case "d":
                    System.out.print("Enter company's name:");
                    String delName = scanner.nextLine();
                    manufacturers = (List<Manufacturer>) manufacturers.stream()
                            .filter(m -> !m.getName().equals(delName));
                    souvenirs = (List<Souvenir>) souvenirs.stream()
                            .filter(s -> !s.getManufacturer().getName().equals(delName));
                    break;
                case "edit": case "e":
                    System.out.print("Enter \"m\" for manufacturer or \"s\" for souvenir:");
                    switch (scanner.nextLine()) {
                        case "m" -> {
                            System.out.print("Enter old company's name:");
                            String oldName = scanner.nextLine();
                            System.out.print("Enter new company's name:");
                            String eName = scanner.nextLine();
                            System.out.print("Enter company's new country of origin:");
                            String eCountry = scanner.nextLine();
                            ListIterator<Manufacturer> iterator = manufacturers.listIterator();
                            while(iterator.hasNext()){
                                Manufacturer next = iterator.next();
                                if(next.getName().equals(oldName))
                                    iterator.set(new Manufacturer(eName,eCountry));
                            }
                        }
                        case "s" -> {
                            System.out.print("Enter souvenir's old name:");
                            String oSName = scanner.nextLine();
                            System.out.print("Enter manufacturer's old name:");
                            String oMName = scanner.nextLine();
                            System.out.print("Enter souvenir's new name:");
                            String eSName = scanner.nextLine();
                            System.out.print("Enter manufacturer's new name:");
                            String eMName = scanner.nextLine();
                            System.out.print("Enter manufacturer's new country of origin:");
                            String eMCountry = scanner.nextLine();
                            System.out.print("Enter new production year:");
                            int eYear = scanner.nextInt();
                            System.out.print("Enter new production month:");
                            int eMonth = scanner.nextInt();
                            System.out.print("Enter new production day:");
                            int eDay = scanner.nextInt();
                            System.out.print("Enter new price:");
                            int ePrice = scanner.nextInt();
                            ListIterator<Souvenir> iterator = souvenirs.listIterator();
                            while(iterator.hasNext()){
                                Souvenir next = iterator.next();
                                if(next.getName().equals(oSName)&&next.getManufacturer().getName().equals(oMName))
                                    iterator.set(new Souvenir(eSName, new Manufacturer(eMName, eMCountry),
                                            LocalDate.of(eYear,eMonth,eDay),ePrice));
                            }
                        }
                        default -> System.out.println("No such option exists!");
                    }
                    break;
                case "exit": case "x":
                    return;
                case "view": case "v":
                    System.out.print("Enter \"m\" for manufacturer or \"s\" for souvenir:");
                    switch (scanner.nextLine()){
                        case "m" ->{
                            System.out.print("Enter souvenir's name:");
                            String vSName = scanner.nextLine();
                            System.out.println("All souvenirs with this name:");
                            souvenirs.stream().filter(s -> s.getManufacturer().getName().equals(vSName))
                                    .forEach(System.out::println);
                        }
                        case "s" ->{
                            System.out.print("Enter company's name:");
                            String vMName = scanner.nextLine();
                            System.out.println("All companies with this name:");
                            manufacturers.stream().filter(s -> s.getName().equals(vMName))
                                    .forEach(System.out::println);
                        }
                        default -> {
                            System.out.println("No such option exists!");
                        }
                    }
                    break;
                case "view all": case "va":
                    System.out.println("All manufacturers:");
                    manufacturers.forEach(System.out::println);
                    System.out.println("All souvenirs:");
                    souvenirs.forEach(System.out::println);
                    break;
                case "view by country": case "vc":
                    System.out.print("Enter country's name:");
                    String selectedCountry = scanner.nextLine();
                    System.out.println("Souvenirs made in " + selectedCountry);
                    souvenirs.stream().filter(s -> s.getManufacturer().getCountry().equals(selectedCountry))
                            .forEach(System.out::println);
                    break;
                case "view by manufacturer": case "vm":
                    System.out.print("Enter manufacturer's name:");
                    String selectedManufacturer = scanner.nextLine();
                    System.out.println("Souvenirs from " + selectedManufacturer);
                    souvenirs.stream().filter(s -> s.getManufacturer().getName().equals(selectedManufacturer))
                            .forEach(System.out::println);
                    break;
                case "View by price": case "vp":
                    System.out.print("Enter price:");
                    int selectedPrice = scanner.nextInt();
                    System.out.println("Souvenirs below the price of " + selectedPrice);
                    souvenirs.stream().filter(s -> s.getPrice()<selectedPrice)
                            .forEach(System.out::println);
                    break;
                case "save": case "s":
                    ms.write("manufacturers.txt", manufacturers);
                    ss.write("souvenirs.txt", souvenirs);
                    break;
                case "update": case "u":
                    manufacturers = ms.read("manufacturers.txt");
                    souvenirs = ss.read("souvenirs.txt");
                    break;
                case "view by year": case "vy":
                    System.out.print("Enter year:");
                    int selectedYear = scanner.nextInt();
                    System.out.println("Souvenirs made in " + selectedYear);
                    souvenirs.stream().filter(s -> s.getProductionDate().getYear()==selectedYear)
                            .forEach(System.out::println);
                    break;
                case "view by years": case "vys":
                    break;
                default:
                    System.out.println("No such option exists!");
                    break;
            }
        }
    }
}
