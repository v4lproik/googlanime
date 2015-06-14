package net.v4lproik.googlanime;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Config {

    @Value("${elasticsearch.host}")
    public String ELASTICSEARCH_HOST;

    @Bean(destroyMethod = "close")
    public TransportClient node() {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "elasticsearch").build();

        if (ELASTICSEARCH_HOST == null)
            throw new IllegalArgumentException("ElasticSearch host cannot be found. Check that the active profile provided a file that contains the variable elasticsearch.host ");

        return new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress(ELASTICSEARCH_HOST, 9300));
    }

    @Bean
    public Client client() {
        return node();
    }

}