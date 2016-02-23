package test.es;

/**
 * Created by root on 21/02/16.
 */
import org.elasticsearch.action.get.GetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.es.EsClient;
import test.es.ESLocalNodeClient;
import test.es.Vacancy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

/**
 * Elasticsearch demo application.
 */
public class ElasticSearchDemoApp {

    private static final Logger LOG = LoggerFactory
            .getLogger(ElasticSearchDemoApp.class);

    public static void main(String[] args) {
        new ElasticSearchDemoApp().indexDocument();
    }

    private final EsClient esClient = new EStransportclient();

    private void indexDocument() {
        final Vacancy vacancy = createTestVacancy();
        String vacancyJson = null;
        try {
         /*  vacancyJson = new ObjectMapper().writeValueAsString(vacancy);
            esClient.getClient().prepareIndex("vacancies", "vacancy")
                    .setSource(vacancyJson).execute().actionGet();*/
           GetResponse getResponse = esClient.getClient().prepareGet("tutorial", "helloworld", "1").execute().actionGet();
            Map<String, Object> source = getResponse.getSource();
            System.out.println("------------------------------");
            System.out.println("Index: " + getResponse.getIndex());
            System.out.println("Type: " + getResponse.getType());
            System.out.println("Id: " + getResponse.getId());
            System.out.println("Version: " + getResponse.getVersion());
            System.out.println(source);
            System.out.println("------------------------------");

            LOG.info("Vacancy has been indexed.");
        } catch (Exception jpe) {
            LOG.error("Vacancy could not be serialized. ", jpe);
        }
        esClient.shutdown();
    }

    private Vacancy createTestVacancy() {
        final Vacancy vacancy = new Vacancy();
        vacancy.setTitle("Software developer");
        vacancy.setDescription("A well-known company is looking for an experienced Java developer.");
        return vacancy;
    }
}
