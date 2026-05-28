package xml;

import java.util.List;
import java.util.Map;

// LibraryBean은 여러 종류의 의존성 주입을 한 번에 보여주는 예제 클래스입니다.
// 문자열 값, 다른 Bean 참조(ref), List, Map을 모두 XML에서 주입받습니다.
public class LibraryBean {

    // 단순 문자열 값 주입 예제입니다.
    private String              libraryName;
    // BookBean 객체 자체를 주입받는 ref 주입 예제입니다.
    private BookBean            featuredBook;   // ref 주입
    // 여러 값을 순서대로 담는 list 주입 예제입니다.
    private List<String>        genres;         // list 주입
    // key-value 형태의 값을 담는 map 주입 예제입니다.
    private Map<String, String> bookPrices;     // map 주입

    public void printInfo() {
        // XML에서 주입된 값들이 실제 객체 안에 잘 들어왔는지 확인합니다.
        System.out.println("[Library - ref + 컬렉션 주입]");
        System.out.println("  도서관명: " + libraryName);
        System.out.println("  추천 도서: " + featuredBook.getTitle());
        System.out.println("  보유 장르: " + genres);
        System.out.println("  도서 가격표: " + bookPrices);
    }

    // 아래 setter들은 Spring이 <property> 값을 넣을 때 호출하는 통로입니다.
    public String getLibraryName()               { return libraryName; }
    public void   setLibraryName(String n)       { this.libraryName = n; }

    public BookBean getFeaturedBook()            { return featuredBook; }
    public void     setFeaturedBook(BookBean b)  { this.featuredBook = b; }

    public List<String> getGenres()              { return genres; }
    public void         setGenres(List<String> g){ this.genres = g; }

    public Map<String, String> getBookPrices()              { return bookPrices; }
    public void                setBookPrices(Map<String, String> m) { this.bookPrices = m; }
}
