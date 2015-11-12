package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class OpinionsDetails {
	private HashMap<Integer, ArrayList<OpinionDetails>> opinionsDetails;
	
	public OpinionsDetails() {
		opinionsDetails = new HashMap<Integer, ArrayList<OpinionDetails>>();
	}
		
	public ArrayList<OpinionDetails> get(int id) {
		return opinionsDetails.get(id);
	}
	
	public int size() {
		return opinionsDetails.keySet().size();
	}

	public void add(OpinionDetails detail) {
		ArrayList<OpinionDetails> opinionsDetails = get(detail.id);
		if (opinionsDetails == null) {
			this.opinionsDetails.put(detail.id, new ArrayList<OpinionDetails>());
			opinionsDetails = get(detail.id);
		}
		
		opinionsDetails.add(detail);
	}
}
