package org.scalalabs.intermediate.lab04;


import scala.Function2;
import scala.Predef$;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.runtime.AbstractFunction2;
import scala.collection.immutable.$colon$colon;

import java.util.Date;

public class PaymentServiceClient {

    public void cachePayment(String userId, int value) {
        Order order = new Order(userId, new Cache(), value);
        List<Order> orders = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray(new Order[]{
                order
        }));
        PaymentService.pay(orders);

    }

    public void cardPayment(String userId, int value, Date date) {
        // you can use new $colon$colon() for adding to a list
    }

    public void resetState(){
        PaymentService.reset();
    }

    public void setVerboseLogMode(boolean mode){
       //TODO insert code here
    }

    public boolean isVerboseLogMode(){
        return PaymentService.verboseLogMode();
    }

    public List<Order> findAllOrders() {
        //use  AbstractFunction2
        //TODO insert code here
        return null;

    }

    public void voucherPayment(String userId, int value) {
       //TODO uncommend and add implementation
       //Order order = new Order(userId, new GiftVoucher(userId), value);
    }

}

/* Uncomment and implement
class GiftVoucher implements Belongs, PaymentMethod {

    String holderName;

}*/
