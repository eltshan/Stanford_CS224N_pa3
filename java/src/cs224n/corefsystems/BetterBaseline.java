package cs224n.corefsystems;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import cs224n.coref.*;
import cs224n.util.CounterMap;
import cs224n.util.Pair;

public class BetterBaseline implements CoreferenceSystem {
	CounterMap<String, String> heads;

	@Override
	public void train(Collection<Pair<Document, List<Entity>>> trainingData) {
		// TODO Auto-generated method stub
		heads = new CounterMap<String, String>();

		for (Pair<Document, List<Entity>> pair : trainingData) {

			for (Entity entity : pair.getSecond()) {
				for (Pair<Mention, Mention> mentionPair : entity.orderedMentionPairs()) {

					heads.incrementCount(mentionPair.getFirst().headWord(), mentionPair.getSecond().headWord(), 1.0);
				}

			}
		}

	}

	@Override
	public List<ClusteredMention> runCoreference(Document doc) {

		List<ClusteredMention> mentions = new ArrayList<ClusteredMention>();
		Map<String, Entity> clusters = new HashMap<String, Entity>();
		// (for each mention...)
		for (Mention m : doc.getMentions()) {
			// (...get its text)
			String mentionHead = m.headWord();
			// (...if we've seen this text before...)
			if (clusters.containsKey(mentionHead)) {
				// (...add it to the cluster)
				mentions.add(m.markCoreferent(clusters.get(mentionHead)));
			} else {
				// (...else create a new singleton cluster)
				ClusteredMention newCluster = m.markSingleton();
				mentions.add(newCluster);
				clusters.put(mentionHead, newCluster.entity);
			}
		}
		// (return the mentions)
		return mentions;
	}

}
