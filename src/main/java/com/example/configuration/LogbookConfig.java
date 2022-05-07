package com.example.configuration;

import net.logstash.logback.marker.RawJsonAppendingMarker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.zalando.logbook.*;
import org.zalando.logbook.json.JsonHttpLogFormatter;

import java.io.IOException;

@Configuration
public class LogbookConfig {

    @Bean
    public RequestFilter requestFilter() {
        return RequestFilter.none();
    }

    @Bean
    public Sink sink() {
        return new CustomSink();
    }

    @Component
    private static class CustomSink implements Sink {

        private HttpLogFormatter logFormatter = new JsonHttpLogFormatter();

        private final Logger log = LoggerFactory.getLogger(this.getClass());

        @Override
        public void write(Precorrelation precorrelation, HttpRequest request) throws IOException {
            RawJsonAppendingMarker marker = new RawJsonAppendingMarker("http", logFormatter.format(precorrelation, request));
            String message = request.getMethod() + " " + request.getRequestUri();
            log.info(marker, message);
        }

        @Override
        public void write(Correlation correlation, HttpRequest request, HttpResponse response) {
            //do nothing
        }
    }
}
