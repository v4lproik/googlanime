package net.v4lproik.googlanime;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by joel on 06/05/2015.
 */
@Configuration
public class Config {

    @Bean(destroyMethod = "close")
    public TransportClient node() {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "elasticsearch").build();
        return new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("sandbox", 9300));
    }

    @Bean
    public Client client() {
        return node();
    }

}