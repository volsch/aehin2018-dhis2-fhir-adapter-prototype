package org.dhis2.fhir.adapter.fhir.metadata.controller;

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

import org.dhis2.fhir.adapter.AbstractAppTest;
import org.dhis2.fhir.adapter.fhir.model.FhirVersion;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests export of metadata.
 *
 * @author volsch
 */
public class MetadataExportControllerAppTest extends AbstractAppTest
{
    @Nonnull
    @Override
    protected FhirVersion getFhirVersion()
    {
        return FhirVersion.DSTU3;
    }

    @Test
    public void invokeAuthorized() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders.get( "/api/metadata" )
            .header( "Authorization", "Basic " + Base64.getEncoder().encodeToString( "all:all_1"
                .getBytes( StandardCharsets.UTF_8 ) ) ) ).andExpect( status().isOk() )
            .andExpect( content().string( Matchers.not( Matchers.containsString( "\"FHIR_PATIENT\"" ) ) ) )
            .andExpect( content().string( Matchers.not( Matchers.containsString( "\"trackedEntities\"" ) ) ) )
            .andExpect( content().string( Matchers.not( Matchers.containsString( "\"trackedEntityRules\"" ) ) ) )
            .andExpect( content().string( Matchers.not( Matchers.containsString( "\"fhirResourceMappings\"" ) ) ) );
    }

    @Test
    public void invokeAuthorizedIncludeResourceMappings() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders.get( "/api/metadata" ).param( "includeResourceMappings", "true" )
            .header( "Authorization", "Basic " + Base64.getEncoder().encodeToString( "all:all_1"
                .getBytes( StandardCharsets.UTF_8 ) ) ) ).andExpect( status().isOk() )
            .andExpect( content().string( Matchers.not( Matchers.containsString( "\"FHIR_PATIENT\"" ) ) ) )
            .andExpect( content().string( Matchers.not( Matchers.containsString( "\"trackedEntities\"" ) ) ) )
            .andExpect( content().string( Matchers.not( Matchers.containsString( "\"trackedEntityRules\"" ) ) ) )
            .andExpect( content().string( Matchers.containsString( "\"fhirResourceMappings\"" ) ) );
    }

    @Test
    public void invokeAuthorizedIncludeTrackedEntities() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders.get( "/api/metadata" ).param( "includeTrackedEntities", "true" )
            .header( "Authorization", "Basic " + Base64.getEncoder().encodeToString( "all:all_1"
                .getBytes( StandardCharsets.UTF_8 ) ) ) ).andExpect( status().isOk() )
            .andExpect( content().string( Matchers.containsString( "\"FHIR_PATIENT\"" ) ) )
            .andExpect( content().string( Matchers.containsString( "\"trackedEntities\"" ) ) )
            .andExpect( content().string( Matchers.containsString( "\"trackedEntityRules\"" ) ) )
            .andExpect( content().string( Matchers.not( Matchers.containsString( "\"fhirResourceMappings\"" ) ) ) );
    }

    @Test
    public void invokeUnauthorized() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders.get( "/api/metadata" )
            .header( "Authorization", "Basic " + Base64.getEncoder().encodeToString( "code_mapping:code_mapping_1"
                .getBytes( StandardCharsets.UTF_8 ) ) ) ).andExpect( status().isForbidden() );
    }
}
