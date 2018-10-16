package org.dhis2.fhir.adapter.fhir.metadata.repository;

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

import org.dhis2.fhir.adapter.fhir.metadata.model.FhirResourceType;
import org.dhis2.fhir.adapter.fhir.metadata.model.RemoteSubscription;
import org.dhis2.fhir.adapter.fhir.metadata.model.RemoteSubscriptionResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for {@link RemoteSubscriptionResource} entities.
 *
 * @author volsch
 */
public interface RemoteSubscriptionResourceRepository extends JpaRepository<RemoteSubscriptionResource, UUID>
{
    @Query( "SELECT r FROM #{#entityName} r JOIN FETCH r.remoteSubscription s WHERE r.id=:resourceId" )
    @Nonnull
    Optional<RemoteSubscriptionResource> findOneForWebHookEvaluation( @Param( "resourceId" ) @Nonnull UUID resourceId );

    @Query( "SELECT r FROM #{#entityName} r JOIN FETCH r.remoteSubscription s LEFT JOIN FETCH s.remoteHeaders WHERE r.id=:resourceId" )
    @Nonnull
    Optional<RemoteSubscriptionResource> findOneForSubscriptionProcessing( @Param( "resourceId" ) @Nonnull UUID resourceId );

    @Query( "SELECT r FROM #{#entityName} r JOIN FETCH r.remoteSubscription s WHERE s=:subscription AND r.fhirResourceType=:fhirResourceType" )
    @Nonnull
    Collection<RemoteSubscriptionResource> findForWebHookEvaluation(
        @Param( "subscription" ) @Nonnull RemoteSubscription remoteSubscription, @Param( "fhirResourceType" ) @Nonnull FhirResourceType fhirResourceType );
}