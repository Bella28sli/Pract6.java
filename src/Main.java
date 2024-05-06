import models.CaptainModel;
import models.EmployeeModel;
import models.PrisonerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


public class Main {
    static ArrayList<ArrayList<Object>> employees = new ArrayList<>(); //1 - Employee number, 2 - Employee access level
    static ArrayList<ArrayList<Object>> heretics = new ArrayList<>(); //1 - heretic id, 2 - faith percentage
    static ArrayList<ArrayList<Object>> captainOrders = new ArrayList<>(); //1 - directive number, 2 - directive type(1 - terminate, 2 - read sermon to, 3 - clean), 3 - directive parameters, 4 - directive text
    static PrisonerModel prisoner = new PrisonerModel();
    static CaptainModel captain = new CaptainModel();
    static EmployeeModel employee = new EmployeeModel();

    public static void main(String[] args) {

        prisoner.WorkAtFactory(1, heretics);
        employee.checkHeretics(1, 2, heretics);

        captain.CreateNewOrder(captainOrders);

        System.out.println(captainOrders);
    }
}