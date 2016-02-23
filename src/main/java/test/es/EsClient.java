package test.es;

/**
 * Created by root on 21/02
 *
 * te*/


import org.elasticsearch.client.Client;

/**
 * Wrapper around an ElasticSearch {@link Client}. Different ways of obtaining a
 * {@link Client} can be implemented in classes implementing this interface.
 *
 * @author hugovalk
 *
 */
public interface EsClient {

    /**
     * Get a reference to an ElasticSearch {@link Client}.
     *
     * @return a {@link Client}.
     */
    Client getClient();

    /**
     * Shutdown the ElasticSearch {@link Client}. The client is not available
     * for querying and indexing.
     */
    void shutdown();
}