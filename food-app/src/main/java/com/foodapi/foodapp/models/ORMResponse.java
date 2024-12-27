package com.foodapi.foodapp.models;

import com.foodapi.foodapp.Util.ResponseEnums;

public class ORMResponse {
    private String result;
    private ResponseEnums status;
    private String message;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

   

    public ResponseEnums getStatus() {
		return status;
	}

	public void setStatus(ResponseEnums status) {
		this.status = status;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
