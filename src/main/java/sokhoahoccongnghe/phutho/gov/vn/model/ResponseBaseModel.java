package sokhoahoccongnghe.phutho.gov.vn.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResponseBaseModel {
    public static <T> ResponseEntity<Object> responseBuidler(String responseMessage,
                                                             HttpStatus responseStatus,
                                                             T responseObject, boolean isSuccess) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE dd MMM yyyy hh:mm:ss");
        Map<String, Object> response = new HashMap<>();
        response.put("message", responseMessage);
        response.put("httpStatus", responseStatus);
        response.put("success", isSuccess);
        if (!isSuccess) response.put("time", sdf.format(new Date()));
        else response.put("data", responseObject);
        return new ResponseEntity<>(response, responseStatus);
    }

//    public static ResponseEntity<Object> responseExceptionBuidler(String responseMessage,
//                                                         HttpStatus responseStatus,
//                                                         Date time,boolean isSuccess) {
//        SimpleDateFormat sdf = new SimpleDateFormat("EEE dd MMM yyyy hh:mm:ss");
//        Map<String, Object> response = new HashMap<>();
//        response.put("time", sdf.format(time));
//        response.put("message", responseMessage);
//        response.put("httpStatus", responseStatus);
//        response.put("success",isSuccess);
//        return new ResponseEntity<>(response, responseStatus);
//    }
}
