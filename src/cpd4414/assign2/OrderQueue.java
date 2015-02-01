/*
 * Copyright 2015 Sidhartha Gopinath <sidharthagopinath@outlook.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cpd4414.assign2;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;

/**
 *
 * @author Sidhartha Gopinath <sidharthagopinath@outlook.com>
 */
public class OrderQueue {

    Queue<Order> orderQueue = new ArrayDeque<>();
    Queue<Order> processing = new ArrayDeque<>();

    public void add(Order order) {
        if (order.getCustomerId().isEmpty() && order.getCustomerName().isEmpty()) {
            throw new NoCustomerException();
        }
        if (order.getListOfPurchases().isEmpty()) {
            throw new NoPurchasesException();
        }
        orderQueue.add(order);
        order.setTimeReceived(new Date());
    }

    public Order next() {
        return orderQueue.peek();
    }

    public void process(Order order) {

        if (order.getTimeReceived() != null) {
            
                order.setTimeProcessed(new Date());
            }
         else {
            throw new RuntimeException();
        }

    }

    public void fulfill(Order order) {
        if (order.getTimeProcessed() == null) {
            throw new RuntimeException();
        }if (order.getTimeReceived() == null) {
            throw new RuntimeException();
        } 
    }

    public String report() {

        if (orderQueue.isEmpty()) {
            return "";
        } 
            return "";
    }

    private class NoCustomerException extends RuntimeException {
    }

    private class NoPurchasesException extends RuntimeException {
    }

}
