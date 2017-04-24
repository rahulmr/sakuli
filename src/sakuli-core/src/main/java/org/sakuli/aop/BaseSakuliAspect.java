/*
 * Sakuli - Testing and Monitoring-Tool for Websites and common UIs.
 *
 * Copyright 2013 - 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sakuli.aop;

import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.sakuli.actions.logging.LogToResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Iterator;

import static org.apache.commons.lang.StringUtils.isNotEmpty;
import static org.apache.commons.lang.StringUtils.removeEnd;
import static org.apache.commons.lang3.StringUtils.removeStart;

/**
 * @author tschneck
 *         Date: 23.09.14
 */
public abstract class BaseSakuliAspect {
    public static final String ALREADY_PROCESSED = "{{SAKULI_EX}}";

    /**
     * @return the {@link Logger} for the assigned joinPoint.
     */
    public static Logger getLogger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
    }

    public static String getClassAndMethodAsString(JoinPoint joinPoint) {
        return String.format("%s.%s()",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName());
    }

    public static String printArgs(JoinPoint joinPoint, boolean logArgs) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Iterator iterator = Arrays.asList(joinPoint.getArgs()).iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            if (logArgs) {
                if (o != null) {
                    if (o instanceof Object[]) {
                        builder.append(printArray((Object[]) o));
                    } else {
                        builder.append(o.toString());
                    }
                } else {
                    builder.append("NULL");
                }
            } else {
                builder.append("****");
            }
            if (iterator.hasNext()) {
                builder.append(", ");
            }
        }
        return builder.append("]").toString();
    }

    public static String printArray(Object[] objects) {
        StringBuilder sb = new StringBuilder("[");
        for (Object o : objects) {
            if (sb.toString().length() > 1) {
                sb.append(", ");
            }
            sb.append(o.toString());
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * @return based on the different arguments of the {@link LogToResult} annotation an different output {@link String}
     */
    public static StringBuilder createLoggingString(JoinPoint joinPoint, LogToResult logToResult) {
        if (logToResult.logArgsOnly()) {
            return new StringBuilder(removeEnd(removeStart(printArgs(joinPoint, logToResult.logArgs()), "["), "]"));
        }

        StringBuilder message = new StringBuilder();
        //log class instance?
        if (logToResult.logClassInstance() && joinPoint.getTarget() != null) {
            message.append("\"").append(joinPoint.getTarget().toString()).append("\" ");
        }
        message.append(getClassAndMethodAsString(joinPoint));

        //add message if needed
        if (isNotEmpty(logToResult.message())) {
            message.append(" - ").append(logToResult.message());
        }
        //add args if needed
        if (ArrayUtils.isNotEmpty(joinPoint.getArgs())) {
            message.append(" with arg(s) ").append(printArgs(joinPoint, logToResult.logArgs()));
        }
        return message;
    }
}
