/*
 * Sakuli - Testing and Monitoring-Tool for Websites and common UIs.
 *
 * Copyright 2013 - 2018 the original author or authors.
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

package org.sakuli.services.forwarder.configuration;

import org.sakuli.datamodel.AbstractTestDataEntity;
import org.sakuli.datamodel.TestCase;
import org.sakuli.datamodel.TestCaseStep;
import org.sakuli.datamodel.TestSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class GetTestDataEntityTypeFunctionTest {

    @DataProvider
    public Object[][] getTestDataEntityTypeDP() {
        return new Object[][] {
                { new TestSuite(), "TestSuite"},
                { new TestCase("test", "1"), "TestCase"},
                { new TestCaseStep(), "TestCaseStep"},
        };
    }

    @Test(dataProvider = "getTestDataEntityTypeDP")
    public void getTestDataEntityType(AbstractTestDataEntity testDataEntity, String expectedType) {
        GetTestDataEntityTypeFunction getTestDataEntityTypeFunction = new GetTestDataEntityTypeFunction();
        assertEquals(getTestDataEntityTypeFunction.execute(Arrays.asList(testDataEntity)), expectedType);
    }

}
