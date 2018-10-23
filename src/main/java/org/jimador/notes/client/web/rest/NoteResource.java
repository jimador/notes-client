package org.jimador.notes.client.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * NoteResource controller
 */
@RestController
@RequestMapping("/api/note-controller")
public class NoteResource {

    private final Logger log = LoggerFactory.getLogger(NoteResource.class);

    private final Source source;

    public NoteResource(Source source) {
        this.source = source;
    }

    /**
    * POST create
    */
    @PostMapping("/create")
    public ResponseEntity<Note> create(@RequestBody Note note) {
        log.info("Creating note with name: " + note.getName() + " and message: " + note.getMessage());
        final Message<String> build = MessageBuilder.withPayload(note.getMessage()).build();
        source.output().send(build);
        return ResponseEntity.ok(note);
    }

    static class Note {
        private String name;
        private String message;

        Note(String name, String message) {
            this.name = name;
            this.message = message;
        }

        Note(){}

        public String getMessage() {
            return message;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Note note = (Note) o;
            return getName().equals(note.getName()) &&
                   getMessage().equals(note.getMessage());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName(), getMessage());
        }
    }
}
