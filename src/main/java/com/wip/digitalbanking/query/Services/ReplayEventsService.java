package com.wip.digitalbanking.query.Services;

import lombok.AllArgsConstructor;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReplayEventsService {

    public EventProcessingConfiguration eventProcessingConfiguration;

    public void replay (){
        String name= "com.wip.digitalbanking.query.Services";
        eventProcessingConfiguration.eventProcessor(name, TrackingEventProcessor.class)
                .ifPresent( p->{
                            p.shutDown();
                            p.resetTokens();
                            p.start();

                        }

                        );
    }
}
