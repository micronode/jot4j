package org.mnode.jot.json


import com.github.jsonj.JsonObject
import org.mnode.jot.schema.AssetType
import org.mnode.jot.schema.Entity
import spock.lang.Specification

class JsonAssetTest extends Specification {

    def 'test entity creation'() {
        given: 'A JSON object instance'
        JsonObject jsonObject = []

        when: 'a name is specified'
        def asset = new JsonAsset(jsonObject).assetType([getProperty: { '123' }] as AssetType)
                .owner([getProperty: { '987' }] as Entity).name("Example asset")

        then: 'the entity is updated'
        jsonObject.get("name").asString() == 'Example asset'

        and: 'string representation is as expected'
        StringWriter writer = []
        asset.writeTo(writer)
        writer as String == '{"assetType":"123","owner":"987","name":"Example asset"}'
    }
}
