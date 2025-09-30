package com.equitybank.billing.parser.domain.http;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.Optional;

@Data
public class BillerRequest implements Serializable {
    @SerializedName("billerName")
    private Optional<String> billerName;
    @SerializedName("billerID")
    private Optional<String> billerID;

}
