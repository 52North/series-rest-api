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
package org.n52.io.response;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.n52.io.request.StyleProperties;
import org.n52.io.Utils;
import org.n52.io.response.v1.StationOutput;
import org.n52.io.v1.data.RawFormats;

public abstract class TimeseriesMetadataOutput<T extends CommonSeriesOutput> extends ParameterOutput implements RawFormats {

    private String uom;

    private ReferenceValueOutput[] referenceValues;

    private TimeseriesValue firstValue;

    private TimeseriesValue lastValue;

    private T parameters;

    // TODO add as extra
    @Deprecated
    private StyleProperties renderingHints;

    // TODO add as extra
    @Deprecated
    private StatusInterval[] statusIntervals;

    private Set<String> rawFormats;

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    @Override
    public String[] getRawFormats() {
        if (rawFormats != null) {
            return rawFormats.toArray(new String[0]);
        }
        return null;
    }

    @Override
    public void addRawFormat(String format) {
        if (format != null && !format.isEmpty()) {
            if (rawFormats == null) {
                rawFormats = new HashSet<String>();
            }
            rawFormats.add(format);
        }
    }

    @Override
    public void setRawFormats(Collection<String> formats) {
        if (formats != null && !formats.isEmpty()) {
            if (rawFormats == null) {
                rawFormats = new HashSet<String>();
            } else {
                rawFormats.clear();
            }
            this.rawFormats.addAll(formats);
        }
    }

    public ReferenceValueOutput[] getReferenceValues() {
        return Utils.copy(referenceValues);
    }

    public void setReferenceValues(ReferenceValueOutput[] referenceValues) {
        this.referenceValues = Utils.copy(referenceValues);
    }

    public TimeseriesValue getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(TimeseriesValue firstValue) {
        this.firstValue = firstValue;
    }

    public TimeseriesValue getLastValue() {
        return lastValue;
    }

    public void setLastValue(TimeseriesValue lastValue) {
        this.lastValue = lastValue;
    }

    public T getParameters() {
        return parameters;
    }

    public void setParameters(T seriesOutput) {
        this.parameters = seriesOutput;
    }

    // TODO add as extra
    @Deprecated
    public StyleProperties getRenderingHints() {
        return this.renderingHints;
    }

    // TODO add as extra
    @Deprecated
    public void setRenderingHints(StyleProperties renderingHints) {
        this.renderingHints = renderingHints;
    }

    // TODO add as extra
    @Deprecated
    public StatusInterval[] getStatusIntervals() {
        return statusIntervals;
    }

    // TODO add as extra
    @Deprecated
    public void setStatusIntervals(StatusInterval[] statusIntervals) {
        this.statusIntervals = statusIntervals;
    }

}