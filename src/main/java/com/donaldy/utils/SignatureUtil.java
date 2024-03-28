package com.donaldy.utils;

import org.tomitribe.auth.signatures.Base64;
import org.tomitribe.auth.signatures.Signature;
import org.tomitribe.auth.signatures.Signer;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author donald
 * @date 2024/3/22
 */
public class SignatureUtil {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
    private static final List<String> SIGNATURE_HEADERS = new ArrayList<>(Arrays.asList("(request-target)", "Host", "Date", "Digest"));

    public static String generateDigest(String payload) throws NoSuchAlgorithmException {
        if (payload == null) {
            return "";
        }
        // 计算 digest
        final byte[] digest = MessageDigest.getInstance("SHA-256").digest(payload.getBytes()); // (1)
        return "SHA-256=" + new String(Base64.encodeBase64(digest));
    }

    public static String generateSignature(String appId, String appSecret, String method,
                                           String uri, Map<String, String> headers)
            throws NoSuchAlgorithmException, IOException {
        // 计算签名
        final Signature signature = new Signature(appId, "hmac-sha256", "hmac-sha256", null, Arrays.asList("(request-target)", "Host", "Date", "Digest"));
        final String secretHash = secretHash(appSecret);
        final Key key = new SecretKeySpec(secretHash.getBytes(), "HmacSHA256");
        final Signer signer = new Signer(key, signature);
        Signature sign = signer.sign(method, uri, headers);
        return sign.toString();
    }

    public static String secretHash(String appSecret) throws NoSuchAlgorithmException {
        final byte[] secretHashBytes = MessageDigest.getInstance("SHA-256").digest(appSecret.getBytes());
        return new String(Base64.encodeBase64(secretHashBytes));
    }

    public static Map<String, String> headers() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", "test-gateway.pingpongx.com");
        String date = DATE_FORMAT.format(new Date());
        headers.put("Date", date);
//        headers.put("Host", "gateway.pingpongx.com");
//        headers.put("Date", "Tue, 16 Jan 2024 10:03:20 GMT");
        return headers;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

//        String payload = "{\"clientId\":\"2111190004017172\",\"pageNo\":\"3\",\"pageSize\":\"10\",\"startTime\":\"1661961600\",\"endTime\":\"1664553600\"}";
//        Map<String, String> headers = headers();
//        String digest = generateDigest(payload);
//        headers.put("Digest",digest);
//        headers.put("Signature",(generateSignature("803797871484260","DFE2167FD0D149C38965AE4D","POST","/v2/dataapi/v2/account/payout-records",headers)));
//        System.out.println(headers);


//        String payload = "{\"clientId\":\"2111190004017172\",\"pageNo\":\"2\",\"pageSize\":\"10\",\"withdrawId\":\"WB2209292330047418003519\"}";
//        Map<String, String> headers = headers();
//        String digest = generateDigest(payload);
//        headers.put("Digest",digest);
//        headers.put("Signature",(generateSignature("803797871484260","DFE2167FD0D149C38965AE4D","POST","/v2/dataapi/v2/account/withdraw-detail",headers)));
//        System.out.println(headers);


        String payload = "{}";
        Map<String, String> headers = headers();
        String digest = generateDigest(payload);
        headers.put("Digest",digest);
        headers.put("Signature",(generateSignature("803797871484260","DFE2167FD0D149C38965AE4D","POST","/v2/dataapi/v2/store/clientIds",headers)));
        System.out.println(headers);

//        String payload = "{\"clientId\":\"2107230003597139\",\"platform\":\"AMAZON_NA\",\"startTime\":1669564800,\"endTime\":1669651200,\"pageNo\":1,\"pageSize\":10}";
//        Map<String, String> headers = headers();
//        String digest = generateDigest(payload);
//        headers.put("Digest",digest);
//        headers.put("Signature",(generateSignature("803737871595076","0A6A8314F46348CB95FD8186","POST","/v2/dataapi/v2/account/statement",headers)));
//        System.out.println(headers);


//        String payload = "{\"accountId\":\"100010080585252\",\"clientId\":\"181008110918153\",\"pageNo\":\"1\",\"pageSize\":\"50\",\"startTime\":\"1688140800\",\"endTime\":\"1689264000\",\"platform\":\"AMAZON_NA\"}";
//
//        String payload = "{\"accountId\":\"100010080585252\",\"clientId\":\"181008110918153\",\"pageNo\":\"1\",\"pageSize\":\"50\",\"startTime\":\"1689004800\",\"endTime\":\"1689264000\",\"platform\":\"AMAZON_NA\"}";
//        Map<String, String> headers = headers();
//        String digest = generateDigest(payload);
//        headers.put("Digest",digest);
//        headers.put("Signature",(generateSignature("804108101452540","ED8FFAB48EB94A2A9B90F970","POST","/v2/dataapi/v2/account/statement",headers)));
//        System.out.println(headers);

//        String payload = "{\"clientId\":\"181008110918153\",\"startTime\":1693411200,\"endTime\":1695139199,\"pageNo\":1,\"pageSize\":100}";
//        Map<String, String> headers = headers();
//        String digest = generateDigest(payload);
//        headers.put("Digest",digest);
//        headers.put("Signature",(generateSignature("804110103043530","3FAA41A579CB43ECB1E6DE09","POST","/v2/dataapi/v2/account/special-account-statement",headers)));
//        System.out.println(headers);


//
//        String payload = "{\"clientId\":\"181008110918153\"}";
//        Map<String, String> headers = headers();
//        String digest = generateDigest(payload);
//        headers.put("Digest",digest);
//        headers.put("Signature",(generateSignature("804110103623101","5CE15E9FB2F04201850A8238","POST","/v2/dataapi/v2/store/platforms",headers)));
//        System.out.println(headers);
    }
}
