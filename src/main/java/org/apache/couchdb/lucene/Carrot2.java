package org.apache.couchdb.lucene;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.store.FSDirectory;
import org.carrot2.clustering.lingo.LingoClusteringAlgorithm;
import org.carrot2.core.CachingController;
import org.carrot2.core.Cluster;
import org.carrot2.core.Document;
import org.carrot2.core.ProcessingComponentConfiguration;
import org.carrot2.core.ProcessingResult;
import org.carrot2.core.attribute.AttributeNames;
import org.carrot2.source.lucene.LuceneDocumentSource;
import org.carrot2.source.lucene.SimpleFieldMapper;
import org.carrot2.util.attribute.AttributeUtils;

public final class Carrot2 {

	public static void main(final String[] args) throws Exception {
		final CachingController controller = new CachingController();
		final Map<String, Object> luceneGlobalAttributes = new HashMap<String, Object>();

		luceneGlobalAttributes.put(AttributeUtils.getKey(LuceneDocumentSource.class, "directory"), FSDirectory
				.getDirectory("/home/rnewson/Source/couchdb-svn/lucene"));
		luceneGlobalAttributes.put(AttributeUtils.getKey(SimpleFieldMapper.class, "titleField"), "source");
		luceneGlobalAttributes.put(AttributeUtils.getKey(SimpleFieldMapper.class, "contentField"), "content");

		controller.init(new HashMap<String, Object>(), new ProcessingComponentConfiguration(LuceneDocumentSource.class,
				"lucene", luceneGlobalAttributes));

		final Map<String, Object> processingAttributes = new HashMap<String, Object>();
		processingAttributes.put(AttributeNames.QUERY, "content:enron");

		ProcessingResult process = controller.process(processingAttributes, "lucene", LingoClusteringAlgorithm.class
				.getName());

		final Collection<Document> documents = process.getDocuments();
		final Collection<Cluster> clusters = process.getClusters();
		final Map<String, Object> attributes = process.getAttributes();

		System.out.println(attributes);

		for (final Cluster cluster : clusters) {
			System.out.println("label: " + cluster.getLabel());
			System.out.println("phrases: " + cluster.getPhrases());
			System.out.print("docs: ");
			for (final Document doc : cluster.getAllDocuments()) {
				System.out.print(doc.getId());
			}
			System.out.println();
		}
	}

	public Carrot2() {
	}

}
