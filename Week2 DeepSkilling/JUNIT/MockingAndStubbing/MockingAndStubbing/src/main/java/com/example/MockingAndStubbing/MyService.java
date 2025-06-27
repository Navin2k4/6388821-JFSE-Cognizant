package com.example.MockingAndStubbing;

public class MyService {
    private final ExternalAPI externalAPI;

    public MyService(ExternalAPI api) {
        this.externalAPI = api;
    }

    public String fetchData() {
        return externalAPI.getData();
    }


}
