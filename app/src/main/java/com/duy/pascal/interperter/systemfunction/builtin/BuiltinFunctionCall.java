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

package com.duy.pascal.interperter.systemfunction.builtin;

import com.duy.pascal.interperter.ast.runtime.value.FunctionCall;
import com.duy.pascal.interperter.declaration.Name;

/**
 * Created by Duy on 17-Aug-17.
 */

public abstract class BuiltinFunctionCall extends FunctionCall {
    @Override
    protected Name getFunctionName() {
        return Name.create(getFunctionNameImpl());
    }

    protected abstract String getFunctionNameImpl();
}
