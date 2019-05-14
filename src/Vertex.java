import java.util.ArrayList;

public class Vertex {

    //defining the fields or properties of our vertex
    private String name; // this field will give a name to the vertex
    private int id; // this field will give an identifier value to the vertex
    private int heuristicValue; // this field will hold the heuristic value of the vertex
    // this field will keep track of the path cost to the neighbor vertices of the current vertex
    private ArrayList pathCostConnectionList = new ArrayList();
    //this field will store the neighbor vertices name
    private ArrayList<Vertex> neighborNameList = new ArrayList();


    //Creating a default constructor that will accept two parameters

//    public Vertex(String name, int id)
//    {
//        this.name = name;
//        this.id = id;
//    }

    //constructor overloading
    public Vertex(String name, int id, int heuristicValue)
    {
        this.name = name;
        this.id = id;
        this.heuristicValue = heuristicValue;
    }

    //defining getters for data retrieval
    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }

    public int getHeuristicValue() {
        return heuristicValue;
    }

    public ArrayList getPathCostConnectionList()
    {
        return pathCostConnectionList;
    }

    public ArrayList getNeighborNameList(){return neighborNameList;}

    //defining setters
    //this method will set the pathCost of the neighbor vertices of the current Vertex

    public void setPathCostConnectionList(int pathCost)
    {
        this.pathCostConnectionList.add(pathCost);

    }

    //adding neighbor vertices to the List
    public void setNeighborNameList(Vertex vertex)
    {
        this.neighborNameList.add(vertex);
    }
}