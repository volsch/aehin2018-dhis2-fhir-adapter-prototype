package org.dhis2.fhir.adapter.fhir.transform.dhis;

/*
 * Copyright (c) 2004-2018, University of Oslo
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

import org.dhis2.fhir.adapter.fhir.metadata.model.RemoteSubscriptionResource;
import org.hl7.fhir.instance.model.api.IBaseResource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;

/**
 * The outcome of the transformation between a DHIS 2 resource and a FHIR resource.
 *
 * @param <R> the concrete type of the returned FHIR resource.
 * @author volsch
 */
public class DhisToFhirTransformOutcome<R extends IBaseResource> implements Serializable
{
    private static final long serialVersionUID = -1022009827965716982L;

    private final RemoteSubscriptionResource subscriptionResource;

    private final R resource;

    private final DhisToFhirTransformerRequest nextTransformerRequest;

    public DhisToFhirTransformOutcome( @Nonnull RemoteSubscriptionResource subscriptionResource, @Nonnull R resource )
    {
        this.subscriptionResource = subscriptionResource;
        this.resource = resource;
        this.nextTransformerRequest = null;
    }

    public DhisToFhirTransformOutcome( @Nonnull DhisToFhirTransformOutcome<R> outcome, @Nullable DhisToFhirTransformerRequest nextTransformerRequest )
    {
        this.subscriptionResource = outcome.getSubscriptionResource();
        this.resource = outcome.getResource();
        this.nextTransformerRequest = nextTransformerRequest;
    }

    @Nonnull
    public RemoteSubscriptionResource getSubscriptionResource()
    {
        return subscriptionResource;
    }

    @Nonnull
    public R getResource()
    {
        return resource;
    }


    @Nullable
    public DhisToFhirTransformerRequest getNextTransformerRequest()
    {
        return nextTransformerRequest;
    }
}