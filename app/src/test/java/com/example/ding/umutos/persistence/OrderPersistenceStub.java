package com.example.ding.umutos.persistence;

import com.example.ding.umutos.objects.Order;

import java.util.List;

public class OrderPersistenceStub implements OrderPersistence {

    private List<Order> orders;

    public OrderPersistenceStub()
    {
        String[] orderInfo1 = {"firstName1", "lastName1", "r3y0b6", "2046666666", "Mars"};
        String[] orderInfo2 = {"firstName2", "lastName2", "r3y0b7", "2046666667", "Heaven"};
        orders.add(new Order("book1", "user1", "seller1", 1, orderInfo1));
        orders.add(new Order("book2", "user2", "seller2", 3, orderInfo2));
    }

    @Override
    public Order insertOrder(Order currentOrder) {
        orders.add(currentOrder);
        return currentOrder;
    }

    @Override
    public List<Order> getBuyerOrders(int userID) {
        return null;
    }

    @Override
    public List<Order> getSellerOrders(int userID) {
        return null;
    }
}
