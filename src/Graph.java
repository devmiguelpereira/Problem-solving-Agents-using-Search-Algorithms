import java.util.*;

class BFS {

    // fields declaration
    private Queue<Vertex> frontier;
    private boolean reached = false;

    //Methods

    //default constructor
    public BFS() {
        this.frontier = new LinkedList<>();// instantiating the Queue(frontier)
    }

    public void bfs(Graph g, Vertex initial, Vertex goal) {
        List<Vertex> nodeList = g.getNodeCollection();//get all the nodes from the graph


        for (int i = initial.getId(); i < nodeList.size(); i++) {
            if (nodeList.get(i).isVisited() == false) {
                nodeList.get(i).setVisited(true);
                bfsInQueue(nodeList.get(i), goal);// adding the nodes to the queue
            }
        }

//        for (Vertex n : nodeList) {
//            if (n.isVisited() == false) {
//                n.setVisited(true);
//                bfsInQueue(n);// adding the nodes to the queue
//            }
//        }
    }

    private void bfsInQueue(Vertex root, Vertex goal) {
        this.frontier.add(root);
        root.setVisited(true);


        while (this.frontier.isEmpty() == false) {
            Vertex currentNode = this.frontier.remove();
            if (reached == false)
                System.out.println("Current Vertex: " + currentNode.getElement().toString());
            if (currentNode == goal) {
                System.out.println("Goal state found -> " + currentNode.getElement());
                reached = true;

            }

            for (Vertex n : currentNode.getNeighbours()) {
                if (n.isVisited() == false) {
                    n.setVisited(true);
                    this.frontier.add(n);
                }
            }

        }
    }
}

class DFS {

    // fields declaration
    private Stack<Vertex> frontier;
    private boolean reached = false;


    //default constructor
    public DFS() {
        this.frontier = new Stack<>(); //instantiating the Stack(frontier)
    }

    public void dfs(Graph g, Vertex initial, Vertex goal) {
        List<Vertex> nodeCollection = g.getNodeCollection(); // get all nodes from the graph
//
        for (int i = initial.getId(); i < nodeCollection.size(); i++) {
            if (nodeCollection.get(i).isVisited() == false) {
                nodeCollection.get(i).setVisited(true);
                dfsInStack(nodeCollection.get(i), goal);// adding the nodes to the queue
            }
        }
//        for (Vertex n : nodeCollection) {
//            if (n.isVisited() == false) {
//                n.setVisited(true);
//                dfsInStack(n);
//            }
//        }

    }

    private void dfsInStack(Vertex root, Vertex goal) {
        this.frontier.push(root);
        root.setVisited(true);

        while (this.frontier.isEmpty() == false) {
            Vertex currentNode = this.frontier.pop();
            if (reached == false)
                System.out.println("Current Vertex: " + currentNode.getElement().toString());
            if (currentNode == goal) {
                System.out.println("Goal state found -> " + currentNode.getElement());
                reached = true;

            }

            for (int i = currentNode.getNeighbours().size() - 1; i >= 0; i--) {
                Vertex n = currentNode.getNeighbours().get(i);
                if (n.isVisited() == false) {
                    n.setVisited(true);
                    this.frontier.push(n);
                }
            }
        }
    }
}

class A_star {

        private int[][] adjacent_matrix; // this field will maintain the integrity of the vertices and edges of the graph
        private int startNodeIndex;
        private int targetNodeIndex;


        private PriorityQueue<Vertex> openSet;
        private boolean[] closedSet;



        public A_star(Graph graph){
            openSet = new PriorityQueue<Vertex>( (Vertex n1, Vertex n2)    -> {
                if (  n1.gethValue() < n2.gethValue()) {
                    return -1;
                }

                else if(n1.gethValue() > n2.gethValue()) {
                    return 1;
                }
                else {
                    return 0;
                }
            });

        }

        public List<Vertex> search(Graph graph,
                                 int startNodeIndex,
                                 int targetNodeIndex)
        {

            List<Vertex> nodeList = graph.getNodeCollection();

            closedSet = new boolean[nodeList.size()];

            Vertex root = nodeList.get(this.startNodeIndex);


            List<Vertex> neighbours = null;
            root.setVisited(true);
            openSet.add(root);
            Vertex currentNode;

            List<Vertex> nodeVisited = new ArrayList<>();

            while(true) {
                // remove from the openSet and add it to the closeSet
                currentNode = openSet.poll();
                nodeVisited.add(currentNode);

                // only if current node is empty
                if (currentNode == null) {
                    break;
                }

                // adding to the list
                this.closedSet[(int) currentNode.getId()-1] = true;

                // found goal state
                if((int) currentNode.getId() == this.targetNodeIndex ) {
                    System.out.println("Goal found "+currentNode.getElement()+" = "+this.targetNodeIndex);
                    break;
                }

                // if none is true we get the neighbors
                neighbours = currentNode.getNeighbours();

                for (Vertex n : neighbours) {
                    updateOpenSetIfNeeded(currentNode, n);
                }
            }

            return nodeVisited;
        }


        public void updateOpenSetIfNeeded(Vertex current, Vertex n) {

            //if node is null or is inside the closedSet or
            // it was visited already so not need to add to openSet

            if (n == null || closedSet[n.getId()]
                    || n.isVisited())
                return;

            boolean isOpen = openSet.contains(n);

            if(isOpen == false ) {
                openSet.add(n);
            }


        }






}


public class Graph {

    // fields declaration
    private int id_increment; // creating an id increment
    private List<Vertex> nodeCollection;
    private int[][] adjacent_matrix; // this field will maintain the integrity of the vertices and edges of the graph.
    private Vertex initial;
    private Vertex goal;

    //Methods

    //default constructor
    public Graph() {
        this.nodeCollection = new ArrayList<>();
    }

    // Creating getters and setters
    public void setNodeCollection(List<Vertex> nodeCollection) {
        this.nodeCollection = nodeCollection;
    }

    public List<Vertex> getNodeCollection() {
        return this.nodeCollection;
    }

    //this method sets all visited nodes to false in order to proceed
    // with the next search algorithm
    public void restart() {
        for (Vertex n : nodeCollection) {
            if (n.isVisited() == true) {
                n.setVisited(false);
            }
        }
    }

    //nodeInit() method will create nodes for the current graph
    public void nodeInit() {

        Scanner scan = new Scanner(System.in); //instantiate a new Scanner object

        System.out.println("This program will help you to create a graph and run the solving agents" +
                " in order to find a solution for the graph given by the user by " +
                " setting the initial state and goal state for the agents to in in the graph");
        System.out.println(" Problem Solving Agents");

        System.out.println("How many nodes do you want for your graph ?");
        System.out.print("\tUser feedback :=> ");

        //need to create a error a miss data handling
        setId_increment(scan.nextByte()); //Prompting the user to provide a integer value
        setAdjacent_matrix(new int[getId_increment()][getId_increment()]); //Creating and initializing the 2D array with a fixed size

        setAdjacent_matrix(); //Assigning a default value = -1 for the whole matrix


        //Loop to prompt the number of nodes wanted by the user
        for (int i = 0; i < this.id_increment; i++) {

            System.out.print("Enter a name for node " + (i + 1) + " :=> ");
            String nodeName = scan.next(); //prompting the user to provide a name

            System.out.print("Enter its heuristic value :=> ");
            int heuristic = scan.nextByte();//prompting the user to provide the heuristic Value

            Vertex newVertex = new Vertex(nodeName, i, heuristic); //creating and initializing a new object of type vertex
            nodeCollection.add(newVertex);

        }

        for (int i = 0; i < nodeCollection.size(); i++) //Display the created node in the nodeCollection
        {
            //Printing the successfully created nodes
            System.out.println("Node Details-> ( Name = " + nodeCollection.get(i).getElement() + "; ID = " + nodeCollection.get(i).getId() +
                    "; heuristic = " + nodeCollection.get(i).gethValue() + " ) was created successfully");
        }

        edgeInit(); //Calling the method to establish the connection between the nodes
    }

    //This edgeInit() will created connections between the created nodes
    // Edge is a line established from one node(vertex) to another
    private void edgeInit() { //edgeInit() must be private to avoid error handling

        Scanner scan = new Scanner(System.in);  //instantiate a new Scanner object
        String getNodeNameFromUser = "";
        int getNodeCount = 0;

        System.out.println("\n\n---------------------------------------------------------------------------");
        System.out.println(" The nodes need to be interconnected to each other to finalize the Graph.");

        System.out.println("\tImportant note:");
        System.out.println("\t\t#> If you have connected node A to B, you do not need to connect B to A again!");

        //prompting the user the number pf nodes to interconnect it with the current node
        for (int i = 0; i < nodeCollection.size(); i++) {
            System.out.print("\nHow many nodes to you want to connect with node " +
                    "(" + nodeCollection.get(i).getElement() + ") :=> ");
            getNodeCount = scan.nextInt();


            //This loop will extract the node names and check if they exist in the node collection
            for (int j = 0; j < getNodeCount; j++) {
                System.out.print("Enter the name of the neighbor node number " + (j + 1) + " for node (" + nodeCollection.get(i).getElement() + ") :=> ");
                //System.out.print("\tEnter the "+(j+1) +" neighbor node for node (" + nodeCollection[i].getName()+") :=> ");
                getNodeNameFromUser = scan.next();


                //check name in the node collection
                int counter = 0;
                int pathCost = 0;
                while (counter < nodeCollection.size()) {
                    //checks if node name exist in the node collection
                    if (nodeCollection.get(counter).getElement().toLowerCase().trim().equals(getNodeNameFromUser.toLowerCase().trim())) {
                        //checks if the node has established a connection already
                        if (getAdjacent_matrix()[i][counter] > -1 && (getAdjacent_matrix()[counter][i] > -1)) {
                            System.out.println("\t\t" + nodeCollection.get(i).getElement() + " is already connected to " +
                                    nodeCollection.get(counter).getElement());
                            break;
                        }
                        // Create a new connection for a given node
                        else {
                            System.out.print("\nWhat is the path cost for this connection :=> ");
                            pathCost = scan.nextShort();
                            getAdjacent_matrix()[i][counter] = pathCost + nodeCollection.get(counter).gethValue(); //assigning the path cost to the 2D Matrix + the heuristic value
                            getAdjacent_matrix()[counter][i] = pathCost + nodeCollection.get(i).gethValue(); //assigning the path cost to the 2D Matrix + the heuristic value
                            System.out.println(nodeCollection.get(i).getElement() + " connected to " +
                                    nodeCollection.get(counter).getElement() + " successfully!");

                            nodeCollection.get(i).addNeighbour(nodeCollection.get(counter));

                            //Printing the adjacent matrix for input visualization
                            for (int[] element : getAdjacent_matrix()) {
                                System.out.println(Arrays.toString(element));
                            }
                            break;
                        }
                    } else if (counter == (nodeCollection.size() - 1))//if node is not found and we exhaust our Array
                    {
                        System.out.println("The name was not found in the node collection");
                    }
                    counter++; //Incrementing count
                }
            }
        }

        System.out.println("The graph have been created successfully!");


        displayNodeCollection();
        setInitial_GoalState();
    }

    private void setInitial_GoalState()

    {
        Scanner scan = new Scanner(System.in);  //instantiate a new Scanner object
        String getNodeNameFromUser = "";

        //This loop will extract the node names and check if they exist in the node collection
        for (int j = 0; j < 1; j++) {
            System.out.print("\n\nEnter the name of initialSate node :=> ");
            //System.out.print("\tEnter the "+(j+1) +" neighbor node for node (" + nodeCollection[i].getName()+") :=> ");
            getNodeNameFromUser = scan.next();


            //check name in the node collection
            int counter = 0;
            while (counter < nodeCollection.size()) {
                //checks if node name exist in the node collection
                if (nodeCollection.get(counter).getElement().toLowerCase().trim().equals(getNodeNameFromUser.toLowerCase().trim())) {
                    this.initial = nodeCollection.get(counter);
                    System.out.println("Name found in the graph");
                } else if (counter == (nodeCollection.size() - 1) && !(nodeCollection.get(counter).getElement().toLowerCase().trim().equals(getNodeNameFromUser.toLowerCase().trim()))) //if node is not found and we exhaust our Array
                {
                    System.out.println("The name was not found in the node collection");
                }
                counter++; //Incrementing count
            }
        }

        for (int j = 0; j < 1; j++) {
            System.out.print("\nEnter the name of goalState node :=> ");
            //System.out.print("\tEnter the "+(j+1) +" neighbor node for node (" + nodeCollection[i].getName()+") :=> ");
            getNodeNameFromUser = scan.next();


            //check name in the node collection
            int counter = 0;
            while (counter < nodeCollection.size()) {
                //checks if node name exist in the node collection
                if (nodeCollection.get(counter).getElement().toLowerCase().trim().equals(getNodeNameFromUser.toLowerCase().trim())) {
                    this.goal = nodeCollection.get(counter);
                } else if (counter == (nodeCollection.size() - 1)) //if node is not found and we exhaust our Array
                {
                    System.out.println("The name was not found in the node collection");
                }
                counter++; //Incrementing count
            }
        }


    }

    private void displayNodeCollection() {
        for (int i = 0; i < nodeCollection.size(); i++) {
            System.out.println("Node name: " + nodeCollection.get(i).getElement() +
                    "\t Id: " + nodeCollection.get(i).getId() +
                    "\t h(n) = " + nodeCollection.get(i).gethValue() +
                    "\t Neighbor List: "
            );
            for (Vertex v : nodeCollection.get(i).getNeighbours()
            ) {
                System.out.println(v.getElement());
            }
        }
    }

    //Getters
    private int getId_increment() {
        return id_increment;
    }


    //Setters
    private void setId_increment(int id_increment) {
        this.id_increment = id_increment;
    }

    // Setting a default value -1 for the whole matrix
    private void setAdjacent_matrix() {
        //If value is greater than -1 it means that there is a connection established
        //      between two nodes
        for (int[] matrix : this.getAdjacent_matrix()) {
            Arrays.fill(matrix, -1);
        }
    }

    public int[][] getAdjacent_matrix() {
        return adjacent_matrix;
    }

    public void setAdjacent_matrix(int[][] adjacent_matrix) {
        this.adjacent_matrix = adjacent_matrix;
    }

    public Vertex getInitial() {
        return initial;
    }

    public Vertex getGoal() {
        return goal;
    }
}





