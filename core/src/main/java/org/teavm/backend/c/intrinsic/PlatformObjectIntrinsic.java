/*
 *  Copyright 2018 Alexey Andreev.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.teavm.backend.c.intrinsic;

import org.teavm.ast.InvocationExpr;
import org.teavm.model.MethodReference;

public class PlatformObjectIntrinsic implements Intrinsic {
    private static final String PLATFORM_OBJECT = "org.teavm.platform.PlatformObject";

    @Override
    public boolean canHandle(MethodReference method) {
        if (!method.getClassName().equals(PLATFORM_OBJECT)) {
            return false;
        }

        return method.getName().equals("getPlatformClass");
    }

    @Override
    public void apply(IntrinsicContext context, InvocationExpr invocation) {
        switch (invocation.getMethod().getName()) {
            case "getPlatformClass":
                context.writer().print("CLASS_OF(");
                context.emit(invocation.getArguments().get(0));
                context.writer().print(")");
                break;
        }
    }
}
