package org.dhis2.fhir.adapter.metadata.sheet.processor;

/*
 * Copyright (c) 2004-2019, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import org.dhis2.fhir.adapter.metadata.sheet.model.MetadataSheetMessage;
import org.dhis2.fhir.adapter.metadata.sheet.model.MetadataSheetMessageCollector;
import org.dhis2.fhir.adapter.metadata.sheet.model.MetadataSheetMessageSeverity;

import javax.annotation.Nonnull;

/**
 * Thrown if an error occurs while importing the metadata sheet and import cannot
 * be continued.<br>
 *
 * <b>This metadata sheet import tool is just a temporary solution
 * and may be removed in the future completely.</b>
 *
 * @author volsch
 */
public class MetadataSheetImportException extends RuntimeException
{
    private static final long serialVersionUID = -4839457854832396547L;

    private final MetadataSheetMessageCollector messageCollector;

    public MetadataSheetImportException( @Nonnull String message )
    {
        this.messageCollector = new MetadataSheetMessageCollector();
        this.messageCollector.addMessage( new MetadataSheetMessage( MetadataSheetMessageSeverity.FATAL, message ) );
    }

    public MetadataSheetImportException( @Nonnull MetadataSheetMessageCollector messageCollector )
    {
        this.messageCollector = messageCollector;
    }

    @Nonnull
    public MetadataSheetMessageCollector getMessageCollector()
    {
        return messageCollector;
    }
}
