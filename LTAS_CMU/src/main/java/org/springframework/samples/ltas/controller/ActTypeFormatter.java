/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.ltas.controller;


import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.ltas.entity.ActType;
import org.springframework.samples.ltas.service.SystemService;


public class ActTypeFormatter implements Formatter<ActType> {

    private final SystemService systemService;


    @Autowired
    public ActTypeFormatter(SystemService systemService) {
        this.systemService = systemService;
    }

    @Override
    public String print(ActType actType, Locale locale) {
        return actType.getName();
    }

    @Override
    public ActType parse(String text, Locale locale) throws ParseException {
        Collection<ActType> findActTypes = this.systemService.findActTypes();
        for (ActType type : findActTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }
        throw new ParseException("type not found: " + text, 0);
    }

}
