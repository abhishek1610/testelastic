package test.es;

/**
 * Created by root on 21/02/16.
 */
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

/**
 * Wrapper around an ElasticSearch {@link Node}, to obtain a {@link Client}
 * from.
 *
 * @author hugovalk
 *
 */
public class ESLocalNodeClient implements EsClient {

    private Node node;

    /**
     * Default constructor, initializing the client node.
     */
    public ESLocalNodeClient() {
        final Settings settings = ImmutableSettings.settingsBuilder()
                .put("node.name", "test1").build();

        node = new NodeBuilder().settings(settings)
                .clusterName("mycluster1")
                        // .local(true)
                //.client(true)
                .build().start();
    }

    /** {@inheritDoc} */
   // @Override
    public Client getClient() {
        return node.client();
    }

    /** {@inheritDoc} */
    //@Override
    public void shutdown() {
        getClient().close();
        node.close();
        node = null;
    }

}
