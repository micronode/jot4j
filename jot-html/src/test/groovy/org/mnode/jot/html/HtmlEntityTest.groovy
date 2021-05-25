package org.mnode.jot.html


import spock.lang.Specification

import static j2html.TagCreator.div

class HtmlEntityTest extends Specification {

    def 'test entity creation'() {
        given: 'An html container tag'
        def div = div()

        when: 'a name is specified'
        new HtmlEntity(div).name("Example name")

        then: 'the entity is updated'
        div.render() == '<div><h1>Example name</h1></div>'
    }
}
