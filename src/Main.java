/* This will be the main driver class to execute
and integrate all the program functionalities
*/

public class Main {

    public static void main(String[]args)
    {
        BFS start = new BFS();
        DFS launch = new DFS();

//        A_star starSearch = new A_star();
        Graph graph = new Graph();

        graph.nodeInit();

        System.out.println(graph.getNodeCollection().size());
        System.out.println("Breadth first search Agent\n");
        start.bfs(graph,graph.getInitial(),graph.getGoal());
        graph.restart();


        System.out.println(graph.getNodeCollection().size());
        System.out.println("\nDepth first search Agent");
        launch.dfs(graph,graph.getInitial(),graph.getGoal());




//        A_star aStarSearchEngine = new A_star();
//        List<Vertex> nodeVisited = new ArrayList<>();
//
//        nodeVisited = aStarSearchEngine.search(graph, 0,0);
//
//        System.out.println("A* visited tree");
//        for(Vertex n: nodeVisited){
//            if(n != null)
//            System.out.println(n.getElement());
//            else
//                System.out.println("Visited list exausted");
//        }




    }
}
