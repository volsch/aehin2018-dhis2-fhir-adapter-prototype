package org.dhis2.fhir.adapter.setup;

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

import javax.validation.Valid;
import java.io.Serializable;

/**
 * The setup of the remote subscription.
 *
 * @author volsch
 */
public class RemoteSubscriptionSetup implements Serializable
{
    private static final long serialVersionUID = 7653552278753840057L;

    @Valid
    private RemoteSubscriptionDhisSetup dhisSetup = new RemoteSubscriptionDhisSetup();

    @Valid
    private RemoteSubscriptionAdapterSetup adapterSetup = new RemoteSubscriptionAdapterSetup();

    @Valid
    private RemoteSubscriptionFhirSetup fhirSetup = new RemoteSubscriptionFhirSetup();

    @Valid
    private SystemUriSetup systemUriSetup = new SystemUriSetup();

    public RemoteSubscriptionDhisSetup getDhisSetup()
    {
        return dhisSetup;
    }

    public void setDhisSetup( RemoteSubscriptionDhisSetup dhisSetup )
    {
        this.dhisSetup = dhisSetup;
    }

    public RemoteSubscriptionAdapterSetup getAdapterSetup()
    {
        return adapterSetup;
    }

    public void setAdapterSetup( RemoteSubscriptionAdapterSetup adapterSetup )
    {
        this.adapterSetup = adapterSetup;
    }

    public RemoteSubscriptionFhirSetup getFhirSetup()
    {
        return fhirSetup;
    }

    public void setFhirSetup( RemoteSubscriptionFhirSetup fhirSetup )
    {
        this.fhirSetup = fhirSetup;
    }

    public SystemUriSetup getSystemUriSetup()
    {
        return systemUriSetup;
    }

    public void setSystemUriSetup( SystemUriSetup systemUriSetup )
    {
        this.systemUriSetup = systemUriSetup;
    }
}