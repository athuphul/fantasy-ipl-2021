import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IPLTransfers_Planner {
	
	final static int MAX_PLAYERS_FROM_ONE_TEAM = 7;
	final static int MIN_PLAYERS_FROM_ONE_TEAM = 0;
	final static int NUM_PLAYERS_IN_ONE_TEAM = 11;
	final static int NUM_TEAMS = 8;
	final static int TOT_FIXTURES = 56;
	final static int TOT_UNCAPPED_TRANSFERS = TOT_FIXTURES - 1;	// One for each group stage match, except the first one
	final static int TOT_GROUP_STAGE_TRANSFERS = 110;
	
	/*
	 * If fixtureGrid[i][j] is true, it means team at index i plays on matchday j + 1
	 */
	static boolean[][] fixtureGrid = new boolean[NUM_TEAMS][TOT_FIXTURES];
	static Map<Integer, String> teamMappings = new HashMap<>();
	static List<int[]> ans = new LinkedList<>();
	
	static double avgPPM = 0.0;	// Players per match
	
	/*
	 * Driver
	 */
	public static void main(String[] args) {
		
		// Put team mappings
		teamMappings.put(-1, "EMPTY");
		teamMappings.put( 0, "MI");
		teamMappings.put( 1, "RCB");
		teamMappings.put( 2, "CSK");
		teamMappings.put( 3, "DC");
		teamMappings.put( 4, "SRH");
		teamMappings.put( 5, "KKR");
		teamMappings.put( 6, "RR");
		teamMappings.put( 7, "PBKS");
		
		// Populate fixture grid
		// MI
		fixtureGrid[0][0]  = true;
		fixtureGrid[0][4]  = true;
		fixtureGrid[0][8]  = true;
		fixtureGrid[0][12] = true;
		fixtureGrid[0][16] = true;
		fixtureGrid[0][23] = true;
		fixtureGrid[0][26] = true;
		fixtureGrid[0][30] = true;
		fixtureGrid[0][35] = true;
		fixtureGrid[0][38] = true;
		fixtureGrid[0][41] = true;
		fixtureGrid[0][46] = true;
		fixtureGrid[0][50] = true;
		fixtureGrid[0][54] = true;
		
		// RCB
		fixtureGrid[1][0]  = true;
		fixtureGrid[1][5]  = true;
		fixtureGrid[1][9]  = true;
		fixtureGrid[1][15] = true;
		fixtureGrid[1][18] = true;
		fixtureGrid[1][21] = true;
		fixtureGrid[1][25] = true;
		fixtureGrid[1][29] = true;
		fixtureGrid[1][32] = true;
		fixtureGrid[1][37] = true;
		fixtureGrid[1][43] = true;
		fixtureGrid[1][45] = true;
		fixtureGrid[1][50] = true;
		fixtureGrid[1][55] = true;
		
		// CSK
		fixtureGrid[2][1]  = true;
		fixtureGrid[2][7]  = true;
		fixtureGrid[2][11] = true;
		fixtureGrid[2][14] = true;
		fixtureGrid[2][18] = true;
		fixtureGrid[2][22] = true;
		fixtureGrid[2][26] = true;
		fixtureGrid[2][31] = true;
		fixtureGrid[2][33] = true;
		fixtureGrid[2][36] = true;
		fixtureGrid[2][40] = true;
		fixtureGrid[2][46] = true;
		fixtureGrid[2][52] = true;
		fixtureGrid[2][55] = true;
		
		// DC
		fixtureGrid[3][1]  = true;
		fixtureGrid[3][6]  = true;
		fixtureGrid[3][10] = true;
		fixtureGrid[3][12] = true;
		fixtureGrid[3][19] = true;
		fixtureGrid[3][21] = true;
		fixtureGrid[3][24] = true;
		fixtureGrid[3][28] = true;
		fixtureGrid[3][34] = true;
		fixtureGrid[3][39] = true;
		fixtureGrid[3][43] = true;
		fixtureGrid[3][47] = true;
		fixtureGrid[3][52] = true;
		fixtureGrid[3][54] = true;
		
		// SRH
		fixtureGrid[4][2]  = true;
		fixtureGrid[4][5]  = true;
		fixtureGrid[4][8]  = true;
		fixtureGrid[4][13] = true;
		fixtureGrid[4][19] = true;
		fixtureGrid[4][22] = true;
		fixtureGrid[4][27] = true;
		fixtureGrid[4][30] = true;
		fixtureGrid[4][33] = true;
		fixtureGrid[4][37] = true;
		fixtureGrid[4][42] = true;
		fixtureGrid[4][47] = true;
		fixtureGrid[4][49] = true;
		fixtureGrid[4][51] = true;
		
		// KKR
		fixtureGrid[5][2]  = true;
		fixtureGrid[5][4]  = true;
		fixtureGrid[5][9]  = true;
		fixtureGrid[5][14] = true;
		fixtureGrid[5][17] = true;
		fixtureGrid[5][20] = true;
		fixtureGrid[5][24] = true;
		fixtureGrid[5][29] = true;
		fixtureGrid[5][34] = true;
		fixtureGrid[5][38] = true;
		fixtureGrid[5][40] = true;
		fixtureGrid[5][44] = true;
		fixtureGrid[5][48] = true;
		fixtureGrid[5][51] = true;
		
		// RR
		fixtureGrid[6][3]  = true;
		fixtureGrid[6][6]  = true;
		fixtureGrid[6][11] = true;
		fixtureGrid[6][15] = true;
		fixtureGrid[6][17] = true;
		fixtureGrid[6][23] = true;
		fixtureGrid[6][27] = true;
		fixtureGrid[6][31] = true;
		fixtureGrid[6][35] = true;
		fixtureGrid[6][39] = true;
		fixtureGrid[6][42] = true;
		fixtureGrid[6][45] = true;
		fixtureGrid[6][48] = true;
		fixtureGrid[6][53] = true;
		
		// PBKS
		fixtureGrid[7][3]  = true;
		fixtureGrid[7][7]  = true;
		fixtureGrid[7][10] = true;
		fixtureGrid[7][13] = true;
		fixtureGrid[7][16] = true;
		fixtureGrid[7][20] = true;
		fixtureGrid[7][25] = true;
		fixtureGrid[7][28] = true;
		fixtureGrid[7][32] = true;
		fixtureGrid[7][36] = true;
		fixtureGrid[7][41] = true;
		fixtureGrid[7][44] = true;
		fixtureGrid[7][49] = true;
		fixtureGrid[7][53] = true;
		
		// IPL Transfer variables
		// 11 to start team + 110 transfers throughout group stage + 55 uncapped transfers
		int numTransfers = NUM_PLAYERS_IN_ONE_TEAM + TOT_GROUP_STAGE_TRANSFERS + TOT_UNCAPPED_TRANSFERS;
		int matchesTBP = TOT_FIXTURES;
		int currentMatchDay = 1;
		
		int[] teamPlayers = new int[NUM_PLAYERS_IN_ONE_TEAM];
		for (int i = 0; i < teamPlayers.length; i++)
			teamPlayers[i] = -1;	// Initialize team to empty
		
		// Each integer array in this list of 56 such arrays represents the optimal team on matchDay i + 1, where i goes from 0->55
		List<int[]> transferPlan = bestTrans(numTransfers, matchesTBP, currentMatchDay, teamPlayers);
		
		// Print transferPlan
		System.out.println();
		printPlan(transferPlan);
	}
	
	/*
	 * Checks if the team passed as param is valid
	 * MAX and MIN player conditions are checked
	 * EMPTY player is NOT checked
	 */
	private static boolean isValidTeam(int[] teams) {
		int[] fromTeam = new int[NUM_TEAMS];
		
		for (int player : teams)
			if (player > -1)
				fromTeam[player]++;
		
		for (int team : fromTeam)
			if (team > MAX_PLAYERS_FROM_ONE_TEAM || team < MIN_PLAYERS_FROM_ONE_TEAM)
				return false;
		
		return true;
	}
	
	/*
	 * Checks for EMPTY players
	 */
	private static boolean isEmptySlot(int[] teams) {
		for (int player : teams)
			if (player == -1)
				return true;
		return false;
	}
	
	/*
	 * Utilizes the backtracking function to compute the best transfer plan for PPG
	 */
	private static List<int[]> bestTrans(int numTransfers, int matchesTBP, int currentMatchDay, int[] teamPlayers) {
		List<int[]> cache = new LinkedList<>();
		
		backtrack(numTransfers, matchesTBP, currentMatchDay, teamPlayers, cache, 0);
		
		return ans;
	}
	
	/*
	 * Prints the list(transfer plan) passed as parameter
	 */
	private static void printPlan(List<int[]> list) {
		for (int[] transfer : list) {
			for (int player : transfer)
				System.out.print(teamMappings.get(player) + ", ");
			System.out.println();
		}
	}

	/*
	 * Calculates the average players per match (PPM)
	 * Cache is the list of teams per matchDay(56 such matchdays), so 56 team arrays in cache list
	 * Each team has 11 slots, so 11 integers
	 * Each slot has a corresponding team num(eg. 0 for MI)
	 */
	private static double calculateAVGPPM(List<int[]> cache) {
		int count = 0;
		/*
		 * Each element in cache is of size 11 (matchDayTeam)
		 * Each element in matchDayTeam maps to a team
		 * Can be checked by i (matchDayNumber), matchDayTeam[j] (team Index)
		 * fixtureGrid[matchDayTeam[j]][i], then ++count
		 */
		for (int i = 0; i < cache.size(); i++) {
			int[] matchDayTeam = cache.get(i);
			for (int j = 0; j < matchDayTeam.length; j++) {
				if (fixtureGrid[matchDayTeam[j]][i])
					count++;
			}
		}
		
		return (double)count/TOT_FIXTURES;
	}
	
	/*
	 * The magical backtrack function
	 */
	private static void backtrack(int numTransfers, int matchesTBP, int currentMatchDay, int[] teamPlayers, List<int[]> cache, int startIndex) {
		/*
		 * This function would be in an invalid state if any of the following are true :
		 * 1. The matches to be played are less than 0
		 * 2. The current matchday is > 56 + 1 (to be in a safe state, currentMatchDay + matchesTBP should always equal 57)
		 * 3. The number of transfers for matchday x is less than 56 - x (because you get one free uncapped transfer per match, these cannot be used before that matchday)
		 * 4. The existing team for this matchday is invalid, i.e. contains more than 7 or less than 0 players from one team
		 * 5. The cache consists of a team that started out with an 'empty' slot on the first matchday
		 */
		if (matchesTBP < 0 || currentMatchDay > TOT_FIXTURES + 1 || numTransfers < TOT_FIXTURES - currentMatchDay 
			|| !isValidTeam(teamPlayers) || (cache.size() > 0 && isEmptySlot(cache.get(0))))
			return;

		/*
		 * This function reaches a terminal/final state if any of the following are true :
		 * 1. The matches to be played are equal to 0 (i.e. we've played all matches in the group stages)
		 * 2. The current matchday is 56 + 1 (finished playing all 56 matches, now time to see if optimal combination or not in the cache)
		 */
		if (matchesTBP == 0 || currentMatchDay == TOT_FIXTURES + 1) {
			// Calculate avgPPM for current cache
			double avgPPM_currSet = calculateAVGPPM(cache);
			
			// DEBUG OUTPUT - 56 matchday teams in this combination
			printPlan(cache);
			System.out.println(avgPPM_currSet);
			System.out.println();
			// DEBUG OUTPUT Ends
			
			if (avgPPM_currSet > avgPPM) {
				avgPPM = avgPPM_currSet;
				ans = new LinkedList<>(cache);
			}
			return;
		}
		
		
		// Backtracking transfers, possibilities - max one for each player in the team on the current matchDay
		/*
		 * Players in today's matchday team until index startIndex in the array are fixed (i.e. their transfer decision has already been made by the caller)
		 * This part takes care of the rest of the possibilities
		 */
		for (int i = startIndex; i < NUM_PLAYERS_IN_ONE_TEAM; i++) {
			// Either make a transfer on the player at this index, or don't
			// Make a transfer - go through all teams, and put a player from that team in place of current player
			for (int j = 0; j < NUM_TEAMS; j++) {				
				// If player from this team already, don't make a transfer
				// OPTIMIZATION : Make a transfer only if that team plays this matchday
				// If fixtureGrid[i][j] is true, it means team at index i plays on matchday j + 1
				if (teamPlayers[i] == j || !fixtureGrid[j][currentMatchDay - 1]) {
					continue;
				}
				// Make a transfer, one by one for player for each team
				int temp = teamPlayers[i];
				teamPlayers[i] = j;
				backtrack(numTransfers - 1, matchesTBP, currentMatchDay, teamPlayers, cache, i + 1);
				teamPlayers[i] = temp;
			}
			// Don't make a transfer - move on to the next index i in the team
		}
		
		// Move to the next matchDay
		/*
		 * When moving to the next matchday, we have finalized our team (playing XI) for this matchday
		 * We now add it to the cache, then backtrack and remove it from the cache
		 */
		cache.add(teamPlayers);
		backtrack(numTransfers, matchesTBP - 1, currentMatchDay + 1, teamPlayers, cache, 0);
		((LinkedList<int[]>) cache).removeLast();
	}
}