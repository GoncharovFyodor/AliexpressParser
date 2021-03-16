package ru.goncharov.aliexpressparser.connector;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class AliexpressConnector implements Connector{
    private int offset;

    @Override
    public HttpURLConnection getConnect() {
        HttpURLConnection connection = null;
        try {
            /*goods are added asynchronously, and since they are parsed with a tool with a js interpreter (headless browser, etc.)
             which causes enumeration by memory, network and process, I found a way to parse them with a get request,
              similar to what is wired in js logic. Increasing the offset by 12 gives us a new page with goods.*/
            URL url = new URL("https://gpsfront.aliexpress.com/queryGpsProductAjax.do?"+
                    "callback=jQuery18308837264126129163_1564698375898&widget_id=5547572&platform=pc&limit=12&offset="+offset
                    +"&phase=1&productIds2Top=&postback=5d26986e-c068-499e-8628-d275b6326d85&_=1564698396398");

            connection = (HttpURLConnection) url.openConnection();
            /*By editing cookies, you can receive products with prices and descriptions in different languages.
            It is also responsible for issuing,  with different cookies may be different issuance*/
            connection.setRequestProperty("Cookie",
                    "ali_apache_id=11.139.3.112.16158238616.983798.4;"+
                            " xman_us_f=x_locale=en_US&x_l=0&last_popup_time=1615841915365&x_user=RU|8804|user|ifm|3709092448&no_popup_today=n&x_lid=ru2101646448gweae&x_c_chg=1&x_c_synced=1&x_as_i=%7B%22aeuCID%22%3A%22a49e0c1644f344b8b44f959ac2b3f619-1615021723685-04050-_etTy06%22%2C%22affiliateKey%22%3A%22_etTy06%22%2C%22channel%22%3A%22AFFILIATE%22%2C%22cv%22%3A%221%22%2C%22isCookieCache%22%3A%22N%22%2C%22ms%22%3A%221%22%2C%22pid%22%3A%221630629288%22%2C%22tagtime%22%3A1615021723685%7D&acs_rt=af02ae5b884b432995a03391de7d9626;"+
                            " aep_usuc_f=site=glo&c_tp=RUB&x_alimid=3709092448&isb=y&region=RU&b_locale=en_US;"+
                            " intl_common_forever=PJLkhVbemVCeiED+yZ4V3cMgpXxednpeZ/nDtMFYfb8Sc2lj2j3XWg==;"+
                            " xman_f=cxP3f2oeBXMsKYrUtlXUyOpxNaHfTBpEMjdSW3BBu98Nd67eKxO7aKtA5V7aTwIqzkIp9DZxnOhI3k/sFsB2wba9qMEPAkitoHL+EG52pzfys3vDXVTGaPsoAAd3BjqKfevFXMDKFeumkfEMCbewPX7vld2IvbHJbUcfdBN22Xx2UQcnRau7ReolOtWILF1EAc5x+lTKun78/gnIgxLYxG++8y4rUQxlvCl6mDWkZAFIKj2DX31AvSwbK8bH+cadMF585Ua4BSbaHmxd3REpT7acUauCzeAKY/wj747/SGFRQbuX+OXetsQHiSxjjsAmtjXn7SDeI54MLGSFcQaH8QPmmRUr7jBtQNsW/OE7K6cX7omNzwoF4EwFd8A+fSMNI9C/s0/6SJwsjpS2wTNQMGFaG7o50PYH80MyYseS3rpSu54rOXz1pA==;"+
                            " ali_apache_track=mt=1|ms=|mid=ru2101646448gweae;"+
                            " cna=9nHWGJnKgGoCAbDB80jYACRe;"+
                            " isg=BPj4Fh6N7AsA8gCQYa-aclTlyaaKYVzrQdnhkzJpEDPmTZk32nPeeopsBV19HRTD;"+
                            " l=eBOhPAWgjsC0zRPOBOfZourza7798IRfguPzaNbMiOCPOKCp5O3FW6wx8C89CnMNnstHR35VLnkHBXTT0yzHQxv9-e_-SAEtSdLh.;"+
                            " _ga=GA1.2.277852559.1615823861; _gid=GA1.2.2100265941.1615823861; _fbp=fb.1.1615823861399.1628892654;"+
                            " _m_h5_tk=0e2b4a2c4b29036407b748b7c45a8122_1615883018733; _m_h5_tk_enc=402c14f917004cbf377214d9ede3a445;"+
                            " aep_common_f=V0ZL+kIieirToq14zmipcy/fZ6zwH5j8ROvUiIBDtDlOYOop9rLJxg==; _uab_collina=161583578207335558530551;"+
                            " RT=\"z=1&dm=aliexpress.com&si=8c245989-e714-47d5-891f-9292debcd573&ss=kmbpsmf5&sl=0&tt=0&bcn=%2F%2F1737ad5d.akstat.io%2F\";"+
                            " aep_history=keywords%5E%0Akeywords%09%0A%0Aproduct_selloffer%5E%0Aproduct_selloffer%0932838577721%0932838577721;"+
                            " acs_usuc_t=acs_rt=da87360bd7f3434ab4e95cc37c312e23&x_csrf=uehud04jphkl; ali_apache_tracktmp=W_signed=Y;"+
                            " XSRF-TOKEN=47e30160-b8bf-418e-acce-4fc848b0261b; _hvn_login=13; aep_usuc_t=ber_l=A0;"+
                            " xman_t=G49n7tq5+UyugWS5slg2GKCJFPnlvZ4BEP6hiKOQUGt4srJbqS0NzSm0GgGkRiOVWf5D9bre2O3FLUwWdcS+GVLsNhgZwcJcE+69NeZgJziZyjsEKU51r10ogp1e3gn9urmpUQFn2JmwGgQEzzqCzW8tPzBgoexw3W1s0oGBeh3Z9MGV1iiX9Vj/20EmALsjaeXBlgV4bHumdAfDwjDVdCdh3kekCRAGDs4K3VACnu2lLFjV+hsj5Sbmk7eR0CTEVg3c+n/bBSeWOSk0nAtwcq24li3CEmBzwiPYXXfI1D1+0ogn4EU5FKIUxNjv4OWltNqGVOLQ6NHD+fATDXgVH1XTFDwHAVmjTQa8RwCaek/c3+TfOa4fwqT3q8m+/1s1Uzbzxg8QDB4fb7mMyRa+Xb5iZCphu6d9UN+fxrW/C70NLWkKLf/h0Ky0JtznFn7EIBLxcKoz/LNIR2M05NNRroO5psjhtBsdfW/niL9KUg7kUmNXfel/4qbVJonFv7Xp/h5tfIw0HX6Y+ecmPckZNIt7VBKEtDt7DJ24H63jp2VwH3+J1NY7QSv0lBHkuFZr8OWttXuHtK6zRgIur/dhXg==");
            connection.setRequestMethod("GET");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void setOffset(int offset) {
        this.offset=offset;
    }

    @Override
    public int getOffset() {
        return offset;
    }
}
