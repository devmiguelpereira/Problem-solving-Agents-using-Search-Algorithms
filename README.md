# Problem-solving-Agents-using-Search-Algorithms
The purpose of this project is to implement a problem-solving agent using a search algorithm. It will be implemented three different strategies: breadthfirst search, depth-first search and A∗. Given a problem, the implementation done will indicate whether there is a solution from the initial state to the goal state. When a solution exists, the program will indicate, for each strategy, all the steps taken, the states that have been visited and the ones that have been considered for examination.

Approach guide line

  In order to solve this project the following modules or feactures will be essential:
  
  Need:
         Node or Vertex,  
         Graph, 
         Search Algorithm;
         
  Description:
  
        -> Vertex: is a point where two or more curves, lines, or edges meet.
             :- Create a vertex class will allow the program to create vertices for our graph 
             :- The Node(Vertex) will posses the following properties:
                  Name, ID, heuristicValue, pathCostConnectionList, neighborNameList
                  
        -> Graph: a graph can be described in mathematical set notation, a graph is said to be a set of vertices V and a set of edges E.
             :- Graph(Vertices, Edges)
             :- Implementation
                  There are two ways to maintain the vertices and edges of a graph by using:
                      1. Adjacency List
                      2. Adjacency Matrix
       
       -> Search Algorithm
             :- A well-defined problem has to be provided in order to run the search agents successfully.
                - The problem can be defined by the following components:
                    .> Initial State
                    .> Actions
                    .> Transition Model
                    .> Successor
                    .> State Space (implicit)
                    .> Graph (implicit)
                    .> Path
                    .> Goal test
                    
                    Mainly taking:
                      .> Initial state
                      .> Actions
                      .> Transition function result -> getSuccessor
                      .> Path
                      .> Path cost
                      .> Goal test
                      
        Drafts of the concluded ideas are going to be uploaded on a .pdf documents with futher explanation.
                    
