package test.es;

/**
 * Created by root on 21/02/16.
 */
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;

/**
 * Wrapper around an ElasticSearch {@link TransportClient} node.
 *
 * @author hugovalk
 *
 */
public class EStransportclient implements EsClient {

    private TransportClient client;

    /**
     * Default constructor, initializing the transport client.
     */
    public EStransportclient() {
        final Settings settings = ImmutableSettings.settingsBuilder()
                .put("client.transport.sniff", true)
               // .put("transport.type", "org.elasticsearch.transport.netty.FoundNettyTransport")
                .put("cluster.name", "mycluster1").build();
    try{    client = new TransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(
                      "192.168.146.133" , 9300));}
    catch (Exception e)
    {}
    }

    /** {@inheritDoc} */
   //Override
    public Client getClient() {
        return client;
    }

    /** {@inheritDoc} */
    // Override
    public void shutdown() {
        client.close();
        client = null;
    }

}
