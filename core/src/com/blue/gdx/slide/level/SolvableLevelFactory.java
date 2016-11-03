package com.blue.gdx.slide.level;


public class SolvableLevelFactory {

	public static Level newSolvableLevel(int mapSize, int difficulty) {
		Level level = new Level(mapSize, mapSize);
		String solution;
		while ((solution = makeAndSolveLevel(level)).length() < difficulty);
		level.setSolutionString(solution);

		return level;
	}
	
	private static String makeAndSolveLevel(Level level) {
		String solution;
		level.replaceWithNewGameGrid();
		while((solution = Solver.solveLevel(level)) == null) {
			level.replaceWithNewGameGrid();
		}
		return solution;
	}
}
