import java.util.ArrayList;
import java.util.List;

public class Vertex {

    //Declaration of the private fields
    private String element;
    private boolean visited;
    private int id;
    private List<Vertex> neighbours;
    private int hValue;





    //Methods

    //default Constructor
    public Vertex(String e, int id, int hValue){
        this.setElement(e);
        this.id = id;
        this.hValue = hValue;
        this.visited = false;
        this.neighbours = new ArrayList<>();

    }

    public boolean isVisited(){
        return this.visited;
    }

    public void addNeighbour(Vertex n)
    {
        this.neighbours.add(n);
    }

    //Creating getters and setters for the private fields

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public List<Vertex> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Vertex> neighbours) {
        this.neighbours = neighbours;
    }

    public void setVisited(boolean status)
    {
        this.visited = status;
    }



    public int getId()
    {
        return id;
    }


    public int gethValue() {
        return hValue;
    }


}