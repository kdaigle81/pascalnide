/*
 *  Copyright (c) 2017 Tran Le Duy
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

package com.duy.pascal.interpreter;

/**
 * Created by Duy on 29-May-17.
 */

public class StringTest extends BaseTestCase {

    @Override
    public String getDirTest() {
        return "test_string";
    }

    public void test1() {
        run("test1.pas");
    }

    public void test2() {
        run("test2.pas");
    }

    public void test3() {
        run("test3.pas");
    }

    public void testStringIndex() {
        try {
            run("test_string_index.pas", false);
            fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testStringIndex2() {
    }

    public void testUpcase(){
        run("upcase.pas");
    }
}
