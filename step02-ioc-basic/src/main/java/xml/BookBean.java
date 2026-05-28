package xml;

// BookBean은 Spring 컨테이너가 관리할 "도서 한 권" 객체입니다.
// XML 설정 파일에서 <bean>으로 등록하면, Spring이 이 클래스로 객체를 만들어 줍니다.
public class BookBean {

    // XML의 <property> 또는 <constructor-arg> 값이 아래 필드에 주입됩니다.
    private String title;
    private String author;
    private int price;

    // 기본 생성자: <property>를 사용하는 setter 주입 방식에서 먼저 호출됩니다.
    // Spring은 객체를 만든 뒤 setTitle(), setAuthor(), setPrice()를 차례로 호출합니다.
    public BookBean() {}

    // 전체 생성자: <constructor-arg>를 사용하는 생성자 주입 방식에서 호출됩니다.
    // 객체가 생성되는 순간 title, author, price 값이 한 번에 들어옵니다.
    public BookBean(String title, String author, int price) {
        this.title  = title;
        this.author = author;
        this.price  = price;
    }

    // getter/setter는 Spring이 값을 주입하거나, 다른 코드가 값을 읽을 때 사용합니다.
    public String getTitle()        { return title; }
    public void   setTitle(String title)   { this.title = title; }

    public String getAuthor()       { return author; }
    public void   setAuthor(String author) { this.author = author; }

    public int    getPrice()        { return price; }
    public void   setPrice(int price)      { this.price = price; }

    @Override
    public String toString() {
        // 객체를 출력할 때 주소값 대신 사람이 읽기 쉬운 도서 정보가 나오게 합니다.
        return String.format("제목: %s | 저자: %s | 가격: %,d원", title, author, price);
    }
}
