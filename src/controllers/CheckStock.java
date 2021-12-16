package controllers;
import models.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class CheckStock {
    ArrayList<Stock> stock = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Customer> custys = new ArrayList<>();
    ArrayList<OrderItem> ois = new ArrayList<>();
    ArrayList<Order> bercow = new ArrayList<>();

    User loggedInAs = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CheckStock cs = new CheckStock();
        cs.login();
    }

    public void login() throws IOException, ClassNotFoundException {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("admin", "admin","9999",true, "admin"));
        ArrayList<Customer> custys = new ArrayList<>();
        custys.add(new Customer("Leo Bloom", "dublin5", "lb1@dc.com", "+447521209413", "5 Ginnel Street, DC14"));


        if (Files.exists(Path.of("users.txt"))) {
        loadUser();
    }
        else {
            createUser();
        }

        System.out.println("Hi, please enter your userID to continue:");
        String uid = new Scanner(System.in).nextLine();
        System.out.println("and now your password:");
        String pw = new Scanner(System.in).nextLine();
        for(User u : users){
            if(uid.equals(u.getUserID()) && u.getPassword().equals(pw)){
                System.out.println("Logging in as " + u.getUserID());
                loggedInAs = u;
                userMenu();
                break;
            }

            else {
                System.out.println("Invalid un/pw");
                login();
            }
        }

    }

    private void createUser() throws IOException, ClassNotFoundException {
        System.out.println("Create new account");
        System.out.println("Enter forename");
        String newfor = new Scanner(System.in).nextLine();
        System.out.println("Enter surname");
        String newsur = new Scanner(System.in).nextLine();
        System.out.println("Enter userID");  //IF KNOWN/GENERATE AUTO??????????????????????????????????????????????
        String newuid = new Scanner(System.in).nextLine();
        System.out.println("Enter password");
        String newpass = new Scanner(System.in).nextLine();
        User u = new User(newfor, newsur, newuid, false, newpass);
        users.add(u);
        saveUser();
        userMenu();

    }

    private void userMenu() throws IOException, ClassNotFoundException {
        System.out.println("1 - Change password");
        System.out.println("2 - Check stock");
        System.out.println("3 - Input order");
        System.out.println("4 - View orders");
        if (loggedInAs.isAdmin()) {
            System.out.println("5 - Change user details");
        }
        int choice = Integer.parseInt(new Scanner(System.in).nextLine());
        switch (choice) {
            case 1 :
                changePass();
                break;
            case 2 :
                checkPantry();
                break;
            case 3 :
                addOrder();
                break;
            case 4 :
                viewOrders();
                break;
            case 5 :
                viewUsers();
                changeUsers();
            default:
                System.out.println("???");
                userMenu();
        }
    }

    private void viewOrders() throws IOException, ClassNotFoundException {

        System.out.println("Which order do you want to view?");
        int x = 1;
        for (Order o : bercow) {
            System.out.println(x + " - " + o.getCusID() + " : $" + o.getPrice());
            x++;
        }
        System.out.println(x + " - Go back");
        int choice = Integer.parseInt(new Scanner(System.in).nextLine());
        viewOrderChoice(choice);
    }

    private void viewOrderChoice(int choice) throws IOException, ClassNotFoundException {
        if (choice > bercow.size()) {
            userMenu();
        }
        Order o = bercow.get(choice - 1);
        //from order we need name, add, total cost
        System.out.println(o.toString());
        userMenu();
    }


    private void addOrder() throws IOException, ClassNotFoundException {

        System.out.println("Input Product ID");
        int addProductID = new Scanner(System.in).nextInt();
        System.out.println("Input quantity");
        int addOrderQuantity = new Scanner(System.in).nextInt();
        OrderItem oi = new OrderItem(addProductID, addOrderQuantity);
        ois.add(oi);
        for (Stock j : stock) {
            for (OrderItem o : ois)
            if (j.getProductId() == (o.getProductID())) {
               double cost1 = (j.getPrice() * o.getOrderQuantity());
               o.setPrice(cost1);
               o.setUnitCost(j.getPrice());
               o.setProductName(j.getProductName());
           }
       }
        System.out.println("Total so far : $" + getCost());
        System.out.println("Anything else? (y/n)");
        String addyn = new Scanner(System.in).nextLine();
        if (Objects.equals(addyn, "y")) {
            addOrder();
        }
        System.out.println("Who's ordering? Input Customer ID");
        String addcusID = new Scanner(System.in).nextLine();
        Order o = new Order(addcusID, ois);
        bercow.add(o);
        custys.add(new Customer("Leo Bloom", "dublin5", "lb1@dc.com", "+447521209413", "5 Ginnel Street, DC14"));
        for (Customer c : custys) {
            for (Order b : bercow)
                if (c.getCusID().equalsIgnoreCase(addcusID)) {
                    b.setCusName(c.getCusName());
                    b.setCusAdd(c.getCusadd());
                }
        }
        o.setPrice(getCost());
        userMenu();
    }

    public double getCost() {
        double total = 0;
        for (int i = 0; i < ois.size(); i++) {
            total += ois.get(i).getPrice();
        }
        return total;
    }

    private void viewUsers() {
        for (User u : users) {
            System.out.println(u.getForename() + " " + u.getSurname() + " : " + u.getUserID() + " " + u.getPassword()
             + " : Admin: " + u.isAdmin());
        }
    }

    private void changeUsers() throws IOException, ClassNotFoundException {
            System.out.println("Which account do you want to change?");
            int x = 1;
            for (User a : users) {
                System.out.println(x + " - " + a.getUserID());
                x++;
            }
            System.out.println(x + " - Go back");
            int choice = Integer.parseInt(new Scanner(System.in).nextLine());
            changeUserChoice(choice);
        }

    private void changeUserChoice(int choice) throws IOException, ClassNotFoundException {
        if (choice > users.size()) {
            userMenu();
        }
        User a = users.get(choice - 1);
        System.out.println("Changing password of user " + a.getUserID());
        System.out.println("Enter new password");
        String newadminpass = (new Scanner(System.in).nextLine());
        a.setPassword(newadminpass);
        System.out.println("Password changed");
        saveUser();
        userMenu();

    }

    private void changePass() throws IOException, ClassNotFoundException {
        System.out.println("Enter old password");
        String pw = new Scanner(System.in).nextLine();
        if (pw.equals(loggedInAs.getPassword())) {
            System.out.println("Change password");
            String newpass = (new Scanner(System.in).nextLine());
            loggedInAs.setPassword(newpass);
            System.out.println("Password changed");
            saveUser();
        } else {
            System.out.println("Invalid pw");
        }
        userMenu();
    }

    private void checkPantry() throws IOException, ClassNotFoundException {
        System.out.println("Yo here's what we got on the shelves");
        if (Files.exists(Path.of("stock.txt"))) {
            loadStock();
            for (Stock j : stock) {
                System.out.println(j.getProductName() + " : " + j.getProductQuantity() + " : " + j.getProductId() +
                        " : " + j.getSource() + " : " + j.getPrice());
            }
        }
        else {
            System.out.println("Nothing");
        }
        menu1();


    }

    private void menu1() throws IOException, ClassNotFoundException {
        System.out.println("What would you like to do?");
        System.out.println("1 - Add stock");
        System.out.println("2 - Remove stock");
        System.out.println("3 - Update stock");
        System.out.println("4 - Exit");
        int choice = Integer.parseInt(new Scanner(System.in).nextLine());
        switch (choice) {
            case 1:
                addStock();
                break;

            case 2:
                removeStock();
                break;

            case 3:
                amendStock();
                break;

            case 4:
                userMenu();
                break;
        }

    }

    private void addStock() throws IOException, ClassNotFoundException {
        System.out.println("Enter product name");
        Scanner addName = new Scanner(System.in);
        String name = (addName.nextLine());

        System.out.println("Enter quantity of " + name);
        Scanner addQuantity = new Scanner(System.in);
        int quantity = (addQuantity.nextInt());

        System.out.println("Where'd you get it at?");
        Scanner addSource = new Scanner(System.in);
        String source = (addSource.nextLine());

        System.out.println("Enter product ID number");
        Scanner addID = new Scanner(System.in);
        int ID = (addID.nextInt());

        System.out.println("Enter price");
        Scanner addPrice = new Scanner(System.in);
        double price = (addPrice.nextDouble());

        Stock s = new Stock(name, quantity, source, ID, price);
        stock.add(s);
        saveStock();
        menu1();
    }

    private void removeStock() throws IOException, ClassNotFoundException {
        checkPantry();
        System.out.println("Enter product ID to remove from stock");
        Scanner reID = new Scanner(System.in);
        int remID = (reID.nextInt());
        for (int i = 0; i < stock.size(); i++) {
            Stock j = stock.get(i);
            if (j.getProductId() == (remID)) {
                stock.remove(i);
            }
        }
        saveStock();
        menu1();
    }

    private void amendStock() throws IOException, ClassNotFoundException {
        System.out.println("What do you want to change?");
        int x = 1;
        for (Stock s : stock) {
            System.out.println(x + " - " + s.getProductName());
            x++;
        }
        System.out.println(x + " - Go back");
        int choice = Integer.parseInt(new Scanner(System.in).nextLine());
        amendStockChoice(choice);

    }

    private void amendStockChoice(int choice) throws IOException, ClassNotFoundException {
        Stock s = stock.get(choice - 1);
        System.out.println(s.getProductName() + " : " + s.getProductQuantity() + " : " + s.getSource() + " : " + s.getProductId());
        System.out.println("What do you want to change?");
        System.out.println("1 - Product name");
        System.out.println("2 - Quantity");
        System.out.println("3 - Source");
        System.out.println("4 - Product ID");
        System.out.println("5 - Price");
        System.out.println("6 - Exit");
        getStockEdit(s, Integer.parseInt(new Scanner(System.in).nextLine()));
    }

    private void getStockEdit(Stock s, int i) throws IOException, ClassNotFoundException {
        switch (i) {
            case 1:
                System.out.println("Enter new name");
                String newName = new Scanner(System.in).nextLine();
                s.setProductName(newName);
                saveStock();
                menu1();

            case 2:
                System.out.println("Enter new quantity");
                int newQuant = new Scanner(System.in).nextInt();
                s.setProductQuantity(newQuant);
                saveStock();
                menu1();

            case 3:
                System.out.println("Enter new source");
                String newSource = new Scanner(System.in).nextLine();
                s.setSource(newSource);
                saveStock();
                menu1();

            case 4:
                System.out.println("Enter new product ID");
                int newID = new Scanner(System.in).nextInt();
                s.setProductID(newID);
                saveStock();
                menu1();

            case 5:
                System.out.println("Enter new price");
                double newPrice = new Scanner(System.in).nextDouble();
                s.setPrice(newPrice);
                saveStock();
                menu1();

            default:
                menu1();
        }
    }

    private void saveStock() throws IOException {
        FileOutputStream file = new FileOutputStream("stock.txt");
        ObjectOutputStream output = new ObjectOutputStream(file);
        output.writeObject(stock);
        output.flush();
        output.close();
    }

    private void loadStock() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream("stock.txt");
        ObjectInputStream input = new ObjectInputStream(file);
        stock = (ArrayList<Stock>) input.readObject();
        input.close();

    }

    private void saveUser() throws IOException {
        FileOutputStream file = new FileOutputStream("users.txt");
        ObjectOutputStream output = new ObjectOutputStream(file);
        output.writeObject(users);
        output.flush();
        output.close();
    }

    private void loadUser() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream("users.txt");
        ObjectInputStream input = new ObjectInputStream(file);
        users = (ArrayList<User>) input.readObject();
        input.close();
    }
}




