package com.insurer.config;

import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        ClientHttpResponse response = execution.execute(request, body);

        log(request,body,response);

        return response;
    }

    private void log(HttpRequest request, byte[] body, ClientHttpResponse response) throws IOException {
        //do logging
    	
    	// final ClientHttpResponse responseCopy = new BufferingClientHttpResponseWrapper(response);
         logger.debug("Method: ", request.getMethod().toString());
         logger.debug("URI: ",  request.getURI().toString());
         logger.debug("Request Body: " + new String(body));
         logger.debug("Response body: " + response.getBody());
         
    }
}