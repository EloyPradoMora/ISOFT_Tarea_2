package com.example.demo;

import com.example.demo.services.ActionService;
import com.example.demo.services.ComedyService;
import com.example.demo.services.DramaService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuIntegrationTest {
    @Mock
    private ComedyService comedyService;
    @Mock
    private ActionService actionService;
    @Mock
    private DramaService dramaService;
    private InputStream systemInOriginal;

    @BeforeEach
    void setUp() {
        systemInOriginal = System.in;
    }
    @AfterEach
    void tearDown() {
        System.setIn(systemInOriginal);
    }
    @Test
    void testIntegrationMenuComedia() throws Exception {
        String simulatedInput = "1\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        when(comedyService.getComedyShowsList(anyInt())).thenReturn(Collections.emptyList());
        when(comedyService.getTopRankingList(anyInt())).thenReturn(Collections.emptyList());
        when(comedyService.getRecientComedyList(anyInt())).thenReturn(Collections.emptyList());
        Menu menu = new Menu(comedyService, actionService, dramaService);
        menu.run();

        verify(comedyService, times(1)).getComedyShowsList(anyInt());
        verify(comedyService, times(1)).getTopRankingList(anyInt());
        verify(comedyService, times(1)).getRecientComedyList(anyInt());

        verifyNoInteractions(actionService);
        verifyNoInteractions(dramaService);
    }

    @Test
    void testIntegrationMenuAccion() throws Exception {
        String simulatedInput = "2\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        when(actionService.get20EnglishActionShows()).thenReturn(Collections.emptyList());
        when(actionService.getTop5BestEvaluatedActionShows()).thenReturn(Collections.emptyList());
        when(actionService.getActionShowsFrom2014()).thenReturn(Collections.emptyList());
        Menu menu = new Menu(comedyService, actionService, dramaService);
        menu.run();

        verify(actionService, times(1)).get20EnglishActionShows();
        verify(actionService, times(1)).getTop5BestEvaluatedActionShows();
        verify(actionService, times(1)).getActionShowsFrom2014();

        verifyNoInteractions(comedyService);
        verifyNoInteractions(dramaService);
    }

    @Test
    void testIntegrationMenuDrama() throws Exception {
        String entradaSimulada = "3\n0\n";
        System.setIn(new ByteArrayInputStream(entradaSimulada.getBytes()));

        when(dramaService.get20EnglishDramaShows()).thenReturn(Collections.emptyList());
        when(dramaService.getTop5BestEvaluatedDramaShows()).thenReturn(Collections.emptyList());
        when(dramaService.getDramaShowsPriorTo2014()).thenReturn(Collections.emptyList());
        Menu menu = new Menu(comedyService, actionService, dramaService);
        menu.run();

        verify(dramaService, times(1)).get20EnglishDramaShows();
        verify(dramaService, times(1)).getTop5BestEvaluatedDramaShows();
        verify(dramaService, times(1)).getDramaShowsPriorTo2014();

        verifyNoInteractions(comedyService);
        verifyNoInteractions(actionService);
    }

    @Test
    void testInvalidOptions() throws Exception {
        String simulatedInput = "9\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Menu menu = new Menu(comedyService, actionService, dramaService);
        menu.run();

        verifyNoInteractions(actionService);
        verifyNoInteractions(dramaService);
        verifyNoInteractions(comedyService);
    }
}
