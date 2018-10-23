package org.jimador.notes.client.web.rest;

import org.jimador.notes.client.ClientApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Test class for the NoteControllerResource REST controller.
 *
 * @see NoteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApp.class)
public class NoteControllerResourceIntTest {

    private MockMvc restMockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        final Source source = null;
        NoteResource noteControllerResource = new NoteResource(source);
        restMockMvc = MockMvcBuilders
            .standaloneSetup(noteControllerResource)
            .build();
    }

    /**
    * Test create
    */
    @Test
    public void testCreate() throws Exception {
        restMockMvc.perform(post("/api/note-controller/create"))
            .andExpect(status().isOk());
    }

}
