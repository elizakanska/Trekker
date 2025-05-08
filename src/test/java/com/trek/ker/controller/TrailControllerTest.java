package com.trek.ker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trek.ker.entity.Trail;
import com.trek.ker.entity.dto.TrailDto;
import com.trek.ker.mapper.TrailMapper;
import com.trek.ker.service.TrailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static java.util.List.of;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrailController.class)
class TrailControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private TrailService service;

    @MockitoBean
    private TrailMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /api/trails — returns list of TrailDto")
    void getAll_returnsList() throws Exception {
        Trail t1 = new Trail();
        Trail t2 = new Trail();
        TrailDto dto1 = new TrailDto(
                1L,               // id
                "Trail A",        // name
                "Kurzeme",        // location
                "Loop",           // type
                "Mežs",           // biome
                "Easy",           // difficulty
                4.2f,             // length (kilometers)
                "imgA.png"        // image URL
        );
        TrailDto dto2 = new TrailDto(
                2L,
                "Trail B",
                "Gauja",
                "Out-&-Back",
                "Meža pauguri",
                "Moderate",
                7.5f,
                "imgB.png"
        );

        when(service.getAllTrails()).thenReturn(of(t1, t2));
        when(mapper.toDto(t1)).thenReturn(dto1);
        when(mapper.toDto(t2)).thenReturn(dto2);

        mvc.perform(get("/api/trails"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Trail A"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Trail B"));

        verify(service).getAllTrails();
        verify(mapper).toDto(t1);
        verify(mapper).toDto(t2);
    }

    @Test
    @DisplayName("GET /api/trails/{id} — returns single TrailDto")
    void getById_returnsDto() throws Exception {
        long id = 7L;
        Trail entity = new Trail();
        TrailDto dto = new TrailDto(
                id,
                "Trail X",
                "Vidzeme",
                "Point-to-Point",
                "Purvs",
                "Hard",
                10.0f,
                "imgX.png"
        );

        when(service.getTrailById(id)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        mvc.perform(get("/api/trails/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Trail X"))
                .andExpect(jsonPath("$.description").value("Desc X"));

        verify(service).getTrailById(id);
        verify(mapper).toDto(entity);
    }

    @Test
    @DisplayName("POST /api/trails — creates and returns TrailDto")
    void create_returnsCreatedDto() throws Exception {
        TrailDto inDto = new TrailDto(
                null,             // new → no ID yet
                "New Trail",
                "Latgale",
                "Loop",
                "Mežs un pļava",
                "Easy",
                3.3f,
                "new.png"
        );
        Trail entity = new Trail();
        TrailDto outDto = new TrailDto(
                5L,               // assigned by service
                "New Trail",
                "Latgale",
                "Loop",
                "Mežs un pļava",
                "Easy",
                3.3f,
                "new.png"
        );

        when(mapper.toEntity(inDto)).thenReturn(entity);
        when(service.saveTrail(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(outDto);

        mvc.perform(post("/api/trails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value("New Trail"))
                .andExpect(jsonPath("$.description").value("New Desc"));

        verify(mapper).toEntity(inDto);
        verify(service).saveTrail(entity);
        verify(mapper).toDto(entity);
    }

    @Test
    @DisplayName("DELETE /api/trails/{id} — deletes and returns 200")
    void delete_invokesService() throws Exception {
        long id = 9L;

        // no need to stub service.deleteTrail() (void), just verify
        mvc.perform(delete("/api/trails/{id}", id))
                .andExpect(status().isOk());

        verify(service).deleteTrail(id);
    }
}
