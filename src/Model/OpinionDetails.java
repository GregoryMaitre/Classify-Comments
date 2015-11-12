package Model;

import java.util.Vector;

public class OpinionDetails {
	public int id;
	public int id_detail;
	public int id_sentence;
	public String polarity;
	public int negation;
	public String polarity_word;
	public String aspect;
	public int polarity_word_position;
	public int aspect_position;
	public int polarity_word_component_id;
	public int aspect_component_id;
	public String relation;
	public String phrase;
	public int irony;
	public String target;
	public String target_aspect;

	@Override
	public String toString() {
		return "OpinionDetails [id=" + id + ", id_detail=" + id_detail + ", id_sentence=" + id_sentence + ", polarity="
				+ polarity + ", negation=" + negation + ", polarity_word=" + polarity_word + ", aspect=" + aspect
				+ ", polarity_word_position=" + polarity_word_position + ", aspect_position=" + aspect_position
				+ ", polarity_word_component_id=" + polarity_word_component_id + ", aspect_component_id="
				+ aspect_component_id + ", relation=" + relation + ", phrase=" + phrase + ", irony=" + irony
				+ ", target=" + target + ", target_aspect=" + target_aspect + "]";
	}

	public Vector<String> getForTable() {
		Vector<String> result = new Vector<String>();
		result.add(Integer.toString(id_detail));
		result.add(Integer.toString(id_sentence));
		result.add(polarity);
		result.add(Integer.toString(negation));
		result.add(polarity_word);
		result.add(aspect);
		result.add(phrase);
		result.add(Integer.toString(irony));
		result.add(target);
		result.add(target_aspect);
		return result;
	}
	
	public static Vector<String> getHeaders() {
		Vector<String> result = new Vector<String>();
		result.add("ID");
		result.add("Sentence ID");
		result.add("Polarity");
		result.add("Is negated");
		result.add("Polarity word");
		result.add("Aspect");
		result.add("Phrase");
		result.add("Is ironic");
		result.add("Target");
		result.add("Target aspect");
		return result;
	}
}
