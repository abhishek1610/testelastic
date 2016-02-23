package test.es.indexer;

/**
 * Created by root on 21/02/16.
 */
public interface Indexer<T> {

    boolean createIndex();

    boolean upgradeIndex();

    boolean indexDocument(T document);

    boolean deleteDocument(T document);

}
