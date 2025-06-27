package com.example.MockingAndStubbing;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MyServiceTest {

    @Test
    public void testExternalAPI_withStubbedReturnValue() {
        ExternalAPI mockApi = Mockito.mock(ExternalAPI.class);
        when(mockApi.getData()).thenReturn("Mock Data");
        MyService service = new MyService(mockApi);
        String result = service.fetchData();
        assertEquals(result, "Mock Data");
    }

    @Test
    public void testExternalAPI_verifyInteraction() {
        ExternalAPI mockApi = Mockito.mock(ExternalAPI.class);
        MyService service = new MyService(mockApi);
        service.fetchData();
        verify(mockApi).getData();
    }
}