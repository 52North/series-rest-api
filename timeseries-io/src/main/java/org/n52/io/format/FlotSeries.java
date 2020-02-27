/**
 * Copyright (C) 2013-2020 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 as publishedby the Free
 * Software Foundation.
 *
 * If the program is linked with libraries which are licensed under one of the
 * following licenses, the combination of the program with the linked library is
 * not considered a "derivative work" of the program:
 *
 *     - Apache License, version 2.0
 *     - Apache Software License, version 1.0
 *     - GNU Lesser General Public License, version 3
 *     - Mozilla Public License, versions 1.0, 1.1 and 2.0
 *     - Common Development and Distribution License (CDDL), version 1.0
 *
 * Therefore the distribution of the program linked with libraries licensed under
 * the aforementioned licenses, is permitted by the copyright holders if the
 * distribution is compliant with both the GNU General Public License version 2
 * and the aforementioned licenses.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 */
package org.n52.io.format;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class FlotSeries {

    private List<Number[]> values;

    private Map<String, FlotSeries> referenceValues;

    private Number[] valueBeforeTimespan;

    private Number[] valueAfterTimespan;

    public FlotSeries() {
        referenceValues = new HashMap<>();
    }

    public List<Number[]> getValues() {
        return values;
    }

    public void setValues(List<Number[]> values) {
        this.values = values;
    }

    @JsonInclude(Include.NON_EMPTY)
    public Map<String, FlotSeries> getReferenceValues() {
        return referenceValues;
    }

    public void setReferenceValues(Map<String, FlotSeries> referenceValues) {
        this.referenceValues = referenceValues;
    }

    public void addReferenceValues(String id, FlotSeries values) {
        this.referenceValues.put(id, values);
    }

    public Number[] getValueBeforeTimespan() {
        return valueBeforeTimespan;
    }

    public void setValueBeforeTimespan(Number[] valueBeforeTimespan) {
        this.valueBeforeTimespan = valueBeforeTimespan;
    }

    public Number[] getValueAfterTimespan() {
        return valueAfterTimespan;
    }

    public void setValueAfterTimespan(Number[] valueAfterTimespan) {
        this.valueAfterTimespan = valueAfterTimespan;
    }

}
