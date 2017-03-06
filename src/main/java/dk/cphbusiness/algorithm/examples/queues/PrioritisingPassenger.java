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
            pp.enqueue(new Passenger(i, new Time(new Date().getTime()),pp.randomCat(), new Plane(new Time(new Date().getTime()))));
        }
    }
    private Category randomCat(){
        int rand = (int)(Math.random()*3);
        if (rand==0) return Category.Monkey;
        if (rand==1)return Category.Disabled;
        if (rand==2)return Category.Family;
        return Category.LateToFlight;
    }
    
    public PrioritisingPassenger(int capacity){
        items = new Passenger[capacity];
    }
            
    @Override
    public void enqueue(Passenger item) {
        Passenger temp = null;
        items[size] = item;
        
        int position = size;
        while (items[position].compareTo(items[position/2])<0){
            temp = items[position/2];
            items[position/2] = items[position];
            items[position] = temp;
        }
        size++;
    
    }

    @Override
    public Passenger dequeue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Passenger peek() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
