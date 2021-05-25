package org.mnode.jot.orientdb

import org.mnode.jot.schema.model.TextNote
import org.mnode.jot.schema.model.User

import java.time.Instant

class OrientDBTextNoteControllerTest extends AbstractOrientDBTest {

    def 'test persistence'() {
        given: 'a textnote controller'
        OrientDBTextNoteController controller = [session]

        when: 'a new textnote is created'
        controller.add(new TextNote(summary: "Test", authors: [new User(name: "User 1")], createdAt: Instant.now()))

        then: 'the textnote is persisted'
        session.query('select * from V').size() == 2
        session.query('select * from TextNote').size() == 1
        session.query('select * from User').size() == 1
        session.query('select * from V').next().getVertex().get().getProperty("summary") == "Test"
    }
}
