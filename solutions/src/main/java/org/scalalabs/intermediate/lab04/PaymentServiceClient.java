package org.scalalabs.intermediate.lab04;


import scala.Function2;
import scala.Predef$;
import scala.collection.immutable.*;
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
        Order order = new Order(userId, new PaymentCard(date, userId), value);
        List<Order> orders = List$.MODULE$.empty();
        orders = new $colon$colon(order, orders);
        PaymentService.pay(orders);
    }

    public void resetState(){
        PaymentService.reset();
    }

    public void setVerboseLogMode(boolean mode){
        PaymentService.verboseLogMode_$eq(mode);
    }

    public boolean isVerboseLogMode(){
        return PaymentService.verboseLogMode();
    }

    public List<Order> findAllOrders() {
        Function2<Order, Order, Object> sortRule = new AbstractFunction2<Order, Order, Object>() {
            public Boolean apply(Order o1, Order o2) {
                return o1.amount() > o2.amount();
            }
        };

        return PaymentService.getSortedHistory(sortRule);

    }

    public void voucherPayment(String userId, int value) {
        Order order = new Order(userId, new GiftVoucher(userId), value);
        List<Order> orders = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray(new Order[]{
                order
        }));
        PaymentService.pay(orders);
    }

}

class GiftVoucher implements Belongs,PaymentMethod {
    String holderName;

    public GiftVoucher(String holderName) {
        this.holderName = holderName;
    }

    public String holderName() {
        return holderName;
    }

    public String firstName() {
         return Belongs$class.firstName(this);
    }
}
