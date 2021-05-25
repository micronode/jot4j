package org.mnode.jot.orientdb.mapper;

import com.orientechnologies.orient.core.record.OVertex;
import org.mnode.jot.schema.TextNote;
import org.mnode.jot.schema.command.PropertyName;

import static org.mnode.jot.schema.PropertyAccessor.Property.Note;

public class TextNoteMapper extends AbstractJotMapper<TextNote> {

    @Override
    public void map(TextNote from, OVertex to) {
        super.map(from, to);
        to.setProperty(PropertyName.Note.getValue(), from.getProperty(Note));
    }
}
