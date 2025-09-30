package com.equitybank.billing.parser.domain.http;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.Optional;

@Data
public class ConfigurationRequest implements Serializable {
    @SerializedName("url")
    private Optional<String> url;
    @SerializedName("body")
    private Optional<String> body;
    @SerializedName("header")
    private Optional<String> header;
    @SerializedName("configurationDataType")
    private Optional<String> configurationDataType;
    @SerializedName("configurationName")
    private Optional<String> configurationName;
}
