import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<ArrayList<Object>> employees = new ArrayList<>(); //1 - Employee number, 2 - Employee access level
    static ArrayList<ArrayList<Object>> heretics = new ArrayList<>(); //1 - heretic id, 2 - faith percentage
    static ArrayList<ArrayList<Object>> captainOrders = new ArrayList<>(); //1 - directive number, 2 - directive type(1 - terminate, 2 - read sermon to, 3 - clean), 3 - directive parameters, 4 - directive text

    public static void main(String[] args) {
    }

    //Employees

    public void checkHeretics(Integer hereticID, int requiredAmount){
        if(requiredAmount > heretics.size())
            System.out.printf("Warning!!!! There are some escaped prisoners (%d outlaws)", requiredAmount - heretics.size());
        heretics.forEach(p -> {
            if((int)p.get(0) == hereticID)
                if((double)p.get(1) == 100)
                    System.out.printf("Heretic with id %d has chosen the right way of soul safety", (int)p.get(0));
        });
    }
    public static void ListenToTheCaptain(){
        if(captainOrders.isEmpty())
            System.out.println("There aren't any new orders from the command for now. Continue doing your duty");
        else{
            System.out.printf("\n!!!!!!Caution, comrade, there are %d new orders in our prison", captainOrders.size());
            captainOrders.forEach(p -> {
                System.out.println("\t" + ((Integer)p.getFirst()).toString() + " -- " + (String)p.get(3) + "task type: " + ((Integer)p.get(1)).toString());
            });
            System.out.println("\t--------------------------------------------------");
        }
    }

    public void ListenToTerminationOrders(){
        ArrayList<ArrayList<Object>> requiredOrders = new ArrayList<>();
        captainOrders.forEach(o -> {
            if(((Integer)o.get(1)).equals(1))
                requiredOrders.add(o);
        });
        if(captainOrders.isEmpty() || requiredOrders.isEmpty())
            System.out.println("There aren't any new orders from the command for now. Continue doing your duty");
        else{
            System.out.printf("\n!!!!!!Caution, comrade, there are %d new orders in our prison", captainOrders.size());
            requiredOrders.forEach(p -> {
                System.out.println("\t" + ((Integer)p.getFirst()).toString() + " -- " + (String)p.get(3) + "task type: " + ((Integer)p.get(1)).toString());
            });
            System.out.println("\t--------------------------------------------------");
        }
    }
    public void ListenToEnlighteningOrders(){
        ArrayList<ArrayList<Object>> requiredOrders = new ArrayList<>();
        captainOrders.forEach(o -> {
            if(((Integer)o.get(2)).equals(1))
                requiredOrders.add(o);
        });
        if(captainOrders.isEmpty() || requiredOrders.isEmpty())
            System.out.println("There aren't any new orders from the command for now. Continue doing your duty");
        else{
            System.out.printf("\n!!!!!!Caution, comrade, there are %d new orders in our prison", captainOrders.size());
            requiredOrders.forEach(p -> {
                System.out.println("\t" + ((Integer)p.getFirst()).toString() + " -- " + (String)p.get(3) + "task type: " + ((Integer)p.get(1)).toString());
            });
            System.out.println("\t--------------------------------------------------");
        }
    }
    public void ListenToCleaningOrders(){
        ArrayList<ArrayList<Object>> requiredOrders = new ArrayList<>();
        captainOrders.forEach(o -> {
            if(((Integer)o.get(3)).equals(1))
                requiredOrders.add(o);
        });
        if(captainOrders.isEmpty() || requiredOrders.isEmpty())
            System.out.println("There aren't any new orders from the command for now. Continue doing your duty");
        else{
            System.out.printf("\n!!!!!!Caution, comrade, there are %d new orders in our prison", captainOrders.size());
            requiredOrders.forEach(p -> {
                System.out.println("\t" + ((Integer)p.getFirst()).toString() + " -- " + (String)p.get(3) + "task type: " + ((Integer)p.get(1)).toString());
            });
            System.out.println("\t--------------------------------------------------");
        }
    }

    public static void CheckPossibilities(Integer employeeId){
        employees.forEach(e -> {
            if(((Integer)e.getFirst()).equals(employeeId))
                switch((Integer)e.getLast()){
                    case 0:
                        System.out.println("You're just a cleaner, you can only clean our beautiful prison from the results of leading prisoners to the right way...");
                        break;
                    case 1:
                        System.out.println("You're one of the most important gear wheal in our prison's mechanism. You should enlighten heretics and make them follow the right way by reading them sermons");
                        break;
                    case 2:
                        System.out.println("You're the closest to the captain person, you're the law in our prison. Well, your task is o look for prisoners and punish the for heretic actions. You also can read them sermons to enlighten heretics");
                }
        });
    }

    public static void ExecuteOrder(Integer employeeID, Integer orderID){
        AtomicReference<Integer> currentAccessLevel = new AtomicReference<Integer>(null);
        AtomicReference<ArrayList<Object>> currentOrder = new AtomicReference<>(null);
        AtomicBoolean employeeExists = new AtomicBoolean(false);
        employees.forEach(e -> {
            if(((Integer)e.getFirst()).equals(employeeID)){
                employeeExists.set(true);
                currentAccessLevel.set((Integer) e.getLast());
            }
        });
        if(!employeeExists.get() || currentAccessLevel.get() == null){
            System.out.println("There aren`t employee with these personal ID");
            return;
        }
        captainOrders.forEach(p -> {
            if(((Integer)p.getFirst()).equals(orderID))
                currentOrder.set(p);
        });
        if(currentOrder.get() == null){
            System.out.println("Order with this id doesn't exist");
            return;
        }
        switch ((Integer)currentOrder.get().get(1)){
            case 1:
                if(currentAccessLevel.get() < 2){
                    System.out.println("You don't have high enough access level to complete order");
                    break;
                }
                //Unchecked cast
                List<Integer> hereticsIDs = (List<Integer>)currentOrder.get().get(2);
                ArrayList<Object> hereticsToDelete= new ArrayList<>();
                heretics.forEach(h -> {
                    hereticsIDs.forEach(id -> {
                        if (((Integer) h.getFirst()).equals(id))
                            hereticsToDelete.add(h);
                    });
                });
                hereticsToDelete.forEach(h -> {
                    try {
                        heretics.remove(h);
                    }
                    catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }
                });
                break;
            case 2:
                if(currentAccessLevel.get() < 1){
                    System.out.println("You don't have high enough access level to complete order");
                    break;
                }
                List<Integer> hereticsIDsToEnlighten = (List<Integer>)currentOrder.get().get(2);
                ArrayList<Object> hereticsToEnlighten= new ArrayList<>();
                heretics.forEach(h -> {
                    hereticsIDsToEnlighten.forEach(id -> {
                        if (((Integer) h.getFirst()).equals(id))
                            hereticsToEnlighten.add(h);
                    });
                });
                hereticsToEnlighten.forEach(h -> {
                    try {
                        Integer index = heretics.indexOf(h);
                        Double faith = (double)heretics.get(index).get(1);
                        heretics.get(index).set(1,faith + 12);
                    }
                    catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }
                });
                break;
            case 3:
                System.out.println("Cleaning...");
                System.out.println("Cleaning completed");
                break;
        }
    }

    //Prisoners

        //Basic prisoner
    public void WorkAtFactory(Integer prisonerID){
        AtomicInteger prisonerIndex = new AtomicInteger(-1);
        AtomicReference<Double> faith = new AtomicReference<>(null);
        heretics.forEach(h -> {
            if(prisonerID.equals(h.getFirst())) {
                prisonerIndex.set(heretics.indexOf(h));
                faith.set((double)h.getLast());
            }
        });
        if(prisonerID>=0 && faith.get() != null){
            heretics.get(prisonerIndex.get()).set(1, faith.get()-4);
        }
    }

        //ConvincedPrisoner
    public void WorkForSalvation(){
        ArrayList<Object> hereticsToEnlighten= new ArrayList<>();
        hereticsToEnlighten.addAll(0, heretics);

        hereticsToEnlighten.forEach(h -> {
            try {
                Integer index = heretics.indexOf(h);
                Double faith = (double)heretics.get(index).get(1);
                heretics.get(index).set(1,faith + 4);
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        });
    }

        //Absolut Heretic
    public void WorkForSanity(){
        ArrayList<Object> hereticsToEnlighten= new ArrayList<>();
        hereticsToEnlighten.addAll(0, heretics);

        hereticsToEnlighten.forEach(h -> {
            try {
                Integer index = heretics.indexOf(h);
                Double faith = (double)heretics.get(index).get(1);
                heretics.get(index).set(1,faith - 5);
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        });
    }

    //Captain
    public void CreateNewOrder(){
        ArrayList<Object> newOrder = new ArrayList<>();
        newOrder.addFirst(((Integer)captainOrders.getLast().getFirst())+1);
        System.out.println("Select the order type\n(\n\t1 - termination\n\t2 - read sermons\n\t3 - clean\n)\n");
        Integer orderType = sc.nextInt();
        sc.nextLine();
        while(orderType>3 || orderType<1){
            System.out.println("Incorrect operation. Reenter the operation number");
            orderType = sc.nextInt();
            sc.nextLine();
        }
        newOrder.add(orderType);
        System.out.println("Enter the guilty heretics IDs");
        ArrayList<Integer> heretics = new ArrayList<>();
        do {
            heretics.add(sc.nextInt());
            sc.nextLine();
            System.out.println("Stop adding heretics (y/n)");
        } while (!sc.nextLine().equalsIgnoreCase("y"));
        newOrder.add(heretics.stream().distinct().collect(Collectors.toList()));
        System.out.println("Enter order text");
        newOrder.add(sc.nextLine());
        sc.nextLine();
        captainOrders.add(newOrder);
    }

    public void DeleteOrder(Integer orderID){
        AtomicReference<Integer> orderToDeleteIndex = new AtomicReference<>(null);
        captainOrders.forEach(o -> {
            if(orderID.equals((int)o.getFirst()))
                orderToDeleteIndex.set(captainOrders.indexOf(o));
        });
        if(orderToDeleteIndex.get() == null)
            System.out.print("Order with such ID doesn't exist");
        else {
            captainOrders.remove(orderToDeleteIndex.get());
        }

    }

    public void RaiseEmployee(){
        AtomicInteger employeeIndex = new AtomicInteger(-1);
        AtomicInteger accessLevel = new AtomicInteger(-1);
        employees.forEach(e -> {
            if(((int)e.getLast()) < 3)
                System.out.printf("%d, Access level - %d", (int)e.getFirst(), (int)e.getLast());
        });
        System.out.println("Input employee ID");
        Integer employeeID = sc.nextInt();
        sc.nextLine();
        employees.forEach(e -> {
            if(employeeID.equals(e.getFirst()) && (int)e.getLast()<3){
                employeeIndex.set(employees.indexOf(e));
                accessLevel.set((int)e.getLast());
            }
        });
        if(employeeIndex.get() == -1 || accessLevel .get() == -1){
            System.out.println("Incorrect ID");
            return;
        }
        employees.get(employeeIndex.get()).set(1,accessLevel.get()+1);
    }
    public void LowerEmployee(){
        AtomicInteger employeeIndex = new AtomicInteger(-1);
        AtomicInteger accessLevel = new AtomicInteger(-1);
        employees.forEach(e -> {
            if(((int)e.getLast()) > 1)
                System.out.printf("%d, Access level - %d", (int)e.getFirst(), (int)e.getLast());
        });
        System.out.println("Input employee ID");
        Integer employeeID = sc.nextInt();
        sc.nextLine();
        employees.forEach(e -> {
            if(employeeID.equals(e.getFirst()) && (int)e.getLast()>1){
                employeeIndex.set(employees.indexOf(e));
                accessLevel.set((int)e.getLast());
            }
        });
        if(employeeIndex.get() == -1 || accessLevel .get() == -1){
            System.out.println("There isn't employee with such ID");
            return;
        }
        employees.get(employeeIndex.get()).set(1,accessLevel.get()+1);
    }
}