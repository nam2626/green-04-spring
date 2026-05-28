package singleton;

// TemporaryBean은 prototype 스코프를 확인하기 위한 클래스입니다.
// prototype Bean은 getBean()을 호출할 때마다 새 객체가 만들어집니다.
public class TemporaryBean {

    // 새 객체인지 쉽게 구분하기 위해 생성 시점에 임의의 id를 만듭니다.
    private final String id;

    public TemporaryBean() {
        this.id = "T-" + (int) (Math.random() * 10000);
    }

    public String getId() {
        return id;
    }
}
