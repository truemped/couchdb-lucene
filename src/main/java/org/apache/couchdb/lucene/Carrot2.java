package org.apache.couchdb.lucene;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.FSDirectory;
import org.carrot2.core.Document;
import org.carrot2.core.IController;
import org.carrot2.core.ProcessingResult;
import org.carrot2.core.SimpleController;
import org.carrot2.core.attribute.AttributeNames;
import org.carrot2.source.lucene.IFieldMapper;
import org.carrot2.source.lucene.LuceneDocumentSource;
import org.carrot2.source.lucene.SimpleFieldMapper;

public final class Carrot2 {

	public static void main(final String[] args) throws Exception {
		final IController controller = new SimpleController();
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("LuceneDocumentSource.directory", FSDirectory
				.getDirectory("/home/rnewson/Source/couchdb-svn/lucene"));
		final IFieldMapper fieldMapper = new SimpleFieldMapper();
		attributes.put("LuceneDocumentSource.fieldMapper", fieldMapper);
		controller.init(attributes);

		final Map<String, Object> processingAttributes = new HashMap<String, Object>();
		processingAttributes.put(AttributeNames.QUERY, new TermQuery(new Term(Config.DB, "enron")));
		processingAttributes.put(AttributeNames.RESULTS, 5);

		final ProcessingResult result = controller.process(processingAttributes, LuceneDocumentSource.class);

		final Collection<Document> documents = result.getDocuments();
		for (final Document document : documents) {
			System.out.println(document.getFields());
		}
	}

	public Carrot2() {
	}

}
