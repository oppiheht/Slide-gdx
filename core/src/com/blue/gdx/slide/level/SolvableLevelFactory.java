package com.blue.gdx.slide.level;


import com.badlogic.gdx.math.MathUtils;

public class SolvableLevelFactory {

	public static Level newSolvableLevel(int mapSize, int minDifficulty, int maxDifficulty) {
		Level level = new Level(mapSize, mapSize);
		int difficulty = MathUtils.random(minDifficulty, maxDifficulty);
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
