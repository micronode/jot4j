package org.mnode.jot.orientdb

import com.orientechnologies.orient.core.db.OrientDB
import com.orientechnologies.orient.core.db.document.ODatabaseDocument
import org.mnode.jot.orientdb.parser.SchemaParser
import spock.lang.Shared
import spock.lang.Specification

abstract class AbstractOrientDBTest extends Specification {

//    @Shared
//    OrientDB orientDb = new OrientDB("remote:localhost","root","rootpwd",
//            OrientDBConfig.defaultConfig());

    @Shared
    OrientDB orientDb = new OrientDB("memory:test", null)

    ODatabaseDocument session

    def setupSpec() {
        orientDb.create("build/db", com.orientechnologies.orient.core.db.ODatabaseType.MEMORY)
//        orientDb.create("test", ODatabaseType.PLOCAL)
    }

    def setup() {
        session = orientDb.open("build/db","admin","admin")

        OrientDBSchemaController controller = [session]
        def classes = new SchemaParser().parse(
                OrientDBSchemaControllerTest.getResourceAsStream('/schemas/default-schema.yml'))
        controller.updateSchema(classes)
    }

    def cleanup() {
        session.close()
    }
}
