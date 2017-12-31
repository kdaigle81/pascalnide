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

package com.duy.pascal.ui.file;

import android.content.Context;
import android.content.res.Resources;

/**
 * @author Jecelyin Peng <jecelyin@gmail.com>
 */
public class FileExplorerContext {
    private final Context context;

    public FileExplorerContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public Resources getResources() {
        return context.getResources();
    }
}
