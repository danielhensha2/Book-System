import java.util.Scanner;
import java.util.ArrayList;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.sql.SQLException; 

//21434193
//Daniel Osarobo

class Main {
  static final private int PORT = 8080;

  public static void main(String[] args) throws IOException, SQLException {
    

    HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0);
     server.createContext("/", new RootHandler() ); 
    server.createContext("/books", new DisplayLibraryHandler() );
    server.createContext("/delete", new DeleteHandler() ); 
    server.createContext("/add", new AddHandler());
    server.createContext("/processAddBOOK", new ProcessAddBOOKHandler());
    server.setExecutor(null);
    server.start();
    System.out.println("The server is listening on port " + PORT);
      Scanner in = new Scanner(System.in);
      String selection;
      BOOKDao books = new BOOKDao();

      do {
        System.out.println("Book Inventory");
        System.out.println("Choose from these options");
        System.out.println("----------------------");
  
        System.out.println("1 - Retrieve all books");
        System.out.println("2 - Search for book");
        System.out.println("3 - Insert new book into database");
        System.out.println("4 - Update existing book price details");
        System.out.println("5 - Delete book from database");
        
        System.out.println("6 - Exit");
        System.out.println("Enter choice > ");
        System.out.println();
   
        selection = in.nextLine();
  
        switch (selection) {
          case "1":
            System.out.println("Retrieve all books");
            ArrayList<BOOK> allBOOKs = books.getAllBOOKs();
            for (int i =0 ; i < allBOOKs.size(); i++){
              System.out.println(allBOOKs.get(i) );
              }
            System.out.println();
            break;
  
          case "2":
            System.out.println("Search for book");
            System.out.println("\nEnter BOOK ID to get details  ");
             int ID = Integer.parseInt(in.nextLine());
             System.out.println(books.getBOOK(ID));
             System.out.println();
             break;
          
  
          case "3":
          System.out.println("Insert new book into database");  
           BOOK book = createBOOK();
           books.addBOOK(book);
          System.out.println();  
        break;
  
          case "4":
            System.out.println("Update existing book price details");
            System.out.println("\nEnter BOOK ID to update price ");
            int uID = Integer.parseInt(in.nextLine());
            System.out.println(books.getBOOK(uID));
            BOOK updatedBOOK = updateBOOK(books.getBOOK(uID));
            books.updateBOOK(updatedBOOK);
            
            break;
  
          case "5":
            
            System.out.println("Delete book from database");
            System.out.println("Enter ID of BOOK to delete");
            int bID = Integer.parseInt(in.nextLine());
            books.deleteBOOK(bID);
            break; 
          
  
          case "6":
            System.out.println("Exit");
            break;
        }
      } while (!selection.equals("6"));    
    }     




private static BOOK createBOOK() {
    // TODO Auto-generated method stub
    int id;
    String title;
    String  author;
    int year; 
    int edition;
    String publisher;
    String isbn;
    String cover;
    String condition;
    int price;
    String notes;

    Scanner in = new Scanner(System.in);
    System.out.println("Please enter ID");
    id = Integer.parseInt(in.nextLine());
    
    System.out.println("Please enter Title");
    title = in.nextLine();

    System.out.println("Please enter Author");
    author = in.nextLine();

    System.out.println("Please enter year");
    year = Integer.parseInt(in.nextLine());

    System.out.println("Please enter edition");
    edition = Integer.parseInt(in.nextLine());

    System.out.println("Please enter publisher");
    publisher = in.nextLine();

    System.out.println("Please enter isbn");
    isbn = in.nextLine();

    System.out.println("Please enter cover");
    cover = in.nextLine();

    System.out.println("Please enter condition");
    condition = in.nextLine();
    
    System.out.println("Please enter price");
    price = Integer.parseInt(in.nextLine());
  
    System.out.println("Please enter notes");
    notes = in.nextLine();

    return new BOOK(id, title, author, year, edition, publisher, isbn, cover, condition, price, notes);
  }
				




 private static BOOK updateBOOK(BOOK book) {
    // TODO Auto-generated method stub
  
    int id;
    String title;
    String  author;
    int year; 
    int edition;
    String publisher;
    String isbn;
    String cover;
    String condition;
    int price;
    String notes;

    Scanner in = new Scanner(System.in);
    System.out.println("Updating BOOK  price with ID:" + book.getID());
    
   System.out.println("Please enter ID");
    id = Integer.parseInt(in.nextLine());
   
    System.out.println("Please enter Title");
    title = in.nextLine();

    System.out.println("Please enter Author");
    author = in.nextLine();

    System.out.println("Please enter year");
    year = Integer.parseInt(in.nextLine());

    System.out.println("Please enter edition");
    edition = Integer.parseInt(in.nextLine());

    System.out.println("Please enter publisher");
    publisher = in.nextLine();

    System.out.println("Please enter isbn");
    isbn = in.nextLine();

    System.out.println("Please enter cover");
    cover = in.nextLine();

    System.out.println("Please enter condition");
    condition = in.nextLine();
    
    System.out.println("Please enter price");
    price = Integer.parseInt(in.nextLine());
  
    System.out.println("Please enter notes");
    notes = in.nextLine();

    return new BOOK(id,title,author,year,edition,publisher,isbn,cover,condition,price,notes);
  }
   
   
   
   
   
   
  }


