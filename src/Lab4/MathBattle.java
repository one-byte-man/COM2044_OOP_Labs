package Lab4;
import java.security.SecureRandom;

public class MathBattle {
	
	enum gameResult {WIN, LOSS, DRAW}
	
	public static final SecureRandom random = new SecureRandom();
	public static final int MAX_DICE_VALUE = 10;
	public static final int MIN_DICE_VALUE = 1;
	public static final int ERROR_TOLERANCE = 2;
	
	
	// Bias zeka seviyesi / tahmin yakınlığı verilmedi ben +-3 seçtim
	// Oyuncunun doğru değere hangi yakınlıkta tahmin etmesi verilmediğinden ben default +-6 seçtim
	public static final int NORMAL_GUESS = 6;
	public static final int BIAS_GUESS = 3;


	public static void main(String[] args) {
		
		int round = checkInputs(args);
		int counter = 1;
		String p1_name = args[0];
		String p2_name = args[1];
		boolean p1_bias = isBias();
		boolean p2_bias = isBias();
		int p1_score = 0;
		int p2_score = 0;
		int p1_dice;
		int p2_dice;
		int p1_ans = 0;
		int p2_ans = 0;
		int p1_correct;
		int p2_correct;
		boolean p1_isCorrect;
		boolean p2_isCorrect;
		
		
		// printRoundResult içine direkt işlem stringini ('3 + 5 = ?') atayamadığımdan,
		// 2 kontrol değeri ile complex bir mimari gerekti. (isRound, isGuess)
		// Çok fazla gereksiz tekrar sebep oldu ama ilgili PDF'e uymam gerekli
		
		do {
			System.out.printf("[Round %d]\n",counter++);
			
			p1_dice = random.nextInt(MAX_DICE_VALUE) + MIN_DICE_VALUE;
			p2_dice = random.nextInt(MAX_DICE_VALUE) + MIN_DICE_VALUE;
			
			printRoundResult(p1_name,p1_dice, p1_ans, true, false);
			printRoundResult(p2_name,p2_dice, p2_ans, true, false);
			System.out.println();
			
			printRoundResult(p1_name,p1_dice, p1_ans, false, true);
			p1_correct = generateQuestion(mapDiceToDifficulty(p1_dice));
			p1_ans = simulateGuess(p1_correct,p1_bias);
			p1_isCorrect = checkAnswer(p1_correct,p1_ans);
			printRoundResult(p1_name,p1_dice, p1_ans, false, false);
			
			printRoundResult(p2_name,p2_dice, p2_ans, false, true);
			p2_correct = generateQuestion(mapDiceToDifficulty(p2_dice));
			p2_ans = simulateGuess(p2_correct,p2_bias);
			p2_isCorrect = checkAnswer(p2_correct,p2_ans);
			printRoundResult(p2_name,p2_dice, p2_ans, false, false);
			
			
			if(p1_isCorrect) p1_score++;
			if(p2_isCorrect) p2_score++;
			round--;
			
			System.out.print("\n");
		} while (round != 0);
		
		printSummary(p1_name,p2_name,p1_score,p2_score);
	}
	
	public static int generateQuestion( int difficultyLevel ) {
		int num1 = 0;
		int num2 = 0;
		int operator = -1;
		String op = "";
		int correctAnswer = -1;
		
		if (difficultyLevel == 1) {
			// + , -, *, /, %
			operator = random.nextInt(2);
			num1 = random.nextInt(10);
			num2 = random.nextInt(10);
		}
		else if (difficultyLevel == 2) {
			operator = random.nextInt(3);
			num1 = random.nextInt(21);
			num2 = random.nextInt(21);
		}
		else if (difficultyLevel == 3) {
			operator = random.nextInt(5);
			num1 = random.nextInt(81)+20;
			num2 = random.nextInt(81)+20;
		}
		
		switch (operator){
			case 0:
				op = " + ";
				correctAnswer = num1 + num2;
				break;
			case 1:
				op = " - ";
				correctAnswer = num1 - num2;
				break;
			case 2:
				op = " * ";
				correctAnswer = num1 * num2;
				break;
			case 3:
				op = " / ";
				correctAnswer = num1 / num2;
				break;
			case 4:
				op = " % ";
				correctAnswer = num1 % num2;
				break;
		}
		// İşlemi yazdırdım
		System.out.printf("%d%s%d = ?\n",num1,op,num2);
		return correctAnswer;
	}
	
	public static boolean checkAnswer(int expected, int given) {
		if (Math.abs(expected - given) <= ERROR_TOLERANCE) return true; // Math kullandım
		return false;
	}
	
	public static int simulateGuess(int correctAnswer) {
		return simulateGuess(correctAnswer, false);
	}
	
	public static int simulateGuess(int correctAnswer, boolean bias)  {
		int guessAnswer = correctAnswer;
		if(bias) {
			guessAnswer += random.nextInt(BIAS_GUESS*2 + 1) - BIAS_GUESS;
		}
		else {
			guessAnswer += random.nextInt(NORMAL_GUESS*2 + 1) - NORMAL_GUESS;
		}
		return guessAnswer;
	}
	
	public static void printRoundResult(String name, int dice, int guess, boolean isRound, boolean isGuess) {
		if (!isRound) {
			if(isGuess)
				System.out.printf("%s's question: ",name);
			else 
				System.out.printf("%s guessed %d\n",name,guess);
		}
		else {
			System.out.printf("%s rolls %d\n",name,dice);
		}
	}
	
	public static void printSummary(String name1, String name2, int score1, int score2 ) {
		gameResult result;
		System.out.println("== Final Scores ==");
		System.out.printf("%s: %d\n",name1,score1);
		System.out.printf("%s: %d\n",name2,score2);
		
		if(score1 > score2) result = gameResult.WIN;
		else if (score1 < score2) result = gameResult.LOSS;
		else result =  gameResult.DRAW;
		
		switch (result) {
			case WIN:
				System.out.printf("Result: %s WINS!\n",name1);
				break;
			case LOSS:
				System.out.printf("Result: %s WINS!\n",name2);
				break;
			case DRAW:
				System.out.printf("Result: DRAW\n");
				break;
		}
	}
	
	public static int mapDiceToDifficulty ( int dieRoll ) {
		if ( dieRoll <= 3) return 1;
		else if ( dieRoll <= 7) return 2;
		else return 3;
	}
	
	public static int checkInputs(String[] input) {
	    if(input.length != 3) {
	        System.out.println("INVALID INPUT : The input must contain 3 inputs!\n");
	        System.exit(1);
	    }
	    
	    int num = 0;
	    try {
	        num = Integer.parseInt(input[2]);
	    } catch (NumberFormatException e) {
	        System.out.println("INVALID INPUT : The round input must be a number!\n");
	        System.exit(1);
	    }
	    
	    if(num < 1) {
	        System.out.println("INVALID INPUT : The round input must be positive!\n");
	        System.exit(1);
	    }
	    return num;
	}
	
	public static boolean isBias() {
		int num = random.nextInt(5);
		if(num == 0) return true;
		return false;
	}
	
}
