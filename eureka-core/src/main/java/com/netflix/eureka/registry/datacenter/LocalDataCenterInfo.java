/*
 * Copyright 2014 Netflix, Inc.
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

package com.netflix.eureka.registry.datacenter;

import com.netflix.eureka.registry.DataCenterInfo;
import rx.Observable;

/**
 * Convenience factory methods for creating {@link DataCenterInfo} objects for a given
 * data center type.
 *
 * @author Tomasz Bak
 */
public final class LocalDataCenterInfo {

    private LocalDataCenterInfo() {
    }

    public enum DataCenterType {
        Basic,
        AWS
    }

    public static Observable<? extends DataCenterInfo> forDataCenterType(DataCenterType type) {
        switch (type) {
            case Basic:
                return Observable.just(BasicDataCenterInfo.fromSystemData());
            case AWS:
                return new AwsDataCenterInfoProvider().dataCenterInfo();
        }
        throw new IllegalStateException("Unhandled type " + type);
    }
}
