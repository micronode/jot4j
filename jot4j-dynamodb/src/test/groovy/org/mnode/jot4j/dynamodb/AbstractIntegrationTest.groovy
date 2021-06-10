package org.mnode.jot4j.dynamodb

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.local.main.ServerRunner
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest
import com.amazonaws.services.dynamodbv2.model.Projection
import com.amazonaws.services.dynamodbv2.model.ProjectionType
import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.ContentBuilder
import net.fortuna.ical4j.model.component.VEvent
import net.fortuna.ical4j.model.property.Attach
import net.fortuna.ical4j.util.Calendars
import org.mnode.jot4j.dynamodb.mapper.CardOrg
import org.mnode.jot4j.dynamodb.mapper.Event
import spock.lang.Shared
import spock.lang.Specification

class AbstractIntegrationTest extends Specification {

    @Shared
    DynamoDBProxyServer dynamoDBProxyServer

    @Shared
    AmazonDynamoDB dynamoDB

    @Shared
    DynamoDBMapper mapper

    @Shared
    Calendar calendar1, calendar2

    @Shared
    VEvent event1, event2

    def setupSpec() {
        System.setProperty("sqlite4java.library.path", "native-libs")
        dynamoDBProxyServer = ServerRunner.createServerFromCommandLineArgs(["-inMemory", "-port", "8000"] as String[])
        dynamoDBProxyServer.start()
        
        dynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration('http://localhost:8000/', 'local'))
                .build()

//        mapper = [dynamoDB, new DynamoDBMapperConfig.Builder()
//                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT).build()]
        mapper = [dynamoDB]

        ContentBuilder builder = []
        event1 = builder.vevent() {
            uid '1'
            summary 'Test Event 1'
            dtstart '20090810', parameters: parameters { value 'DATE' }
            action 'DISPLAY'
            attach new Attach(new File('../LICENSE').bytes)
        }

        event2 = builder.vevent() {
            uid '2'
            summary 'Test Event 2'
            dtstart '20100810', parameters: parameters { value 'DATE' }
        }

        calendar1 = builder.calendar() {
            prodid '-//Ben Fortuna//iCal4j 1.0//EN'
            version '2.0'
            uid '123'
            vevent(event1)
            vevent(event2)
        }

        calendar2 = Calendars.load('src/test/resources/samples/justin.ics')
    }

    def setup() {
        Projection projection = new Projection().withProjectionType(ProjectionType.ALL)

        CreateTableRequest createTableRequest = new CreateTableRequestBuilder().dynamoDb(dynamoDB).typeClass(CardOrg).build()
        createTableRequest.getGlobalSecondaryIndexes().forEach(index -> index.withProjection(projection))
        dynamoDB.createTable(createTableRequest)

        CreateTableRequest createCalTableRequest = new CreateTableRequestBuilder().dynamoDb(dynamoDB).typeClass(Event).build()
        createCalTableRequest.getGlobalSecondaryIndexes().forEach(index -> index.withProjection(projection))
        dynamoDB.createTable(createCalTableRequest)
    }

    def cleanup() {
        DeleteTableRequest deleteCardTableRequest = new DynamoDBMapper(dynamoDB).generateDeleteTableRequest(CardOrg)
        dynamoDB.deleteTable(deleteCardTableRequest)

        DeleteTableRequest deleteCalTableRequest = new DynamoDBMapper(dynamoDB).generateDeleteTableRequest(Event)
        dynamoDB.deleteTable(deleteCalTableRequest)
    }

    def cleanupSpec() {
        dynamoDBProxyServer.stop()
    }
}
