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
package org.n52.web.ctrl;

import static org.n52.io.request.QueryParameters.createFromQuery;
import static org.n52.web.common.Stopwatch.startStopwatch;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.n52.io.request.IoParameters;
import org.n52.io.response.OutputCollection;
import org.n52.io.response.ParameterOutput;
import org.n52.io.response.extension.MetadataExtension;
import org.n52.sensorweb.spi.LocaleAwareSortService;
import org.n52.sensorweb.spi.ParameterService;
import org.n52.web.common.Stopwatch;
import org.n52.web.exception.BadRequestException;
import org.n52.web.exception.InternalServerException;
import org.n52.web.exception.ResourceNotFoundException;
import org.n52.web.exception.WebExceptionAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.ModelAndView;

public abstract class ParameterController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterController.class);

    private List<MetadataExtension<ParameterOutput>> metadataExtensions = new ArrayList<>();

    private ParameterService<ParameterOutput> parameterService;

    public void getRawData(HttpServletResponse response, String id, MultiValueMap<String, String> query) {
        if (!getParameterService().supportsRawData()) {
            throw new BadRequestException("Querying of raw procedure data is not supported by the underlying service!");
        }
        IoParameters queryMap = createFromQuery(query);
        try (InputStream inputStream = getParameterService().getRawDataService().getRawData(id, queryMap)) {
            if (inputStream == null) {
                throw new ResourceNotFoundException("No raw data found for id '" + id + "'.");
            }
            IOUtils.copyLarge(inputStream, response.getOutputStream());
        } catch (IOException e) {
            throw new InternalServerException("Error while querying raw data", e);
        }
    }

    public Map<String, Object> getExtras(String resourceId, MultiValueMap<String, String> query) {
        IoParameters queryMap = createFromQuery(query);

        Map<String, Object> extras = new HashMap<>();
        for (MetadataExtension<ParameterOutput> extension : metadataExtensions) {
            ParameterOutput from = parameterService.getParameter(resourceId, queryMap);
            final Map<String, Object> furtherExtras = extension.getExtras(from, queryMap);
            Collection<String> overridableKeys = checkForOverridingData(extras, furtherExtras);
            if (!overridableKeys.isEmpty()) {
                String[] keys = overridableKeys.toArray(new String[0]);
                LOGGER.warn("Metadata extension overrides existing extra data: {}", Arrays.toString(keys));
            }
            extras.putAll(furtherExtras);
        }
        return extras;
    }

    private Collection<String> checkForOverridingData(Map<String, Object> data, Map<String, Object> dataToAdd) {
        Map<String, Object> currentData = new HashMap<>(data);
        Set<String> overridableKeys = currentData.keySet();
        overridableKeys.retainAll(dataToAdd.keySet());
        return overridableKeys;
    }

    public ModelAndView getCollection(MultiValueMap<String, String> query) {
        IoParameters queryMap = createFromQuery(query);

        if (queryMap.isExpanded()) {
            Stopwatch stopwatch = startStopwatch();
            OutputCollection<ParameterOutput> result = addExtensionInfos(parameterService.getExpandedParameters(queryMap));
            LOGGER.debug("Processing request took {} seconds.", stopwatch.stopInSeconds());

            // TODO add paging
            return createModelAndView(result);
        } else {
            OutputCollection<ParameterOutput> results = parameterService.getCondensedParameters(queryMap);

            // TODO add paging
            return createModelAndView(results);
        }
    }

    public ModelAndView getItem(String id, MultiValueMap<String, String> query) {
        IoParameters queryMap = createFromQuery(query);
        ParameterOutput item = parameterService.getParameter(id, queryMap);
        
        if (item == null) {
            throw new ResourceNotFoundException("Found no parameter for id '" + id + "'.");
        }

        ParameterOutput parameter = addExtensionInfos(item);
        return new ModelAndView().addObject(parameter);
    }

    protected OutputCollection<ParameterOutput> addExtensionInfos(OutputCollection<ParameterOutput> toBeProcessed) {
        for (ParameterOutput parameterOutput : toBeProcessed) {
            addExtensionInfos(parameterOutput);
        }
        return toBeProcessed;
    }

    protected ParameterOutput addExtensionInfos(ParameterOutput output) {
        for (MetadataExtension<ParameterOutput> extension : metadataExtensions) {
            extension.addExtraMetadataFieldNames(output);
        }
        return output;
    }

    protected ModelAndView createModelAndView(OutputCollection<?> items) {
        return new ModelAndView().addObject(items);
    }

    public ParameterService<ParameterOutput> getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService<ParameterOutput> parameterService) {
        ParameterService<ParameterOutput> service = new WebExceptionAdapter<>(parameterService);
        this.parameterService = new LocaleAwareSortService<>(service);
    }

    public List<MetadataExtension<ParameterOutput>> getMetadataExtensions() {
        return metadataExtensions;
    }

    public void setMetadataExtensions(List<MetadataExtension<ParameterOutput>> metadataExtensions) {
        this.metadataExtensions = metadataExtensions;
    }
}
