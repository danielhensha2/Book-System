import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class DeleteHandler implements HttpHandler{
  public void handle(HttpExchange he) throws IOException {
   
    System.out.println("DeleteHandler Called");
    he.sendResponseHeaders(200,0);
    BufferedWriter out = new BufferedWriter(  
        new OutputStreamWriter(he.getResponseBody() ));
    
    // Get param from URL
    Map <String,String> parms = Util.requestStringToMap
    (he.getRequestURI().getQuery());

    // print the params for debugging 
    System.out.println(parms);

    //get ID number
    int ID = Integer.parseInt(parms.get("id"));

    BOOKDao books = new BOOKDao();

    

    try{
      // get the dvd details before we delete from the Database
      BOOK deletedBOOK = books.getBOOK(ID);
      // actually delete from database;
      books.deleteBOOK(ID);
      

     out.write(
      "<html>" +
      "<head> <title>DVD Library</title> "+
         "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
      "</head>" +
      "<body>" +
      "<h1> BOOK Deleted</h1>"+
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
      "    <td>"+ deletedBOOK.getID() + "</td>" +
      "    <td>"+ deletedBOOK.getTitle() + "</td>" +
      "    <td>"+ deletedBOOK.getAuthor() + "</td>" +
      "    <td>"+ deletedBOOK.getYear() + "</td>" +
      "    <td>"+ deletedBOOK.getEdition() + "</td>" +  
      "    <td>"+ deletedBOOK.getPublisher() + "</td>" +
      "    <td>"+ deletedBOOK.getIsbn() + "</td>" +
      "    <td>"+ deletedBOOK.getCover() + "</td>" +
      "    <td>"+ deletedBOOK.getCondition() + "</td>" +
      "    <td>"+ deletedBOOK.getPrice() + "</td>" +
      "    <td>"+ deletedBOOK.getNotes() + "</td>" +
      "  </tr>" 
        );
      
      out.write(
      "</tbody>" +
      "</table>" +
      "<a href=\"/\">Back to Menu </a>"+
      "</body>" +
      "</html>");
     }catch(SQLException se){
      System.out.println(se.getMessage());
    }
    out.close();

}
}
