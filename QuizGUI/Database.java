import java.util.ArrayList;

public class Database {

	private static ArrayList<Question> questions = new ArrayList<>();

	public static void Add(Question q) {
		questions.add(q);

	}

	public static void remove(Question q) {
		questions.remove(q);

	}

	public static void remove(int index) {
		questions.remove(index);
	}

	public static Question get(int index) {
		return questions.get(index);
	}

	public static int Size() {
		return questions.size();
	}

	public static boolean CheckAnswer(int choice, int question) {
		String correct = questions.get(question).getAnswer();
		if (correct.equals(questions.get(question).getOption1()) && choice == 1)
			return true;
		else if (correct.equals(questions.get(question).getOption2()) && choice == 2)
			return true;
		else if (correct.equals(questions.get(question).getOption3()) && choice == 3)
			return true;
		else if (correct.equals(questions.get(question).getOption4()) && choice == 4)
			return true;

		else
			return false;

	}

	public static void manuallyAdd() {
		questions.add(new Question("Who is the current president of US?", "Joe Biden", "Obama", "Joe Biden", "Trump",
				"Zack"));
		questions.add(new Question("Which is not a planet?", "Earth", "Earth", "Jupiter", "Moon", "Saturn"));
		questions.add(new Question("Capital of Japan?", "Tokyo", "Osaka", "Tukyu", "Beijing", "Tokyo"));
		questions.add(new Question("Which form of government can be seen in Malaysia?", "Constitutional Monarchy", "Parliamentary", "Presidential", "Constitutional Monarchy",
				"Dictatorship"));
		questions.add(new Question("Who is the current president of US?", "Joe Biden", "Obama", "Joe Biden", "Trump",
				"Zack"));
		questions.add(new Question("NASA was established in?", "1958", "1968", "1972", "1956",
				"1958"));
		questions.add(new Question("Not a neighboring country of Malaysia?", "Vietnam", "Vietnam", "Brunei", "Indonesia",
				"Thailand"));

	}

}
