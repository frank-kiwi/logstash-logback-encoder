/*
 * Copyright 2013-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.logstash.logback.composite;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import ch.qos.logback.core.spi.DeferredProcessingAware;
import com.fasterxml.jackson.core.JsonGenerator;

/**
 * Outputs an incrementing sequence number.
 * Useful for determining if log events get lost along the transport chain.
 */
public class SequenceJsonProvider<Event extends DeferredProcessingAware> extends AbstractFieldJsonProvider<Event> {

    public static final String FIELD_SEQUENCE = "sequence";

    private final AtomicLong sequenceNumber = new AtomicLong(0L);

    public SequenceJsonProvider() {
        setFieldName(FIELD_SEQUENCE);
    }

    @Override
    public void writeTo(JsonGenerator generator, Event iLoggingEvent) throws IOException {
        JsonWritingUtils.writeNumberField(generator, getFieldName(), sequenceNumber.incrementAndGet());
    }

}
