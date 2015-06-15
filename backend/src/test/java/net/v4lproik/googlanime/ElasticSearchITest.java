package net.v4lproik.googlanime;

import junit.framework.TestCase;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {
                Config.class
        })
@ImportResource("classpath*  : application-context.xml")
public class ElasticSearchITest extends TestCase {

    @Autowired
    private TransportClient client;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void elasticsearch_getInformation_fromAnimesIndex_shouldBeOk(){
        client.prepareGet("animes", "anime", "1")
                .execute()
                .actionGet();
    }
}