package models;

import interfaces.Captain;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class CaptainModel extends PrisonPerson implements Captain {
    static Scanner sc = new Scanner(System.in);
    @Override
    public void CreateNewOrder(ArrayList<ArrayList<Object>> captainOrders) {
        ArrayList<Object> newOrder = new ArrayList<>();
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

    @Override
    public void DeleteOrder(Integer orderID, ArrayList<ArrayList<Object>> captainOrders) {
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

    @Override
    public void RaiseEmployee(ArrayList<ArrayList<Object>> employees) {
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

    @Override
    public void LowerEmployee(ArrayList<ArrayList<Object>> employees) {
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
