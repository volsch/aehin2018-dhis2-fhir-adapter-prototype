package org.dhis2.fhir.adapter.fhir.transform.dhis.impl.program;

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

import ca.uhn.fhir.rest.param.DateRangeParam;
import org.dhis2.fhir.adapter.dhis.tracker.program.ProgramStageId;
import org.dhis2.fhir.adapter.fhir.metadata.model.ProgramStageRule;
import org.dhis2.fhir.adapter.fhir.metadata.model.RuleInfo;
import org.dhis2.fhir.adapter.fhir.model.FhirVersion;
import org.dhis2.fhir.adapter.fhir.transform.dhis.PreparedDhisToFhirSearch;
import org.dhis2.fhir.adapter.fhir.transform.dhis.impl.AbstractPreparedDhisToFhirSearch;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link PreparedDhisToFhirSearch} for program stages.
 *
 * @author volsch
 */
public class PreparedProgramStageToFhirSearch extends AbstractPreparedDhisToFhirSearch<ProgramStageRule>
{
    private final List<ProgramStageId> programStageIds;

    public PreparedProgramStageToFhirSearch( @Nonnull FhirVersion fhirVersion, @Nonnull List<RuleInfo<ProgramStageRule>> ruleInfos, @Nullable Map<String, List<String>> filter, @Nullable DateRangeParam lastUpdatedDateRange, int count,
        @Nonnull List<ProgramStageId> programStageIds )
    {
        super( fhirVersion, ruleInfos, filter, lastUpdatedDateRange, count );
        this.programStageIds = programStageIds;
    }

    @Nullable
    public ProgramStageId getNextProgramStageId( @Nullable ProgramStageId previousProgramStageId )
    {
        if ( previousProgramStageId == null )
        {
            return programStageIds.get( 0 );
        }
        final int index = programStageIds.indexOf( previousProgramStageId );
        if ( (index + 1) >= programStageIds.size() )
        {
            return null;
        }
        return programStageIds.get( index + 1 );
    }
}
