package cs224n.corefsystems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cs224n.coref.ClusteredMention;
import cs224n.coref.Document;
import cs224n.coref.Entity;
import cs224n.coref.Mention;
import cs224n.util.Pair;

public class OneCluster implements CoreferenceSystem {

	@Override
	public void train(Collection<Pair<Document, List<Entity>>> trainingData) {
		// TODO Auto-generated method stub

	}

	// @Override

	public List<ClusteredMention> runCoreference(Document doc) {
		ClusteredMention cm = null;
		List<ClusteredMention> mentions = new ArrayList<ClusteredMention>();

		for (Mention mention : doc.getMentions()) {
			if (cm == null) {
				cm = mention.markSingleton();
			}
			mention.markCoreferent(cm.entity);
			mentions.add(mention.markCoreferent(cm.entity));
		}
		return mentions;

	}
}
/*
 * public List<ClusteredMention> runCoreference(Document doc) {
 * 
 * ClusteredMention singleCluster = null; List<ClusteredMention> mentions = new
 * ArrayList<ClusteredMention>();
 * 
 * // For each mention for (Mention m : doc.getMentions()) { //TODO: Change this
 * to addAll, which was given in the starter code. // Mark the first mention as
 * the single cluster if (singleCluster == null) { singleCluster =
 * m.markSingleton(); mentions.add(singleCluster); } else { // Add all other
 * mentions to that cluster
 * mentions.add(m.markCoreferent(singleCluster.entity)); } }
 * 
 * return mentions; }
 */
// }
