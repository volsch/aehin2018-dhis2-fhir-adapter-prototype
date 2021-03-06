package org.dhis2.fhir.adapter.cache;

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

import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Duration;
import java.util.UUID;

/**
 * Simple cache configuration of a Redis cache.
 *
 * @author volsch
 */
@Validated
public class SimpleRedisCacheConfig implements Serializable
{
    private static final long serialVersionUID = 7668397012800515816L;

    @NotNull
    private Duration timeToLive = Duration.ZERO;

    @NotNull
    private String keyPrefix = UUID.randomUUID().toString();

    @Nonnull
    public Duration getTimeToLive()
    {
        return timeToLive;
    }

    public void setTimeToLive( @Nonnull Duration timeToLive )
    {
        this.timeToLive = timeToLive;
    }

    @Nonnull
    public CacheKeyPrefix getCacheKeyPrefix()
    {
        return new CacheKeyPrefix()
        {
            @Override
            @Nonnull
            public String compute( @Nonnull String cacheName )
            {
                return keyPrefix + ":" + cacheName + ":";
            }
        };
    }

    @Nonnull
    public String getKeyPrefix()
    {
        return keyPrefix;
    }

    public void setKeyPrefix( @Nonnull String keyPrefix )
    {
        this.keyPrefix = keyPrefix;
    }

    public void setKeyPrefixString( @Nonnull String keyPrefix )
    {
        this.keyPrefix = keyPrefix;
    }
}
