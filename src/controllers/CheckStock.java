package controllers;
import models.Stock;
import models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class CheckStock {
    public ArrayList<Stock> stock = new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
CheckStock cs = new CheckStock();
cs.login();

    }

    public void login() throws IOException, ClassNotFoundException {
        ArrayList<User> users = new ArrayList<>();

            users.add(new User("Tom", "Lehane","1111",true, "pass"));
            users.add(new User("James", "Joyce", "1112", false, "jj1"));


            System.out.println("Hi, please enter your userID to continue:");
            String uid = new Scanner(System.in).nextLine();
            System.out.println("and now your password:");
            String pw = new Scanner(System.in).nextLine();
            for(User u : users){
                if(uid.equals(u.getUserID()) && u.getPassword().equals(pw)){
                    System.out.println("yo");
                    checkPantry();
                    break;
                }

                else {
                    System.out.println("Invalid un/pw");
                }
            }

    }


    private void checkPantry() throws IOException, ClassNotFoundException {
        System.out.println("Yo here's what we got on the shelves");
        load();
        for (Stock j : stock) {
            System.out.println(j.toString());
        }
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

        stock.add(new Stock(name, quantity, source, ID));
        save();
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
        save();
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
        System.out.println("5 - Exit");
        getStockEdit(s, Integer.parseInt(new Scanner(System.in).nextLine()));
    }

    private void getStockEdit(Stock s, int i) throws IOException, ClassNotFoundException {
        switch (i) {
            case 1:
                System.out.println("Enter new name");
                String newName = new Scanner(System.in).nextLine();
                s.setProductName(newName);
                save();
                menu1();

            case 2:
                System.out.println("Enter new quantity");
                int newQuant = new Scanner(System.in).nextInt();
                s.setProductQuantity(newQuant);
                save();
                menu1();

            case 3:
                System.out.println("Enter new source");
                String newSource = new Scanner(System.in).nextLine();
                s.setSource(newSource);
                save();
                menu1();

            case 4:
                System.out.println("Enter new product ID");
                int newID = new Scanner(System.in).nextInt();
                s.setProductID(newID);
                save();
                menu1();
        }
    }

    private void save() throws IOException {
        FileOutputStream file = new FileOutputStream("stock.txt");
        ObjectOutputStream output = new ObjectOutputStream(file);
        output.writeObject(stock);
        output.flush();
        output.close();
    }

    private void load() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream("stock.txt");
        ObjectInputStream input = new ObjectInputStream(file);
        stock = (ArrayList<Stock>) input.readObject();
        input.close();

    }
}



