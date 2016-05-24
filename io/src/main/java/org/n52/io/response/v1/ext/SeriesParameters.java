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
package org.n52.io.response.v1.ext;

import org.n52.io.response.CommonSeriesParameters;
import org.n52.io.response.ParameterOutput;
import org.n52.io.response.ServiceOutput;
import org.n52.io.response.v1.PhenomenonOutput;

/**
 * TODO: JavaDoc
 *
 * @author <a href="mailto:h.bredel@52north.org">Henning Bredel</a>
 */
public class SeriesParameters implements CommonSeriesParameters {

    private ParameterOutput phenomenon;

    private ParameterOutput procedure;

    private ParameterOutput category;

    private ParameterOutput offering;

    private ParameterOutput feature;

    private ParameterOutput service;

    private ParameterOutput platform;

    @Override
    public ParameterOutput getPhenomenon() {
        return phenomenon;
    }

    public void setPhenomenon(PhenomenonOutput phenomenon) {
        this.phenomenon = phenomenon;
    }

    @Override
    public ParameterOutput getProcedure() {
        return procedure;
    }

    public void setProcedure(ParameterOutput procedure) {
        this.procedure = procedure;
    }

    @Override
    public ParameterOutput getCategory() {
        return category;
    }

    public void setCategory(ParameterOutput category) {
        this.category = category;
    }

    @Override
    public ParameterOutput getOffering() {
        return offering;
    }

    public void setOffering(ParameterOutput offering) {
        this.offering = offering;
    }

    @Override
    public ParameterOutput getFeature() {
        return feature;
    }

    public void setFeature(ParameterOutput feature) {
        this.feature = feature;
    }

    @Override
    public ParameterOutput getService() {
        return service;
    }

    public void setService(ServiceOutput service) {
        this.service = service;
    }

    @Override
    public ParameterOutput getPlatform() {
        return platform;
    }

    public void setPlatform(ParameterOutput platform) {
        this.platform = platform;
    }

}