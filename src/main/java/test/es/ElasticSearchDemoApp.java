package test.es;

/**
 * Created by root on 21/02/16.
 */
import org.elasticsearch.action.get.GetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.es.EsClient;
import test.es.ESLocalNodeClient;
import test.es.Student;
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
        final Student stud = createTestStudent();
        String StudentJson = null;
        try {
           StudentJson = new ObjectMapper().writeValueAsString(stud);
            esClient.getClient().prepareIndex("students", "student","1")
                    .setSource(StudentJson).execute().actionGet();
           GetResponse getResponse = esClient.getClient().prepareGet("students", "student", "1").execute().actionGet();
            Map<String, Object> source = getResponse.getSource();
            System.out.println("------------------------------");
            System.out.println("Index: " + getResponse.getIndex());
            System.out.println("Type: " + getResponse.getType());
            System.out.println("Id: " + getResponse.getId());
            System.out.println("Version: " + getResponse.getVersion());
            System.out.println(source);
            System.out.println("------------------------------");

            LOG.info("student has been indexed.");
        } catch (Exception jpe) {
            LOG.error("student could not be serialized. ", jpe);
        }
        esClient.shutdown();
    }

    private Student createTestStudent() {
        final Student stud = new Student();
        stud.setName("Abhishek");
        stud.setDept("Data warehousing");
        stud.setSection("AB");
        stud.setCollege("test");
        return stud;
    }
}
