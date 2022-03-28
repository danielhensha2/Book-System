import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;

public class DisplayLibraryHandler implements HttpHandler{
  public void handle(HttpExchange he) throws IOException {

    he.sendResponseHeaders(200,0);
    BufferedWriter out = new BufferedWriter(  
        new OutputStreamWriter(he.getResponseBody() ));
    
    BOOKDao books = new BOOKDao();
    try{
    ArrayList<BOOK> allBOOKS = books.getAllBOOKs();
   
    out.write(
      "<html>" +
      "<head> <title>BOOK Library</title> "+
         "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
      "</head>" +
      "<body>" +
      "<div class=\"container\">" +
      "<h1> BOOKs in Stock!</h1>"+
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
      "    <th>Delete</th" +
      "  </tr>" +
      "</thead>" +
      "<tbody>");

      for (BOOK b : allBOOKS){
        out.write(
      "  <tr>"       +
      "    <td>"+ b.getID() + "</td>" +
      "    <td>"+ b.getTitle() + "</td>" +
      "    <td>"+ b.getAuthor() + "</td>" +
      "    <td>"+ b.getYear() + "</td>" +
      "    <td>"+ b.getEdition() + "</td>" +
      "    <td>"+ b.getPublisher() + "</td>" +
      "    <td>"+ b.getIsbn() + "</td>" +
      "    <td>"+ b.getCover() + "</td>" +
      "    <td>"+ b.getCondition() + "</td>" +
      "    <td>"+ b.getPrice() + "</td>" +
      "    <td>"+ b.getNotes() + "</td>" +
      "    <td> <a href=\"/delete?id=" + b.getID() + "\"> delete </a> </td>" +  
      "  </tr>" 
        );
      }
      out.write(
      "</tbody>" +
      "</table>" +
     "<a href=\"/\">Back to Menu </a>"+
      "</div>" +   
             
      "</body>" +
      "</html>");
     }catch(SQLException se){
      System.out.println(se.getMessage());
    }
    out.close();

  }

}