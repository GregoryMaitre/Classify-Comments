package Model;

public class Questions {
	public static String[] QUESTIONS = { 
			"Est-ce un commentaire positif?", 
			"Est-ce qu'il y a un pronom?", 
			"Est-ce qu'il y a de l'ironie?", 
			"Est-ce qu'il y a une ponctuation anormale?",
			"Est-ce qu'il y a des guillemets?",
			"Est-ce qu'il y a des interjections?",
			"Est-ce qu'il y a des laughter expressions?"};
	public static String[][] ANSWERS = { 
			{ "Commentaire positif", "Commentaire neutre", "Commentaire negatif" }, 
			{ "I we", "you he, she, they", "Aucun pronom" },
			{ "Il y a de l ironie", "Il n'y a pas d ironie", "Il y a peut etre de l ironie" },
			{ "Il y a une ponctuation anormale", "Il n y a pas de ponctuation anormale"} ,
			{ "Il y a des guillemets", "Il n y a pas de guillemets"},
			{ "Il y a des interjections", "Il n y a pas d interjections"},
			{ "Il y a des laughter expressions", "Il n y a pas de laughter expressions"}};
}
