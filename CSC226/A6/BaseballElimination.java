/* BaseballElimination.java
   CSC 226 - Fall 2023
   Assignment 5 - Baseball Elimination Program
   
   This template includes some testing code to help verify the implementation.
   To interactively provide test inputs, run the program with
	java BaseballElimination
	
   To conveniently test the algorithm with a large input, create a text file
   containing one or more test divisions (in the format described below) and run
   the program with
	java -cp .;algs4.jar BaseballElimination file.txt (Windows)
   or
    java -cp .:algs4.jar BaseballElimination file.txt (Linux or Mac)
   where file.txt is replaced by the name of the text file.
   
   The input consists of an integer representing the number of teams in the division and then
   for each team, the team name (no whitespace), number of wins, number of losses, and a list
   of integers represnting the number of games remaining against each team (in order from the first
   team to the last). That is, the text file looks like:
   
	<number of teams in division>
	<team1_name wins losses games_vs_team1 games_vs_team2 ... games_vs_teamn>
	...
	<teamn_name wins losses games_vs_team1 games_vs_team2 ... games_vs_teamn>

	
   An input file can contain an unlimited number of divisions but all team names are unique, i.e.
   no team can be in more than one division.
*/

import edu.princeton.cs.algs4.*;
import java.util.*;
import java.io.File;
import java.lang.reflect.Array;

//Do not change the name of the BaseballElimination class
public class BaseballElimination{
	
	// We use an ArrayList to keep track of the eliminated teams.
	public ArrayList<String> eliminated = new ArrayList<String>();

	/* BaseballElimination(s)
		Given an input stream connected to a collection of baseball division
		standings we determine for each division which teams have been eliminated 
		from the playoffs. For each team in each division we create a flow network
		and determine the maxflow in that network. If the maxflow exceeds the number
		of inter-divisional games between all other teams in the division, the current
		team is eliminated.
	*/
	public BaseballElimination(Scanner s){
		final int T_NAME = 0;
		final int WINS = 1;
		final int LEFT = 2;
		final int SOURCE = 0;
		final int SINK = 1;

		ArrayList<String[]> data = new ArrayList<String[]>();
		
		//populate data from txt file
		while(s.hasNext()){
			String[] team = s.nextLine().split("\\s+");
			data.add(team);
			System.out.println(Arrays.toString(team));
		}

		//janky fix
		int numTeams = Integer.parseInt(data.get(0)[0]);
		data.remove(0);

		//create flow network for each team
		for(int i = 0; i < numTeams; i++){
			//create flow network
			int numVertices = 2 + (numTeams - 1) + ((numTeams - 1) * (numTeams - 2) / 2);
			FlowNetwork fn = new FlowNetwork(numVertices);
			int gameVertices = 2;
			int teamVertices = 0;
			int gameIndex = 0;
			int teamIndex = 0;
			int gameOffset = 0;
			int teamOffset = 0;

			//set team vertices
			for(int j = 0; j < numTeams; j++){
				if(j != i){
					fn.addEdge(new FlowEdge(numVertices - 1, gameVertices + gameOffset + j, Double.parseDouble(data.get(j)[WINS]) + Double.parseDouble(data.get(j)[LEFT])));
					teamVertices++;
				}
			}

			//set game vertices
			for (int j = 0; j < numTeams - 1; j++) {
				for (int k = j + 1; k < numTeams; k++) {
					if (j != i && k != i) {
						int team1Vertex = gameVertices + gameOffset + j;
						int team2Vertex = gameVertices + gameOffset + k;
			
						fn.addEdge(new FlowEdge(team1Vertex, gameVertices + numTeams - 1, Double.parseDouble(data.get(i)[LEFT])));
						fn.addEdge(new FlowEdge(gameVertices + numTeams - 1, team1Vertex, Double.POSITIVE_INFINITY));
						fn.addEdge(new FlowEdge(team2Vertex, gameVertices + numTeams - 1, Double.parseDouble(data.get(i)[LEFT])));
						fn.addEdge(new FlowEdge(gameVertices + numTeams - 1, team2Vertex, Double.POSITIVE_INFINITY));
						
						gameVertices++;
					}
				}
				gameOffset += numTeams - 1;
	
			}

			//find max flow
			FordFulkerson ff = new FordFulkerson(fn, 0, 1);

			//check if team is eliminated
			for(int j = 0; j < numTeams; j++){
				if(ff.inCut(j + 1)){
					eliminated.add(data.get(i)[T_NAME]);
					break;
				}
			}
		}




	}
	 	
	/* main()
	   Contains code to test the BaseballElimantion function. You may modify the
	   testing code if needed, but nothing in this function will be considered
	   during marking, and the testing process used for marking will not
	   execute any of the code below.
	*/
	public static void main(String[] args){
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Reading input values from stdin.\n");
		}
		
		BaseballElimination be = new BaseballElimination(s);		
		
		if (be.eliminated.size() == 0)
			System.out.println("No teams have been eliminated.");
		else
			System.out.println("Teams eliminated: " + be.eliminated);
	}
}
