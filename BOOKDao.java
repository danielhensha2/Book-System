import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class BOOKDao {
	
	public BOOKDao() {}
	
	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
		try {
			String dbURL = "jdbc:sqlite:books.sqlite";
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return dbConnection;
		}
	
	public ArrayList<BOOK> getAllBOOKs() throws SQLException {
	System.out.println("Retrieving all books ...");
    ArrayList<BOOK> BOOK = new ArrayList<>();
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM books;";
		ArrayList<BOOK> books = new ArrayList<>();

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			//System.out.println("DBQuery = " + query);
			result = statement.executeQuery(query); // Execute SQL query and record response to string
			while (result.next()) {
        
				int id =  result.getInt("ID");
				String title = result.getString("Title");
			  String author = result.getString("Author");
				int year = result.getInt("Year");
        int edition = result.getInt("Edition");
        String publisher = result.getString("Publisher");
       String isbn =  result.getString("Isbn");
       String cover = result.getString("Cover");
        String condition = result.getString("Condition");
        int price = result.getInt("Price");
        String notes = result.getString("Notes");
        
        books.add(new BOOK(id,title,author,year,edition,publisher,isbn,cover,condition,price,notes));
        
         
			}
		} catch(Exception e) {
			System.out.println("get all books: "+e);
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return books;
	}

  public boolean addBOOK(BOOK in) throws 
  SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		
		String update = "INSERT INTO books (ID, Title, Author, Year, Edition, Publisher, Isbn, Cover, Condition, Price, Notes) VALUES ("+in.getID() + ",'" + 
      in.getTitle()+ "','"  + in.getAuthor()+ "'," + in.getYear()  + "'," + in.getEdition()+"',"+in.getPublisher()+"',"+in.getIsbn() +"'," + in.getCover() + "'," + in.getCondition()+ "'," +in.getPrice()  + "," +in.getNotes() + ");";
		boolean ok = false;
			try {
					dbConnection = getDBConnection();
					statement = dbConnection.createStatement();
					System.out.println(update);
		// execute SQL query
					statement.executeUpdate(update);
					ok = true;
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} finally {
					if (statement != null) {
						statement.close();
					}
					if (dbConnection != null) {
						dbConnection.close();
					}
					
				}
			return ok;
	}

public BOOK getBOOK(int book_id) throws SQLException {

		BOOK temp = null;
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;

		String query = "SELECT * FROM books WHERE ID =" + book_id + ";";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			//System.out.println("DBQuery: " + query);
			// execute SQL query
			result = statement.executeQuery(query);

			while (result.next()) {


				int id = result.getInt("ID");
				String title = result.getString("Title");
				String author = result.getString("Author");
				int year = result.getInt("Year");
        int edition = result.getInt("Edition");
        String publisher = result.getString("Publisher");
        String Isbn = result.getString("Isbn");
        String cover = result.getString("Cover");
        String condition = result.getString("Condition");
        int price = result.getInt("Price");
        String notes = result.getString("Notes");
				
      temp = new BOOK(id,title,author,year,edition,publisher,Isbn,cover,condition,price,notes);

			}
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return temp;
	}

	public Boolean deleteBOOK(int book_id) throws SQLException {
		System.out.println("Deleting book");
		Connection dbConnection = null;
		Statement statement = null;
		int result = 0;
		String query = "DELETE FROM books WHERE ID = " + book_id + ";";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
		  //System.out.println(query);
			// execute SQL query
			result = statement.executeUpdate(query);
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

public Boolean updateBOOK(BOOK book)throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
     		String query = "UPDATE books " + "SET ID = '" + book.getID() + "'," + "Title = '" + book.getTitle() + "'," + "Author = '" + book.getAuthor() + "'," + "Year= '" + book.getYear() + "'," +"Edition = '" + book.getEdition() + " '," + "Publisher = '" + book.getPublisher() + " '," + "Isbn = '" + book.getIsbn() + " '," + "Cover = '" + book.getCover() + "'," + "Condition = '" + book.getCondition() + "'," + "Price = '" + book.getPrice() + " WHERE ID = " + book.getID()
      + ";";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			//System.out.println(query);
			// execute SQL update
			statement.executeUpdate(query);

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			return false;

		} finally {

			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return true;
	}
}

