package Model;

public class Questions {
	public static String[] QUESTIONS = { 
			"Est-ce un commentaire positif?", 
			"Est-ce un commentaire long?",
			"Est-ce qu'il y a une négation?", 
			"Est-ce qu'il y a un pronom?", 
			"Est-ce qu'il y a de l'ironie?", 
			"Est-ce qu'il y a un smiley?", 
			"Est-ce qu'il y a des gros mots?",
			"Est-ce Pro ou Con la controverse?"};
	public static String[][] ANSWERS = { 
			{ "Commentaire positif", "Commentaire Neutre", "Commentaire negatif" },
			{ "Commentaire long", "Commentaire court" }, 
			{ "Présence de négation", "Absence de négation" },
			{ "I, we", "you, he, she, they", "Aucun pronom" },
			{ "Il y a de l'ironie", "Il n'y a pas d'ironie" },
			{ "Il y a un smiley positif", "Il y a un smiley négatif", "Il n'y a pas de smiley" },
			{ "Il y a des gros mots", "Il n'y a pas de gros mots" }, 
			{ "Pro", "Con" , "Neutre"}};
}
