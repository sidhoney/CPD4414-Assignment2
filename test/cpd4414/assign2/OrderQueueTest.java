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

import cpd4414.assign2.OrderQueue;
import cpd4414.assign2.Purchase;
import cpd4414.assign2.Order;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Sidhartha Gopinath <sidharthagopinath@outlook.com>
 */
public class OrderQueueTest {

    public OrderQueueTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testWhenCustomerExistsAndPurchasesExistThenTimeReceivedIsNow() {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        orderQueue.add(order);
        long expResult = new Date().getTime();
        long result = order.getTimeReceived().getTime();
        assertTrue(Math.abs(result - expResult) < 1000);
    }

    @Test
    public void testWhenCustomerNameDoesNotExistAndCustomerIDDoesNotExistThenThrowException() {
        OrderQueue orderQueue = new OrderQueue();
        boolean didthrow = false;
        Order order = new Order("", "");
        try {
            orderQueue.add(order);
        } catch (Exception e) {

            didthrow = true;
        }
        assertTrue(didthrow);

    }

    @Test
    public void testWhenNoListOfPurchasesExistsThenThrowException() throws Exception {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        boolean didthrow = false;

        try {
            orderQueue.add(order);
        } catch (Exception e) {
            didthrow = true;
        }
        assertTrue(didthrow);
    }

    @Test
    public void testWhenOrdersInTheSystemThenReturnOrderWithEarliestTimeRecieved() throws Exception {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        orderQueue.add(order);
        Order orderNew = new Order("CUST00002", "ABCDE Construction");
        orderNew.addPurchase(new Purchase("PROD0005", 350));
        orderNew.addPurchase(new Purchase("PROD0006", 450));
        orderQueue.add(orderNew);
        Order expResult = order;
        Order result = orderQueue.next();
        assertEquals(expResult, result);
    }

    @Test
    public void testWhenNoOrdersInTheSystemThenReturnNull() {
        OrderQueue orderQueue = new OrderQueue();
        Order expResult = null;
        String result = null;
        assertEquals(expResult, result);
    }



    @Test
    public void testWhenOrderDoesNotHaveATimeRecievedThrowException() {
        boolean didThrow = false;
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        try {
            orderQueue.process(order);
        } catch (RuntimeException ex) {
            didThrow = true;
        }
        assertTrue(didThrow);
    }



    @Test
    public void testWhenTheOrderDoesNotHaveATimeRecievedThrowException() {
        boolean didThrow = false;
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        order.setTimeReceived(new Date(new Date().getTime() - 1422722222));
        try {
            orderQueue.fulfill(order);
        } catch (RuntimeException e) {
            didThrow = true;
        }
        assertTrue(didThrow);
    }

    @Test
    public void testOrderDoesNotHaveATimeProcessedThrowException() {
        boolean didThrow = false;
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        order.setTimeProcessed(new Date(new Date().getTime() - 1422724484));
        try {
            orderQueue.fulfill(order);
        } catch (RuntimeException e) {
            didThrow = true;
        }
        assertTrue(didThrow);
    }

    @Test
    public void testOrderDoesNotHaveATimeRecievedThrowException() {
        OrderQueue orderQueue = new OrderQueue();
        String result = orderQueue.report();
        assertEquals("", result);
    }
}
