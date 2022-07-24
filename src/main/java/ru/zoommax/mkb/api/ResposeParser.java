package ru.zoommax.mkb.api;

import lombok.Getter;

public class ResposeParser {
    /**
     * Код причины отказа
     * [0-9]{1}
     */
    @Getter
    private String reason_code;
    /**
     * Код ответа
     * [0-9]{1,2}
     */
    @Getter
    private String response_code;
    /**
     * Описание ответа
     * [a-zA-Z0-9,.!]{1,500}
     */
    @Getter
    private String description;
    /**
     * Адрес страницы для прохождения 3DSec
     * [a-zA-Z0-9,.!=/-_%].
     */
    @Getter
    private String acs_url;
    /**
     * Шифрованные параметры для прохождения 3DSec
     * [a-zA-Z0-9,.!=].
     */
    @Getter
    private String pareq;
    /**
     * Уникальный идентификатор запроса для связи с обработкой ответа
     * [0-9a-zA-Z/-]{32,50}
     */
    @Getter
    private String request_id;
    /**
     * Идентификатор агрегатора
     * [0-9a-zA-Z/-]{32,50}/td>
     */
    @Getter
    private String psp_id;
    /**
     * Сумма с комиссией
     * [0-9]
     */
    @Getter
    private String sum;
    /**
     * Процент комиссии
     * [0-9]
     */
    @Getter
    private String percent;
    /**
     * Сумма комиссии
     * [0-9]
     */
    @Getter
    private String fee;
    /**
     * ID клиента
     * [a-zA-Z0-9]
     */
    @Getter
    private String client_id;
    @Getter
    private String version;
    /**
     * Номер ссылки RRN
     * [0-9]{12}
     */
    @Getter
    private String rrn;
    /**
     * Код авторизации
     * [A-Z0-9]{6}
     */
    @Getter
    private String auth_id;
    /**
     * Код ответа хоста
     * [0-9]{2}
     */
    @Getter
    private String host_code;
    /**
     * Остаток на счете
     * [0-9] {1,500}
     */
    @Getter
    private String balance;
    /**
     * Карта получателя(PAN)
     * [0-9]{12,19}
     */
    @Getter
    private String destination_account;
    /**
     * Признак отправки рекуррентного платежа (вкл/откл).
     * Y или N
     */
    @Getter
    private String recurring;
    /**
     * Дата первого платежа
     * ^[0-9]{8}$
     */
    @Getter
    private String executionDate;
    /**
     * Периодичность списания:
     *
     * D – раз в день,
     * W – раз в неделю (7 дней),
     * F – раз в 2 недели (14 дней),
     * M – раз в месяц (30 дней),
     * E – раз в 2 месяца (60 дней),
     * Q – раз в квартал (90 дней),
     * Y – раз в год (365 дней).
     *
     * Отсчёт начинается от даты первого платежа.
     *
     * ^[D||W||F||M||E||Q||Y|$
     */
    @Getter
    private String frequency;
    /**
     * Дата следующего платежа
     * ^[0-9]{8}$
     */
    @Getter
    private String nextDataPay;
    /**
     * Количество списаний
     * от 1 до 365 или 999
     */
    @Getter
    private String quantityPay;
    /**
     * Сумма списания
     * ^[0-9]{15}$
     */
    @Getter
    private String amount;

}
