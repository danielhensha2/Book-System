import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class ProcessAddBOOKHandler implements HttpHandler{
  public void handle(HttpExchange he) throws IOException {
   
    System.out.println("ProcessAddBOOKHandler Called");
    he.sendResponseHeaders(200,0);
    BufferedWriter out = new BufferedWriter(  
        new OutputStreamWriter(he.getResponseBody() ));
    
    // Get param from URL
    Map <String,String> parms = Util.requestStringToMap
    (he.getRequestURI().getQuery());

    // print the params for debugging 
    System.out.println(parms);

    //get ID number
  

    BOOKDao books = new BOOKDao();

    System.out.println("about to get data");

    String title = parms.get("title");
    String author = parms.get("author");
    int year = Integer.parseInt(parms.get("year"));
    int edition = Integer.parseInt(parms.get("edition"));
    String publisher = parms.get("publisher");
    String isbn = parms.get("isbn");
    String cover =  parms.get("cover");
    String condition = parms.get("condition");
    int price = Integer.parseInt(parms.get("price"));
    String notes = parms.get("notes");
    int ID = Integer.parseInt(parms.get("id"));
   
    System.out.println("about to create book"); // Debugging message 
    BOOK book = new BOOK(ID,title,author,year,edition,publisher,isbn,cover,condition,price,notes);
    System.out.println("BOOK to Add" + book);

    try {  
    books.addBOOK(book); // add to database 
      

     out.write(
      "<html>" +
      "<head> <title>BOOK Library</title> "+
         "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
      "</head>" +
      "<body>" +
      "<h1> BOOK Added</h1>"+
      "<table class=\"table\">" +
      "<thead>" +
      "  <tr>" +
      "    <th>ID</th>" +
      "    <th>Title</th>" +
      "    <th>Author</th>" +
      "    <th>Year</th>" +
      "    <th>Edition</th>" +
      "    <th>Publisher</th>" +
      "    <th>Isbn</th>" +
      "    <th>Cover</th>" +
      "    <th>Condition</th>" +
      "    <th>Price</th>" +
      "    <th>Notes</th>" +
    
      "  </tr>" +
      "</thead>" +
      "<tbody>");

      
        out.write(
      "  <tr>"       +
      "    <td>"+ book.getID() + "</td>" +
      "    <td>"+ book.getTitle() + "</td>" +
      "    <td>"+ book.getAuthor() + "</td>" +
      "    <td>"+ book.getYear() + "</td>" +
      "    <td>"+ book.getEdition() + "</td>" +
      "    <td>"+ book.getPublisher() + "</td>" +
      "    <td>"+ book.getIsbn() + "</td>" +
      "    <td>"+ book.getCover() + "</td>" +
      "    <td>"+ book.getCondition() + "</td>" +
      "    <td>"+ book.getPrice() + "</td>" +
      "    <td>"+ book.getNotes() + "</td>" +
      "  </tr>" 
        );
   
      out.write(
      "</tbody>" +
      "</table>" +
      "<a href=\"/\">Back to List </a>"+
      "</body>" +
      "</html>");
   }catch(SQLException se){
      System.out.println(se.getMessage());
    }
  
    out.close();

}
}
