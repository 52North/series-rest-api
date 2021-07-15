/*
 * Copyright (C) 2013-2021 52°North Spatial Information Research GmbH
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.n52.io.HrefHelper;

public class HrefHelperTest {

    @Test
    public void when_constructBackwardsCompatibleUrl_then_relativeLink() {
        String actual = HrefHelper.constructHref(null, "/procedures");
        assertThat(actual, is("./procedures"));
    }

    @Test
    public void when_constructUrlWithNonTrailingSlashBaseUrl_then_fullLink() {
        String actual = HrefHelper.constructHref("http://localhost:8080/foo/bar", "/procedures");
        assertThat(actual, is("http://localhost:8080/foo/bar/procedures"));
    }

    @Test
    public void when_constructUrlFromBaseUrl_then_fullLink() {
        String actual = HrefHelper.constructHref("http://localhost:8080/foo/bar/", "/procedures");
        assertThat(actual, is("http://localhost:8080/foo/bar/procedures"));
    }

    @Test
    public void given_baseUrlWithoutTrailingSlash_when_pathWithTrailingSlash_then_fullLink() {
        String actual = HrefHelper.constructHref("http://localhost:8080/foo/bar", "procedures");
        assertThat(actual, is("http://localhost:8080/foo/bar/procedures"));
    }

}
