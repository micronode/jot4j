package org.mnode.jot.json;

import com.github.jsonj.JsonObject;
import org.mnode.jot.schema.ExternalLink;
import org.mnode.jot.schema.Project;
import org.mnode.jot.schema.Task;
import org.mnode.jot.schema.TaskType;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class JsonTask extends JsonJot implements Task {

    public JsonTask(JsonObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public Task taskType(TaskType taskType) {
        jsonObject.put("taskType", (String) taskType.getProperty(Property.Uid));
        return this;
    }

    @Override
    public Task project(Project project) {
        jsonObject.put("project", (String) project.getProperty(Property.Uid));
        return this;
    }

    @Override
    public Task dueDate(ZonedDateTime dueDate) {
        jsonObject.put("dueDate", DateTimeFormatter.ISO_ZONED_DATE_TIME.format(dueDate));
        return this;
    }

    @Override
    public Task status(CompletionStatus completionStatus) {
        jsonObject.put("status", completionStatus.toString());
        return this;
    }

    @Override
    public Task attachments(ExternalLink... attachments) {
        jsonObject.put("attachments", Arrays.stream(attachments).map(a -> a.getProperty(Property.Url)));
        return this;
    }
}
