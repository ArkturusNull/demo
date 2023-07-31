package com.chatbot.demo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response {
	
    private String description;
    private List<String> options = new ArrayList<String>();
    private List<Boolean> bloquedOptions = new ArrayList<>();
     
    public Response() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

	public void setBloquedOptions(List<Boolean> bloquedOptions) {
		this.bloquedOptions = bloquedOptions;
	}

	public List<Boolean> getBloquedOptions() {
		return bloquedOptions;
	}
	
	
	
}
