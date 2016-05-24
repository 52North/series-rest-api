/*
 * Copyright (C) 2013-2016 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 as published
 * by the Free Software Foundation.
 *
 * If the program is linked with libraries which are licensed under one of
 * the following licenses, the combination of the program with the linked
 * library is not considered a "derivative work" of the program:
 *
 *     - Apache License, version 2.0
 *     - Apache Software License, version 1.0
 *     - GNU Lesser General Public License, version 3
 *     - Mozilla Public License, versions 1.0, 1.1 and 2.0
 *     - Common Development and Distribution License (CDDL), version 1.0
 *
 * Therefore the distribution of the program linked with libraries licensed
 * under the aforementioned licenses, is permitted by the copyright holders
 * if the distribution is compliant with both the GNU General Public License
 * version 2 and the aforementioned licenses.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */
package org.n52.series.db.da.v1;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.n52.io.request.IoParameters;
import org.n52.io.request.Parameters;
import org.n52.io.request.RequestSimpleParameterSet;
import org.n52.io.response.v1.ext.GeometryCategory;
import org.n52.io.response.v1.ext.GeometryInfo;
import org.n52.io.response.v1.ext.PlatformOutput;
import org.n52.sensorweb.spi.SearchResult;
import org.n52.series.db.da.DataAccessException;
import org.n52.series.db.da.beans.DescribableEntity;
import org.n52.series.db.da.beans.ext.GeometryEntity;
import org.n52.series.db.da.beans.ext.PlatformEntity;

public class GeometriesRepository extends ExtendedSessionAwareRepository implements OutputAssembler<GeometryInfo> {

    @Override
    public List<GeometryInfo> getAllCondensed(DbQuery parameters) throws DataAccessException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GeometryInfo> getAllExpanded(DbQuery parameters) throws DataAccessException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GeometryInfo getInstance(String id, DbQuery parameters) throws DataAccessException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<SearchResult> searchFor(IoParameters parameters) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SearchResult> convertToSearchResults(List<? extends DescribableEntity> found, String locale) {
        // TODO Auto-generated method stub
        return null;
    }

    private GeometryInfo createExpanded(GeometryEntity entity, DbQuery parameters, Session session) throws DataAccessException {
        GeometryInfo result = createCondensed(entity, parameters);
//        result.setGeometry(geometry);
        return result;
    }

    private GeometryInfo createCondensed(GeometryEntity entity, DbQuery parameters) {
        GeometryInfo result = new GeometryInfo(GeometryCategory.PLATFORM_SITE);
//        result.setId(Long.toString(entity.getPkid()));
//        result.setHrefBase(parameters.getHrefBase());
//        result.setPlatform(platform);
        return result;
    }

}