package dk.cphbusiness.airport.template;

import dk.cphbusiness.algorithm.examples.queues.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

public class PassengerProducer {
  private final List<Plane> planes;
  private final PriorityQueue<Passenger> queue;
  
  public PassengerProducer(List<Plane> planes, PriorityQueue<Passenger> queue) {
    this.planes = planes;
    this.queue = queue;
    }
  
  public void tick(Clock clock) {
      for (Plane plane : planes) {
          if (plane.getDepartureTime().compareTo(clock.getTime())>1){
              List<Passenger> passengers = plane.getPassengers();
              for (Passenger passenger : passengers) {
                  queue.enqueue(passenger);
              }
          }
      }
      
    }
  
  
  }
