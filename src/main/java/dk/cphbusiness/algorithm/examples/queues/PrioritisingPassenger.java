/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.algorithm.examples.queues;

import dk.cphbusiness.airport.template.Category;
import dk.cphbusiness.airport.template.Passenger;
import dk.cphbusiness.airport.template.Plane;
import dk.cphbusiness.airport.template.Time;
import java.util.Date;

/**
 *
 * @author Daniel
 */
public class PrioritisingPassenger implements PriorityQueue<Passenger> {

    private final Passenger[] items;
    private int size = 0;

    public static void main(String[] args) {
        PrioritisingPassenger pp = new PrioritisingPassenger(100);
        for (int i = 0; i < 100; i++) {
            pp.enqueue(new Passenger(i, new Time(new Date().getTime()), pp.randomCat(), new Plane(new Time(new Date().getTime()))));
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(pp.dequeue().getCategory());
        }
    }

    private Category randomCat() {
        int rand = (int) (Math.random() * 3);
        if (rand == 0) {
            return Category.Monkey;
        }
        if (rand == 1) {
            return Category.Disabled;
        }
        if (rand == 2) {
            return Category.Family;
        }
        return Category.LateToFlight;
    }

    public PrioritisingPassenger(int capacity) {
        items = new Passenger[capacity];
    }

    @Override
    public void enqueue(Passenger item) {
        Passenger temp = null;
        items[size] = item;

        int position = size;
        while (items[position].compareTo(items[(position - 1) / 2]) < 0) {

            temp = items[(position - 1) / 2];
            items[(position - 1) / 2] = items[position];
            items[position] = temp;
            position = (position - 1) / 2;
        }
        size++;

    }

    @Override
    public Passenger dequeue() {
        Passenger first = items[0];
        Passenger temp;

        items[0] = items[size - 1];
        items[size - 1] = null;
        int pos = 0;
        while (pos * 2 + 1< size && items[pos * 2 + 1] != null && pos * 2 + 2< size && items[pos * 2 + 2] != null) {
            if (items[pos * 2 + 1].compareTo(items[pos * 2 + 2]) >= 0){
                System.out.println("go west!");
                temp = items[pos * 2 +1];
                items[pos * 2 +1] = items[pos];
                items[pos] = temp;
                pos = pos * 2 + 1;
            } 
            else if (items[pos * 2 + 1].compareTo(items[pos * 2 + 2]) < 0){
                System.out.println("go right!");
                temp = items[pos * 2 +2];
                items[pos * 2 +2] = items[pos];
                items[pos] = temp;
                pos = pos * 2 + 2;
            }
            else break;
            
        }
        if (pos * 2 + 1< size && items[pos * 2 + 1] != null || pos * 2 + 2< size && items[pos * 2 + 2] != null) {

        }

        return first;
    }

    @Override
    public Passenger peek() {
        return items[0];
    }

    @Override
    public int size() {
        return size;
    }

    private void swap() {

    }

}
