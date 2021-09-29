package com.jetbrains.zCompanyInterviewPrep.amazon.ood;

import java.util.*;

// refer to: https://leetcode.com/discuss/interview-question/260467/amazon-interview-question-design-a-locker


enum Size {
    SMALL, MEDIUM, LARGE
}

enum LockerStatus {
    UNOCCUPIED, OCCUPIED
}

//Could not use Package as a class name, so next best thing is box?
class Box {
    Size size;
    int lockerLocation;

    public Box() {}
    public Box(Size s) {
        this.size = s;
    }
}

class LockerSpot {
    Size size;
    LockerStatus status;
    Box box;

    public LockerSpot(Size size) {
        this.size = size;
        status = LockerStatus.UNOCCUPIED;
    }
}

//I used your naming convention and a locker is a cllection of lockerspots.
class Locker {
    Queue<LockerSpot> lockerQueue;
    List<LockerSpot> lockerSpots;

    public Locker(int numOfLockers, Size size) {
        lockerSpots = new ArrayList<>();
        for(int i = 0; i < numOfLockers; i++) {
            lockerSpots.add(new LockerSpot(size));
        }
        //possible use a priortyqueue and sort on something interseting   => lockspot with less height will be more likely to be choosen
        lockerQueue = new LinkedList<>(lockerSpots);
    }

    public boolean isSpotAvailable() {
        return lockerQueue.size() > 0;
    }

    public LockerSpot getSpot() {
        return lockerQueue.poll();
    }

    public void addSpotToQueue(LockerSpot spot) {
        lockerQueue.add(spot);
    }
}

class LockerManager {
    static final int MAX_SMALL = 3;
    static final int MAX_MED = 3;
    static final int MAX_LARGE = 3;
    Locker[] lockers;
    HashMap<Box, LockerSpot> boxLocationMap;  // need to retrieve the lockerspot immediately based on the box info

    public LockerManager() {
        lockers = new Locker[3];
        lockers[0] = new Locker(MAX_SMALL, Size.SMALL);
        lockers[1] = new Locker(MAX_MED, Size.MEDIUM);
        lockers[2] = new Locker(MAX_LARGE, Size.LARGE);

        boxLocationMap = new HashMap<>();
    }

    /**
     * Find the next available lockerspot. Only lockerspots greater or equal to box
     * can fit. Ex small box can fit in all three lockerspot sizes.
     *
     * @param box
     */
    public void addBox(Box box) {
        boolean isSpotFound = false;
        //this can be improved, im using small = 0, med = 1, large = 2 but with enumss lol
        for (int i = box.size.ordinal(); i <= Size.LARGE.ordinal(); i++) {
            if (lockers[i].isSpotAvailable()) {
                LockerSpot newSpot = lockers[i].getSpot();
                newSpot.box = box;
                newSpot.status = LockerStatus.OCCUPIED;
                box.lockerLocation = i;
                boxLocationMap.put(box, newSpot);
                isSpotFound = true;

                System.out.println("add the box to teh locker: " + box.size);
                break;
            }
        }
        if (!isSpotFound) {
            //add to some queue?
        }
    }

    public void removeBox(Box box) {
        if (boxLocationMap.containsKey(box)) {
            LockerSpot lockerSpot = boxLocationMap.get(box);
            lockerSpot.status = LockerStatus.UNOCCUPIED;
            boxLocationMap.remove(box);
            lockers[box.lockerLocation].addSpotToQueue(lockerSpot);

            System.out.println("remove the box from the locker " + box.size);
        }
    }
}

// locker controller
public class LockerController {
    public static void main(String args[]) {
        LockerManager manager = new LockerManager();
        Box sItem = new Box(Size.SMALL);
        Box mItem = new Box(Size.MEDIUM);
        manager.addBox(sItem);
        manager.addBox(mItem);

        manager.removeBox(sItem);
    }
}

