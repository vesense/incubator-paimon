/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.paimon.format;

import org.apache.paimon.options.ConfigOption;

import org.apache.orc.OrcConf;

import static org.apache.orc.OrcConf.DICTIONARY_KEY_SIZE_THRESHOLD;
import static org.apache.orc.OrcConf.DIRECT_ENCODING_COLUMNS;
import static org.apache.paimon.options.ConfigOptions.key;

/** Options for orc format. */
public class OrcOptions {

    public static final ConfigOption<Integer> ORC_WRITE_BATCH_SIZE =
            key("orc.write.batch-size")
                    .intType()
                    .defaultValue(1024)
                    .withDescription("write batch size for orc.");

    public static final ConfigOption<String> ORC_COMPRESS =
            key(OrcConf.COMPRESS.getAttribute())
                    .stringType()
                    .defaultValue("lz4")
                    .withDescription(
                            "Define the compression codec for ORC file, if a higher compression ratio is required, "
                                    + "it is recommended to configure it as 'zstd', and you can configure: "
                                    + "orc.compression.zstd.level");

    public static final ConfigOption<Integer> ORC_COLUMN_ENCODING_DIRECT =
            key(DIRECT_ENCODING_COLUMNS.getAttribute())
                    .intType()
                    .noDefaultValue()
                    .withDescription(
                            "Comma-separated list of fields for which dictionary encoding is to be skipped in orc.");

    public static final ConfigOption<Double> ORC_DICTIONARY_KEY_THRESHOLD =
            key(DICTIONARY_KEY_SIZE_THRESHOLD.getAttribute())
                    .doubleType()
                    .defaultValue((Double) DICTIONARY_KEY_SIZE_THRESHOLD.getDefaultValue())
                    .withDescription(
                            "If the number of distinct keys in a dictionary is greater than this "
                                    + "fraction of the total number of non-null rows, turn off "
                                    + "dictionary encoding in orc. Use 0 to always disable dictionary encoding. "
                                    + "Use 1 to always use dictionary encoding.");

    // Do not use OrcConf.COMPRESSION_ZSTD_LEVEL, it may cause IDE testing to occur
    // NoSuchMethodException
    public static final ConfigOption<Integer> ORC_COMPRESSION_ZSTD_LEVEL =
            key("orc.compression.zstd.level")
                    .intType()
                    .defaultValue(3)
                    .withDescription(
                            "Define the compression level to use with ZStandard codec while writing data. "
                                    + "The valid range is 1~22.");
}
