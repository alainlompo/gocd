/*
 * Copyright 2018 ThoughtWorks, Inc.
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

package com.thoughtworks.go.apiv2.datasharing.usagedata.representers

import com.thoughtworks.go.server.domain.UsageStatistics
import org.junit.jupiter.api.Test

import static com.thoughtworks.go.api.base.JsonUtils.toObjectString
import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson

class UsageStatisticsRepresenterTest {

  @Test
  void  "should represent usage statistics"() {
    def actualJson = toObjectString({ UsageStatisticsRepresenter.toJSON(it, new UsageStatistics(100l, 10l, 1527244129553, "server-id", "18.7.0")) })
    def expectedJson = [
            "server_id"     : "server-id",
            "message_version": 1,
            "data"        : [
                    pipeline_count                : 100,
                    agent_count                   : 10,
                    oldest_pipeline_execution_time: 1527244129553,
                    gocd_version: "18.7.0"
            ]
    ]
    assertThatJson(actualJson).isEqualTo(expectedJson)
  }

  @Test
  void  "should handle null oldest_pipeline_execution_time"() {
    def actualJson = toObjectString({ UsageStatisticsRepresenter.toJSON(it, new UsageStatistics(100l, 10l, null, "server-id", "18.7.0")) })
    def expectedJson = [
            "server_id"     : "server-id",
            "message_version": 1,
            "data"        : [
                    pipeline_count                : 100,
                    agent_count                   : 10,
                    oldest_pipeline_execution_time: 0,
                    gocd_version: "18.7.0"
            ]
    ]
    assertThatJson(actualJson).isEqualTo(expectedJson)
  }
}
