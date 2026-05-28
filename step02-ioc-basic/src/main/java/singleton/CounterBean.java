package singleton;

// CounterBean은 singleton 스코프를 확인하기 위한 클래스입니다.
// singleton Bean은 Spring 컨테이너 안에서 기본적으로 한 번만 생성되고 계속 재사용됩니다.
public class CounterBean {

    // count 값이 여러 getBean() 호출 사이에서 공유되는지 확인합니다.
    private int count = 0;

    public void increment() {
        // 같은 객체라면 c1에서 증가시킨 count를 c2에서도 볼 수 있습니다.
        count++;
    }

    public int getCount() {
        return count;
    }
    
    public void onDestroy() {
    	System.out.println("CounterBean 소멸");
    }
}
