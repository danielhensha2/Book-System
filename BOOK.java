//21434193
//Daniel Osarobo
public class BOOK {

private int book_id;
private String title;
private String author;
private int year;
private int edition;
private String publisher;
private String isbn;
private String cover;
private String condition;
private int price;
private String notes; 
  


public BOOK(int ID, String title, String author, int year, int edition, String publisher, String isbn, String cover, String condition, int price, String notes) {

  this.book_id = ID;
  this.title = title;
  this.author = author;
  this.year = year;
  this.edition = edition;
  this.publisher = publisher;
  this.isbn = isbn;
  this.cover = cover;
  this.condition = condition;
  this.price = price;
  this.notes = notes;  
}
  public int getID() {
    return this.book_id;
 }
   public void setID ( int book_id) {
     this.book_id = book_id;
   }
  public String getTitle() {
    return this.title;
  } 
 public void setTitle ( String title) {
   this.title = title;
 }
 public String getAuthor () {
   return this.author;
 }
  public void setAuthor (String author) {
    this.author = author;
  }
  public int getYear() {
    return this.year;
 }
  public void setYear (int year) {
    this.year = year;
   }
  public int getEdition() {
   return this.edition;
  }
  public void setEdition (int edition){
    this.edition = edition;
  }
  public String getPublisher() {
    return this.publisher;
  } 
 public void setPublsiher ( String publisher) {
   this.publisher = publisher;
   }
 public String getIsbn () {
   return this.isbn;
 }
  public void setIsbn (String isbn) {
    this.isbn = isbn;
  }
  public String getCover() {
    return this.cover;
  }
  public void setCover (String cover) {
    this.cover = cover;
  }
  public String getCondition() {
    return this.condition;
  }
  public void setCondition (String condition) {
    this.condition = condition; 
  }
  public int getPrice() {
    return this.price;
  }
  public void setPrice (int price) {
    this.price = price;
  }
 public String getNotes() {
   return this.notes;
 }
  public void setNotes (String notes) {
    this.notes = notes;
  }
  @Override
	public String toString() {
		return "BOOK [ID=" + this.book_id + ", Title=" + this.title + ", Author=" + this.author + ", Year=" + this.year + ", Edition=" + this.edition + ", Publisher=" + this.publisher +", Isbn=" + this.isbn +", Cover=" + this.cover +", Condition=" + this.condition +", Price=" + this.price +", Notes=" + this.notes +" ]";
}

}