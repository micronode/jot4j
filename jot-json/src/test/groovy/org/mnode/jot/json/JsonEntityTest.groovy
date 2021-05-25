package org.mnode.jot.json


import com.github.jsonj.JsonObject
import spock.lang.Specification

class JsonEntityTest extends Specification {

    def 'test entity creation'() {
        given: 'A JSON object instance'
        JsonObject jsonObject = []

        when: 'a name is specified'
        def entity = new JsonEntity(jsonObject).name("Example name")

        then: 'the entity is updated'
        jsonObject.get("name").asString() == 'Example name'

        and: 'string representation is as expected'
        StringWriter writer = []
        entity.writeTo(writer)
        writer as String == '{"name":"Example name"}'
    }
}
