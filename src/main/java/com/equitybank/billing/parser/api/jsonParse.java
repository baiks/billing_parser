package com.equitybank.billing.parser.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.JsonPath;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/parser/v2")
public class jsonParse {
    public static void main(String[] args) throws JsonProcessingException {

        jsonParse jsonparse=new jsonParse();
        String jsonbill="{\n" +
                " \"amount\": \"230.0\",\n" +
                " \"billName\": \"Test_Account_3\",\n" +
                " \"billNumber\": \"33333333\",\n" +
                " \"billerCode\": \"123456\",\n" +
                " \"createdOn\": \"2016-12-20\",\n" +
                " \"currencyCode\": \"KES\",\n" +
                " \"customerName\": \"Test_Account_3\",\n" +
                " \"customerRefNumber\": \"33333333\",\n" +
                " \"description\": \"subscription fees\",\n" +
                " \"dueDate\": \"2016-12-21\",\n" +
                " \"expiryDate\": \"2016-12-29\",\n" +
                " \"Remarks\": \"Fees\",\n" +
                " \"type\": \"1\"\n" +
                "}";


        String res= jsonparse.parse(jsonbill,"/amount","/billName","/billNumber","/description");
        System.out.println(res);
    }

    @RequestMapping(value = "jsonparse", method = RequestMethod.POST )
 public  String parse(String jsonpayload ,String path1,String path2,String path3,String path4) throws JsonProcessingException {



        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonpayload);
        JsonNode response_status = node.at(path1);
        JsonNode response_code = node.at(path2);
        JsonNode response_message = node.at(path3);
        JsonNode response_data = node.at(path4);

     List<String> resList=new ArrayList<>();
        resList.add(response_status.toString());
        resList.add(response_code.toString());
        resList.add(response_message.toString());
        resList.add(response_data.toString());

    return resList.toString();
    }
    @RequestMapping(value = "jsonBillparser", method = RequestMethod.POST )
    public  String Billparser(String jsonpayload ,@RequestHeader("statuscode") String statuscode,@RequestHeader("description") String description,@RequestHeader("message") String message,@RequestHeader("amount") String amount) throws JsonProcessingException {



        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonpayload);
        JsonNode response_status = node.at(statuscode);
        JsonNode response_code = node.at(description);
        JsonNode response_message = node.at(message);
        JsonNode response_data = node.at(amount);

        List<String> resList=new ArrayList<>();
        resList.add(response_status.toString());
        resList.add(response_code.toString());
        resList.add(response_message.toString());
        resList.add(response_data.toString());

        return resList.toString();
    }

}
