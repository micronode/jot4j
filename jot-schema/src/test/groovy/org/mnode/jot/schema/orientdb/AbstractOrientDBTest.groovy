package org.mnode.jot.schema.orientdb


import com.orientechnologies.orient.core.db.OrientDB
import com.orientechnologies.orient.core.db.OrientDBConfig
import com.orientechnologies.orient.core.db.document.ODatabaseDocument
import spock.lang.Shared
import spock.lang.Specification

abstract class AbstractOrientDBTest extends Specification {

    @Shared
    OrientDB orientDb = new OrientDB("remote:localhost","root","rootpwd",
            OrientDBConfig.defaultConfig());

//    @Shared
//    OrientDB orientDb = new OrientDB("memory:test", null)

    ODatabaseDocument session

    def setupSpec() {
//        orientDb.create("test", com.orientechnologies.orient.core.db.ODatabaseType.MEMORY)
//        orientDb.create("test", ODatabaseType.PLOCAL)
    }

    def setup() {
        session = orientDb.open("test","admin","admin")
    }

    def cleanup() {
        session.close()
    }
}
