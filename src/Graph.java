import java.util.*;

public class Graph {
    /*
       In the process of creating a Graph through this Graph blueprint note that in this graph all the
        edges are bi-directional which by default it is assumed to be an unordered graph or undirected graph
     */

    //Fields Declaration
    private String name;
    private int id_increment; // creating an id increment variable to provide IDs to the nodes
    private Vertex[] nodeCollection; // this field will hold a collection graph nodes
    private int[][] adjacent_matrix; // this field will maintain the integrity of the vertices and edges of the graph.

    private int initialSateId;
    private int goalStateId;


    //default constructor
    public Graph() {

    }

    //Constructor overloading
    public Graph(String name) {
        this.name = name;
    }

    public void create() {
        nodeInit();
    }

    public void display() {
        displayNodeCollection();
    }

    //nodeInit() method will create nodes for the current graph
    private void nodeInit() {

        Scanner scan = new Scanner(System.in); //instantiate a new Scanner object

        System.out.println("This program will help you to create a graph and run the solving agents" +
                " in order to find a solution for the graph given by the user by " +
                " setting the initial state and goal state for the agents to in in the graph");
        System.out.println(" Problem Solving Agents");

        System.out.println("How many nodes do you want for your graph ?");
        System.out.print("\tUser feedback :=> ");

        //need to create a error a miss data handling
        setId_increment(scan.nextByte()); //Prompting the user to provide a integer value
        nodeCollection = new Vertex[getId_increment()]; // Creating and initializing the array size for the nodeCollection
        adjacent_matrix = new int[getId_increment()][getId_increment()]; //Creating and initializing the 2D array with a fixed size

        setAdjacent_matrix(); //Assigning a default value = -1 for the whole matrix


        //Loop to prompt the number of nodes wanted by the user
        for (int i = 0; i < nodeCollection.length; i++) {

            System.out.print("Enter a name for node " + (i + 1) + " :=> ");
            String nodeName = scan.next(); //prompting the user to provide a name

            System.out.print("Enter its heuristic value :=> ");
            int heuristic = scan.nextByte();//prompting the user to provide the heuristic Value

            nodeCollection[i] = new Vertex(nodeName, i, heuristic); //creating and initializing a new object of type vertex

        }

        for (int i = 0; i < nodeCollection.length; i++) //Display the created node in the nodeCollection
        {
            //Printing the successfully created nodes
            System.out.println("Node Details-> ( Name = " + nodeCollection[i].getName() + "; ID = " + nodeCollection[i].getId() +
                    "; heuristic = " + nodeCollection[i].getHeuristicValue() + " ) was created successfully");
        }

        edgeInit(); //Calling the method to establish the connection between the nodes
    }

    //This edgeInit() will created connections between the created nodes
    // Edge is a line established from one node(vertex) to another
    private void edgeInit() { //edgeInit() must be private to avoid error handling

        Scanner scan = new Scanner(System.in);  //instantiate a new Scanner object
        String getNodeNameFromUser;
        int getNodeCount;

        System.out.println("\n\n---------------------------------------------------------------------------");
        System.out.println(" The nodes need to be interconnected to each other to finalize the Graph.");

        System.out.println("\tImportant note:");
        System.out.println("\t\t#> If you have connected node A to B, you do not need to connect B to A again!");

        //prompting the user the number pf nodes to interconnect it with the current node
        for (int i = 0; i < nodeCollection.length; i++) {
            System.out.print("\nHow many nodes to you want to connect with node " +
                    "(" + nodeCollection[i].getName() + ") :=> ");
            getNodeCount = scan.nextInt();


            //This loop will extract the node names and check if they exist in the node collection
            for (int j = 0; j < getNodeCount; j++) {
                System.out.print("Enter the name of the neighbor node number " + (j + 1) + " for node (" + nodeCollection[i].getName() + ") :=> ");
                //System.out.print("\tEnter the "+(j+1) +" neighbor node for node (" + nodeCollection[i].getName()+") :=> ");
                getNodeNameFromUser = scan.next();


                //check name in the node collection
                int counter = 0;
                while (counter < nodeCollection.length) {
                    //checks if node name exist in the node collection
                    if (nodeCollection[counter].getName().toLowerCase().trim().equals(getNodeNameFromUser.toLowerCase().trim())) {
                        //checks if the node has established a connection already
                        if (adjacent_matrix[i][counter] > -1 && (adjacent_matrix[counter][i] > -1)) {
                            System.out.println("\t\t" + nodeCollection[i].getName() + " is already connected to " +
                                    nodeCollection[counter].getName());
                            break;
                        }
                        // Create a new connection for a given node
                        else {
                            System.out.print("\nWhat is the path cost for this connection :=> ");
                            adjacent_matrix[i][counter] = scan.nextShort(); //assigning the path cost to the 2D Matrix
                            adjacent_matrix[counter][i] = adjacent_matrix[i][counter]; //assign the path cost, creating an undirected graph
                            System.out.println(nodeCollection[i].getName() + " connected to " +
                                    nodeCollection[counter].getName() + " successfully!");

                            //Printing the adjacent matrix for input visualization
                            for (int[] element : adjacent_matrix) {
                                System.out.println(Arrays.toString(element));
                            }
                            break;
                        }
                    } else if (counter == (nodeCollection.length - 1))//if node is not found and we exhaust our Array
                    {
                        System.out.println("The name was not found in the node collection");
                    }
                    counter++; //Incrementing count
                }
            }
        }

        System.out.println("The graph have been created successfully!");

        //updating the path from one node to another to the Node Collection
        updatePathCostList(adjacent_matrix);

        displayNodeCollection();

    }

    //updating the path from one node to another to the Node Collection
    // Adding the names to the neighboring list variable
    private void updatePathCostList(int[][] matrix) {

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                if (matrix[row][column] > -1) {
                    nodeCollection[row].setNeighborNameList(nodeCollection[column]);
                    nodeCollection[row].setPathCostConnectionList(matrix[row][column]);
                }
            }
        }
    }


    private void displayNodeCollection() {
        for (int i = 0; i < nodeCollection.length; i++) {
            System.out.println("Node name: " + nodeCollection[i].getName() +
                    "\t Id: " + nodeCollection[i].getId() +
                    "\t h(n) = " + nodeCollection[i].getHeuristicValue() +
                    "\t PathCost List: " + nodeCollection[i].getPathCostConnectionList() +
                    "\t Neighbor List: " + nodeCollection[i].getNeighborNameList());
        }
    }


    //Getters
    private int getId_increment() {
        return id_increment;
    }


    //getting the name of the created graph which is optional
    public String getName() {
        return name;
    }


    //Setters
    private void setId_increment(int id_increment) {
        this.id_increment = id_increment;
    }

    // Setting a default value -1 for the whole matrix
    private void setAdjacent_matrix() {
        //If value is greater than -1 it means that there is a connection established
        //      between two nodes
        for (int[] matrix : this.adjacent_matrix) {
            Arrays.fill(matrix, -1);
        }
    }


    //----------------------------------------------Search Algorithm Functions--------------------------------------------------------------------



}





