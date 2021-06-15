package org.mnode.jot4j.dynamodb

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig
import com.amazonaws.services.dynamodbv2.local.main.ServerRunner
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest
import com.amazonaws.services.dynamodbv2.model.Projection
import com.amazonaws.services.dynamodbv2.model.ProjectionType
import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.ContentBuilder
import net.fortuna.ical4j.model.component.VAvailability
import net.fortuna.ical4j.model.component.VEvent
import net.fortuna.ical4j.model.component.VJournal
import net.fortuna.ical4j.model.component.VToDo
import net.fortuna.ical4j.model.property.Attach
import net.fortuna.ical4j.util.Calendars
import net.fortuna.ical4j.vcard.VCard
import org.mnode.jot4j.dynamodb.mapper.CardOrg
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

    @Shared
    VToDo todo1, todo2

    @Shared
    VJournal journal1, journal2

    @Shared
    VAvailability availability1, availability2

    @Shared
    VCard card1, card2, group1, group2

    @Shared
    String tableName = 'JOT_TEST'

    def setupSpec() {
        System.setProperty("sqlite4java.library.path", "native-libs")
        dynamoDBProxyServer = ServerRunner.createServerFromCommandLineArgs(["-inMemory", "-port", "8000"] as String[])
        dynamoDBProxyServer.start()
        
        dynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration('http://localhost:8000/', 'local'))
                .build()

//        mapper = [dynamoDB, new DynamoDBMapperConfig.Builder()
//                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT).build()]
        mapper = [dynamoDB, new DynamoDBMapperConfig.Builder()
                .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(tableName))
                .build()]

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
            organizer 'johnd@example.com', parameters: parameters { cn 'John Doe' }
            attendee 'aliced@example.com'
            valarm {
                uid '22'
            }
        }

        todo1 = builder.vtodo() {
            summary 'Test Task 1'
        }

        todo2 = builder.vtodo() {
            summary 'Test Task 2'
        }

        journal1 = builder.vjournal() {
            summary 'Test Journal 1'
        }

        journal2 = builder.vjournal() {
            summary 'Test Journal 2'
        }

        availability1 = builder.vavailability() {
            uid '1'
            summary 'Test Availability 1'
            available {
                uid '11'
            }
        }

        availability2 = builder.vavailability() {
            uid '2'
            summary 'Test Availability 2'
            available {
                uid '22'
            }
        }

        calendar1 = builder.calendar() {
            prodid '-//Ben Fortuna//iCal4j 1.0//EN'
            version '2.0'
            uid '123'
            vevent(event1)
            vevent(event2)
        }

        calendar2 = Calendars.load('src/test/resources/samples/justin.ics')

        card1 = new net.fortuna.ical4j.vcard.ContentBuilder().vcard {
            fn 'Test Card 1'
        }

        card2 = new net.fortuna.ical4j.vcard.ContentBuilder().vcard {
            fn 'Test Card 2'
        }

        group1 = new net.fortuna.ical4j.vcard.ContentBuilder().vcard {
            fn 'Test Group 1'
            member '1234-1234-1234-1234'
            member '9999-9999-9999-9999'
        }

        group2 = new net.fortuna.ical4j.vcard.ContentBuilder().vcard {
            fn 'Test Group 2'
            member 'johnd@example.com'
            member 'alexd@example.com'
            org 'Amazon Inc.'
        }
    }

    def setup() {
        Projection projection = new Projection().withProjectionType(ProjectionType.ALL)

        CreateTableRequest createTableRequest = new CreateTableRequestBuilder()
                .dynamoDb(dynamoDB).typeClass(CardOrg).tableName(tableName).build()
        createTableRequest.getGlobalSecondaryIndexes().forEach(index -> index.withProjection(projection))
        dynamoDB.createTable(createTableRequest)

//        CreateTableRequest createCalTableRequest = new CreateTableRequestBuilder().dynamoDb(dynamoDB).typeClass(Event).build()
//        createCalTableRequest.getGlobalSecondaryIndexes().forEach(index -> index.withProjection(projection))
//        dynamoDB.createTable(createCalTableRequest)
    }

    def cleanup() {
        DeleteTableRequest deleteTableRequest = new DynamoDBMapper(dynamoDB)
                .generateDeleteTableRequest(CardOrg).withTableName(tableName)
        dynamoDB.deleteTable(deleteTableRequest)

//        DeleteTableRequest deleteCalTableRequest = new DynamoDBMapper(dynamoDB).generateDeleteTableRequest(Event)
//        dynamoDB.deleteTable(deleteCalTableRequest)
    }

    def cleanupSpec() {
        dynamoDBProxyServer.stop()
    }

    def assertTableItemCount(long expectedCount) {
        dynamoDB.describeTable(tableName).getTable().getItemCount() == expectedCount
    }
}
