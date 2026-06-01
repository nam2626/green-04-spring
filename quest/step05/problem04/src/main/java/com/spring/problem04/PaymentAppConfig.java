package com.spring.problem04;

// TODO 1: @Configuration 어노테이션을 추가하라.
// TODO 2: @ComponentScan("com.spring.problem04") 어노테이션을 추가하라.
//         (CashPayment, CardPayment의 @Component를 스캔하기 위함)
public class PaymentAppConfig {

    // TODO 3: @Bean 어노테이션을 추가하고 ReceiptPrinter 객체를 반환하는 메서드를 작성하라.
    //   메서드 이름: receiptPrinter
    //   반환 타입: ReceiptPrinter

    // TODO 4: @Bean 어노테이션을 추가하고 CardPayment를 사용하는 CheckoutService Bean을 작성하라.
    //   메서드 이름: checkoutServiceWithCard
    //   파라미터: CardPayment cardPayment, ReceiptPrinter receiptPrinter
    //   반환 타입: CheckoutService

    // TODO 5: @Bean 어노테이션을 추가하고 CashPayment를 사용하는 CheckoutService Bean을 작성하라.
    //   메서드 이름: checkoutServiceWithCash
    //   파라미터: CashPayment cashPayment, ReceiptPrinter receiptPrinter
    //   반환 타입: CheckoutService
}
