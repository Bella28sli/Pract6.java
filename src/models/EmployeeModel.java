package models;


import interfaces.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class EmployeeModel extends PrisonPerson implements Employee {

    @Override
    public void checkHeretics(Integer hereticID, int requiredAmount, ArrayList<ArrayList<Object>> heretics) {
        if(requiredAmount > heretics.size())
            System.out.printf("Warning!!!! There are some escaped prisoners (%d outlaws)", requiredAmount - heretics.size());
        heretics.forEach(p -> {
            if((int)p.get(0) == hereticID)
                if((double)p.get(1) == 100)
                    System.out.printf("Heretic with id %d has chosen the right way of soul safety", (int)p.get(0));
        });
    }

    @Override
    public void ListenToTheCaptain(ArrayList<ArrayList<Object>> captainOrders) {
        if(captainOrders.isEmpty())
            System.out.println("There aren't any new orders from the command for now. Continue doing your duty");
        else{
            System.out.printf("\n!!!!!!Caution, comrade, there are %d new orders in our prison", captainOrders.size());
            captainOrders.forEach(p -> {
                System.out.println("\t" + ((Integer)p.get(0)).toString() + " -- " + (String)p.get(3) + "task type: " + ((Integer)p.get(1)).toString());
            });
            System.out.println("\t--------------------------------------------------");
        }
    }

    @Override
    public void ListenToTerminationOrders(ArrayList<ArrayList<Object>> captainOrders) {
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
                System.out.println("\t" + ((Integer)p.get(0)).toString() + " -- " + (String)p.get(3) + "task type: " + ((Integer)p.get(1)).toString());
            });
            System.out.println("\t--------------------------------------------------");
        }
    }

    @Override
    public void ListenToEnlighteningOrders(ArrayList<ArrayList<Object>> captainOrders) {
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
                System.out.println("\t" + ((Integer)p.get(0)).toString() + " -- " + (String)p.get(3) + "task type: " + ((Integer)p.get(1)).toString());
            });
            System.out.println("\t--------------------------------------------------");
        }
    }

    @Override
    public void ListenToCleaningOrders(ArrayList<ArrayList<Object>> captainOrders) {
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
                System.out.println("\t" + ((Integer)p.get(0)).toString() + " -- " + (String)p.get(3) + "task type: " + ((Integer)p.get(1)).toString());
            });
            System.out.println("\t--------------------------------------------------");
        }
    }

    @Override
    public void CheckPossibilities(Integer employeeId, ArrayList<ArrayList<Object>> employees) {
        employees.forEach(e -> {
            if(((Integer)e.get(0)).equals(employeeId))
                switch((Integer)e.get(0)){
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

    @Override
    public void ExecuteOrder(Integer employeeID, Integer orderID, ArrayList<ArrayList<Object>> employees, ArrayList<ArrayList<Object>> captainOrders, ArrayList<ArrayList<Object>> heretics) {
        AtomicReference<Integer> currentAccessLevel = new AtomicReference<Integer>(null);
        AtomicReference<ArrayList<Object>> currentOrder = new AtomicReference<>(null);
        AtomicBoolean employeeExists = new AtomicBoolean(false);
        employees.forEach(e -> {
            if(((Integer)e.get(0)).equals(employeeID)){
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
}
