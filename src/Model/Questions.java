package Model;

public class Questions {
	public static String[] QUESTIONS = { "Est-ce un commentaire positif?", "Est-ce un commentaire long?",
			"Est-ce qu'il y a une négation?", "Est-ce qu'il y a un pronom?", "Présence de mots positifs?",
			"Est-ce qu'il y a une opinion cachée?", "Est-ce qu'il y a un smiley?", "Est-ce qu'il y a des gros mots?" };
	public static String[][] ANSWERS = { { "Commentaire positif", "Commentaire Neutre", "Commentaire negatif" },
			{ "Commentaire long", "Commentaire court" }, { "Présence de négation", "Absence de négation" },
			{ "I, we", "you, he, she, they", "Aucun pronom" },
			{ "Présence de mots positifs", "Présence de mots négatifs", "Que des mots neutres" },
			{ "Il y a une opinion caché positive", "Il y a une opinion caché négative", "Il n'y a pas d'opinion caché" },
			{ "Il y a un smiley positif", "Il y a un smiley négatif", "Il n'y a pas de smiley" },
			{ "Il y a des gros mots", "Il n'y a pas de gros mots" }, };
}
