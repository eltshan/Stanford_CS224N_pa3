package cs224n.corefsystems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cs224n.coref.ClusteredMention;
import cs224n.coref.Document;
import cs224n.coref.Entity;
import cs224n.coref.Mention;
import cs224n.util.Pair;

public class AllSingleton implements CoreferenceSystem {

	@Override
	public void train(Collection<Pair<Document, List<Entity>>> trainingData) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ClusteredMention> runCoreference(Document doc) {
		// TODO Auto-generated method stub
		List<ClusteredMention> mentions = new ArrayList<ClusteredMention>();

		// For each mention
		for (Mention mention : doc.getMentions()) {
			mentions.add(mention.markSingleton());
		}
		return mentions;
	}

}
