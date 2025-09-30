package com.equitybank.billing.parser.domain.http;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Optional;

@Data
public class EnterpriseRequest implements Serializable {

    @SerializedName("billerID")
    private Optional<String> billerID;
    @SerializedName("configName")
    private Optional<String> configName;

    /**
     * HashTable to optimize on speed while getting with key
     */
    @SerializedName("data")
    private Optional<Hashtable<String, String>> data;
}
