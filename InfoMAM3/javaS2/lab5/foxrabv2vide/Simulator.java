package lab5.foxrabv2vide;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and foxes.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 *
 * @author Hélène Collavizza
 */
 class Simulator
{
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 50;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 50;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.02;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.08;
    // The probability that a rabbit will be created in any given grid position.
    private static final double WOLF_CREATION_PROBABILITY = 0.005;

    // List of animals in the field.
    private List<Animal> animals;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;

    // true to start with an empty list of foxes an rabbits
    private boolean empty;
    // true to only show a textual view
    private boolean textual;

    /**
     * Construct a simulation field with default size
     * @param empty : if true, the field is empty and one can
     *              add few animals using addFox or addRabbit
     * @param textual if true, only textual information is displayed
     */
     Simulator( boolean empty,boolean textual) {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH,empty,textual);
    }

    /**
     * Construct a simulation field with default size.
     */
     Simulator()    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH,false,false);
    }


    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     * @param empty to start with empty list of fox and rabbit
     * @param textual to don't display the graphical view
     */
     Simulator(int depth, int width,boolean empty, boolean textual)  {
        this.empty=empty;
        this.textual = textual;
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }

        animals = new ArrayList<>();
        field = new Field(depth, width);

        // Create a view of the state of each location in the field.
        if (!textual) {
            view = new SimulatorView(depth, width);
            view.setColor(Rabbit.class, Color.orange);
            view.setColor(Fox.class, Color.blue);
        }

        // create a random number of foxes and rabbits
        if (!empty)
            populate();
    }

    /**
     * to add an animal at location (r,c)
     * @param r
     * @param c
     */
    protected void addFox(int r, int c) {
        Location location = new Location(r, c);
        Fox fox = new Fox(false, field, location);
        animals.add(fox);
    }

    /**
     * to add a rabbit at location (r,c)
     * @param r
     * @param c
     */
    protected void addRabbit(int r,int c) {
        Location location = new Location(r, c);
        Rabbit rabbit = new Rabbit(false, field, location);
        animals.add(rabbit);
    }


    /**
     * Run the simulation from its current state for a reasonably long period,
     * e.g. 500 steps.
     */
     void runLongSimulation()    {
        simulate(500);
    }

    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
     void simulate(int numSteps)    {
        // to slowdown the simulation
        // if there are less than 50 step, the simulation is very slow
      	int tempo = (numSteps < 80) ? 1000:100;
        for(int step = 1; step <= numSteps && view.isViable(field); step++) {
           	try {
				Thread.sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
           simulateOneStep();
        }
    }

    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
     void simulateOneStep()    {
        step++;

        // Provide space for newborn animals.
        List<Animal> newAnimals = new ArrayList<Animal>();
        // Let all rabbits act.
        for(Iterator<Animal> it = animals.iterator(); it.hasNext(); ) {
            Animal animal = it.next();
            animal.act(newAnimals);
            if(! animal.isAlive()) {
                it.remove();
            }
        }

        // Add the newly born foxes and rabbits to the main lists.
        animals.addAll(newAnimals);

        if (!textual)
            view.showStatus(step, field);
    }

    /**
     * Reset the simulation to a starting position.
     */
     void reset()  {
        step = 0;
        animals.clear();
    }

    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate()
    {
        reset();
        Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    addFox(row,col);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    addRabbit(row,col);
                }

                // else leave the location empty.
            }
        }
        // Show the starting state in the view.
        if (!textual)
            view.showStatus(step, field);
    }

    /**
     * affiche une vision textuelle du field
     */
     void textualView() {
        System.out.println("Simulation step :" + step);
        if (animals.isEmpty())
            System.out.println("No animal");
        else {
            for (Animal a : animals) {
                System.out.println(a);
            }
        }
    }
}
