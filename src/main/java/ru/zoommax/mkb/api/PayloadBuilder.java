package ru.zoommax.mkb.api;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.http.entity.StringEntity;
import ru.zoommax.web.Client;
import ru.zoommax.web.RequestType;

@Builder
public class PayloadBuilder {
    /**
     * Идентификатор магазина
     * [0-9]{15}
     */
    @Getter
    private String mid;
    /**
     * Идентификатор заказа
     * [a-zA-Z0-9,-/(){}]{1,100}
     */
    @Getter
    private String oid;
    /**
     * Полный номер карты
     * [0-9]{12,19}
     */
    @Getter
    private String pan;
    /**
     * Срок действия карты (в формате YYMM)
     * [0-9]{4}
     */
    @Getter
    private String exp_date;
    /**
     * CVV код карты
     * [0-9]{3}
     */
    @Getter
    private String cvv;
    /**
     * Сумма заказа, Сумма завершения операции
     * [0-9]{1,12}
     */
    @Getter
    private String amount;
    /**
     * Валюта заказа
     * [0-9]{3}
     */
    @Getter
    private String currency;
    /**
     * Идентификатор банка-эквайра
     * [0-9]{5}
     */
    @Getter
    private String aid;
    /**
     * Почта клиента
     * [a-zA-Z0-9,-/(){}]
     */
    @Getter
    private String client_mail;
    /**
     * Поле для получения ответа от сервера
     * [a-zA-Z0-9,-/(){}]
     */
    @Getter
    private String resp_url;
    /**
     * Поле для получения ответа от сервера
     * [a-zA-Z0-9,-/(){}]
     */
    @Getter
    private String direct_post_url;
    /**
     * Идентификатор агрегатора
     * [0-9]{0,20}
     */
    @Getter
    private String psp_id;
    /**
     * Признак отправки рекуррентного платежа.
     * При =N поля игнорируются.
     * ^[N||Y]$
     */
    @Getter
    private String Recurring;
    /**
     * Дата первого платежа, т.е. фактически текущая дата (YYYYMMDD)
     * ^[0-9]{8}$
     */
    @Getter
    private String ExecutionDate;
    /**
     * Периодичность списания, где D — раз в день, M — раз в месяц, Q — раз в квартал.
     * Отсчёт начинается от даты первого платежа.
     * ^[D||M||Q|$
     */
    @Getter
    private String Frequency;
    /**
     * Количество списаний по подписке.
     * От 1 до 365- максимально допустимые значения зависят от Frequency, если подписка не до окончания срока действия карты.
     * 999-до окончания срока действия карты
     * ^[0-9]{1,3}$
     */
    @Getter
    private String NumberOfRecurrences;
    /**
     * Клиентская комиссия
     * true
     */
    @Getter
    private String isCommission;
    /**
     * Признак привязки карты
     * true
     */
    @Getter
    private String bind_card;
    /**
     * ID клиента, для привязки дополнительной карты к уже существующему клиенту
     * [a-zA-Z0-9]
     */
    @Getter
    private String client_id;
    /**
     * Номер чека из запроса на создание чека
     * [a-zA-Z0-9,-/(){}]{1,100}
     */
    @Getter
    private String receipt_id;
    /**
     * Идентификатор запроса
     * [0-9]{32,50}
     */
    @Getter
    private String request_id;
    /**
     * Шифрованный результат прохождения 3DSec
     * [a-zA-Z0-9,.!=].
     */
    @Getter
    private String pares;
    /**
     * Значение проверки подлинности владельца карты
     * [A-Za-z0-9 +]{20,50}(=)
     */
    @Getter
    private String cavv;
    /**
     * Идентификатор 3DSec операции
     * [A-Za-z0-9 +]{20,50}(=)
     */
    @Getter
    private String xid;
    /**
     * [0-9]{2}
     */
    @Getter
    private String eci;
    /**
     * Токен из Samsung Pay
     * Токен из Apple Pay
     * JSON объект
     */
    @Getter
    private String token;
    /**
     * Тип оплаты
     * samsung, apple
     */
    @Getter
    private String wallet;
    /**
     * Фамилия
     * [a-zA-Z-]
     */
    @Getter
    private String s_surname;
    /**
     * Имя
     * [a-zA-Z-]
     */
    @Getter
    private String s_firstName;
    /**
     * Адрес
     * [a-zA-ZZ0-9,-/]
     */
    @Getter
    private String s_address;
    /**
     * Идентификатор карты
     * [a-zA-Z0-9,-/(){}]
     */
    @Getter
    private String card_id;
    /**
     * тип ответа
     * Full / Short
     */
    @Getter
    private String responseType;
    @Getter
    private String login;
    @Getter
    private String password;

    /**
     *
     * @param point Url из API МКБ
     * @return
     */
    @SneakyThrows
    public ResposeParser request(MkbEndPoints point, RequestType type){
        Client client = null;
        String url = point.toString();
        System.out.println(url);
        if (type == RequestType.POST) {
            String json = new Gson().toJson(this);
            client = Client.builder().requestType(type).stringEntity(new StringEntity(json)).urlPath(url).build();
        }else {
            switch (point){
                case recurring:
                    url += "?mid="+getMid()+"&oid="+getOid();
                    break;
                case client:
                    url = url.replace("{mid}", getMid()).replace("{client_id}", getClient_id());
                    break;
                case clientCardDelete:
                    url = url.replace("{mid}", getMid()).replace("{client_id}", getClient_id()).replace("{card_id}", getCard_id());
                    break;
                case clientCardAllDelete:
                    url = url.replace("{mid}", getMid()).replace("{client_id}", getClient_id());
                    break;
                case getOrderStatus:
                    url = url.replace("{mid}", getMid()).replace("{oid}", getOid())+"?login="+getLogin()+"&password="+getPassword()+"&responseType="+getResponseType();
                    break;
            }
            client = Client.builder().requestType(type).urlPath(url).build();
        }
        assert client != null;
        String response = client.request();
        return new Gson().fromJson(response, ResposeParser.class);
    }
}
