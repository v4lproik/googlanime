package net.v4lproik.googlanime;

import junit.framework.TestCase;
import net.v4lproik.googlanime.client.elasticsearch.ElasticsearchIndexInitializer;
import net.v4lproik.googlanime.client.elasticsearch.ElasticsearchTestConfiguration;
import org.elasticsearch.client.Client;
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
                ElasticsearchTestConfiguration.class
        })
@ImportResource("classpath*  : application-context.xml")
public class ElasticSearchITest extends TestCase {

    @Autowired
    private ElasticsearchIndexInitializer elasticsearchIndexInitializer;

    @Autowired
    private Client client;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        elasticsearchIndexInitializer.createIndex();
    }



    @Test
    public void elasticsearch_getInformation_fromAnimesIndex_shouldBeOk(){
        try {
            client.prepareGet("animes", "anime", "1")
                    .execute()
                    .actionGet();
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }

    }
}