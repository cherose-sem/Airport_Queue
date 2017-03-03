package dk.cphbusiness.airport.template;

import dk.cphbusiness.algorithm.examples.queues.PriorityQueue;
import java.util.List;

public class PassengerConsumer {
  private final List<Plane> planes;
  private final PriorityQueue<Passenger> queue;
  private int processingTicksLeft = 0;
  // Passenger being processed
  private Passenger passenger;

  public PassengerConsumer(List<Plane> planes, PriorityQueue<Passenger> queue) {
    this.planes = planes;
    this.queue = queue;
    }
  
  public void tick(Clock clock) {
    if (processingTicksLeft > 0) {
      processingTicksLeft--;
      return;
      }
    else {
      Time now = clock.getTime();
      if (passenger.getPlane().getDepartureTime().compareTo(now) < 0) {
        passenger.setStatus(Status.MissedPlane);
        System.out.println("Passenger "+passenger.getId()+" missed the plane");
        }
      else {
        passenger.setStatus(Status.Boarded);
        System.out.println("Passenger "+passenger.getId()+" has boarded");
        }
      }
    if (queue.isEmpty()) return;
    else {
      passenger = queue.dequeue();
      switch (passenger.getCategory()) {
        case LateToFlight:
          processingTicksLeft = 60;
          break;
        case BusinessClass:
          processingTicksLeft = 60;
          break;
        case Disabled:
          processingTicksLeft = 180;
          break;
        case Family:
          processingTicksLeft = 180;
          break;
        case Monkey:
          processingTicksLeft = 60;
          break;
        }
      }
    
    }

  }