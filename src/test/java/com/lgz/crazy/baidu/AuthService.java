package com.lgz.crazy.baidu;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by lgz on 2019/3/12.
 */
public class AuthService {
    /**
     * 获取权限token
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    public static String getAuth() {
        //appID = "15739957";
        // 官网获取的 API Key 更新为你注册的
        String clientId = "5B1r1BKRmAy7PzmmGPRERPzQ";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "fZM2AkEpuMoS1AP3gMuRb9ZUy1TZWw6M";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

    public static void main(String[] args) {
        String auth = getAuth();
        System.out.println(auth);
    }

    //20190312
    //result:{"refresh_token":"25.ecadd015f90d14c212d7d265ae18eb58.315360000.1867737818.282335-15739957",
    // "expires_in":2592000,
    // "session_key":"9mzdCymOcxDTWILkOXsCNpslE1GwYguh5715YfqOVXaF3WttPuHjAH4ENMld9ddmwWmxWZcl\/mgkn+dwMnN4p6ujx\/zhzw==",
    // "access_token":"24.01ae998171358bb4ac4faf17f14b2e7c.2592000.1554969818.282335-15739957",
    // "scope":"public vis-classify_dishes vis-classify_car brain_all_scope vis-classify_animal vis-classify_plant brain_object_detect brain_realtime_logo brain_dish_detect brain_car_detect brain_animal_classify brain_plant_classify brain_advanced_general_classify brain_poi_recognize wise_adapt lebo_resource_base lightservice_public hetu_basic lightcms_map_poi kaidian_kaidian ApsMisTest_Test\u6743\u9650 vis-classify_flower lpq_\u5f00\u653e cop_helloScope ApsMis_fangdi_permission smartapp_snsapi_base iop_autocar oauth_tp_app smartapp_smart_game_openapi oauth_sessionkey smartapp_swanid_verify smartapp_opensource_openapi",
    // "session_secret":"3e08d63416fa888bb7ae95a89dd621fa"}
    //24.01ae998171358bb4ac4faf17f14b2e7c.2592000.1554969818.282335-15739957

}
