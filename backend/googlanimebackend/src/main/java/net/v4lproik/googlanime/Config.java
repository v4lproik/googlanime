package net.v4lproik.googlanime;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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