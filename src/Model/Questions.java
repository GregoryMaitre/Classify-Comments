package Model;

public class Questions {
	public static String[] QUESTIONS = { "Est-ce un commentaire positif?", "Est-ce un commentaire long?",
			"Est-ce qu'il y a une n�gation?", "Est-ce qu'il y a un pronom?", "Pr�sence de mots positifs?",
			"Est-ce qu'il y a une opinion cach�e?", "Est-ce qu'il y a un smiley?", "Est-ce qu'il y a des gros mots?" };
	public static String[][] ANSWERS = { { "Commentaire positif", "Commentaire Neutre", "Commentaire negatif" },
			{ "Commentaire long", "Commentaire court" }, { "Pr�sence de n�gation", "Absence de n�gation" },
			{ "I, we", "you, he, she, they", "Aucun pronom" },
			{ "Pr�sence de mots positifs", "Pr�sence de mots n�gatifs", "Que des mots neutres" },
			{ "Il y a une opinion cach� positive", "Il y a une opinion cach� n�gative", "Il n'y a pas d'opinion cach�" },
			{ "Il y a un smiley positif", "Il y a un smiley n�gatif", "Il n'y a pas de smiley" },
			{ "Il y a des gros mots", "Il n'y a pas de gros mots" }, };
}
