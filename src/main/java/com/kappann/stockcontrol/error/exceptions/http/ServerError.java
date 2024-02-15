package com.kappann.stockcontrol.error.exceptions.http;
import org.springframework.web.client.RestClientException;
public class ServerError extends RestClientException {
    public ServerError(String msg) {
        super(msg);
    }
}