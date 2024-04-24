package com.kosign.redoapi.constant;

public class Constants {
    public static final String AMOUNT_MAX = "9999999999999999.99";
    public static final int AMOUNT_INTEGER = 18;
    public static final int TAX_INTEGER = 5;
    public static final int AMOUNT_FRACTION_DIGITS = 2;
    public static final int BILLER_ID_LENGTH = 10;

    public static final int FREE_TRANSACTION_NUMBER_LENGTH = 10;
    public static final int FREE_NUMBER_OF_CUSTOMER_LENGTH = 10;

    public static final int SERVICE_CODE_LENGTH = 10;
    public static final int SERVICE_PLAN_ID_LENGTH = 10;
    public static final int VIRTUAL_ACCOUNT_NUMBER_LENGTH = 20;
    public static final int PARENT_ACCOUNT_NUMBER_LENGTH = 20;
    public static final int BANK_CODE_LENGTH = 4;
    public static final int CURRENCY_CODE_LENGTH = 3;

    public static final int DATE_LENGTH = 8;
    public static final int DATE_LENGTHS = 6;
    final public static String DATE_PATTERN = "^[0-9]{" + DATE_LENGTH + "}$";

    final public static int BILL_NO_LENGTH = 16;
    final public static String BILL_NO_PATTERN = "^[0-9]{" + BILL_NO_LENGTH + "}$";

    public static final int TIME_LENGTH = 6;
    public static final int TRANSACTION_ID_LENGTH = 20;

    public static final int DATETIME_LENGTH = 14;
    final public static String DATETIME_PATTERN = "^[0-9]{" + DATETIME_LENGTH + "}$";

    public static final int PAYER_NAME_LENGTH = 25;
    public static final int REMARK_LENGTH = 250;
    public static final int DESCRIPTION_LENGTH = 250;
    public static final int PHONE_NUMBER_LENGTH = 20;
    public static final int COUNTRY_PHONE_CODE_LENGTH = 5;
    public static final int EMAIL_LENGTH = 100;
    public static final int PROFILE_IMAGE_LENGTH = 250;
    public static final int GROUP_ID_LENGTH = 10;
    public static final int FULL_NAME_LENGTH = 100;
    public static final int PASSWORD_LENGTH = 50;
    public static final int STATE_LENGTH = 10;
    public static final int COMMUNE_LENGTH = 10;
    public static final int DISTRICT_LENGTH = 10;
    public static final int VILLAGE_LENGTH = 100;
    public static final int STREET_NO_LENGTH = 100;
    public static final int BUILDING_NO_LENGTH = 100;

    public static final int TELEPHONE_LENGTH = 20;


    public static final int TELEGRAM_ID_LENGTH = 50;

    public static final int VIRTUAL_ACCOUNT_LENGTH = 20;

    public static final int CURRENCY_LENGTH = 3;

    public static final int PAYMENT_TYPE_LENGTH = 10;


    public static final int ID_DOCUMENT_TYPE = 10;
    public static final int ID_DOCUMENT_NUMBER = 20;
    public static final int TAX_PERSON_TYPE = 10;
    public static final int FULLNAME_LENGTH = 100;
    public static final int COMPANY_LENGTH = 100;
    public static final int PHONENUMBER_LENGTH = 20;
    public static final int CONTACT_PHONENUMBER_LENGTH = 20;
    public static final int IMG_LENGTH = 250;
    public static final int ADDRESS_LENGTH = 250;
    public static final int PWD_LENGTH = 50;
    public static final int PASSWORDS_LENGTH = 150;
    public static final int BUSINESS_TYPE_LENGTH = 10;
    public static final int TAX_ID_LENGTH = 100;
    public static final int PAYMENT_TERM_LENGTH = 3;

    final public static String PAYMENT_TERM_PATTERN = "^[0-9]{1," + PAYMENT_TERM_LENGTH + "}$";

    public static final int OTP_CODE_LENGTH = 6;
    public static final int ACCOUNT_NAME_LENGTH = 100;

    public static final int MOBILE_ACCOUNT_NUMBER_LENGTH = 10;
    public static final int MOBILE_ACCOUNT_NAME_LENGTH = 25;
    public static final int SESS_ID_LENGTH = 100;

    public static final int CODE = 10;

    public static final int GROUP_CODE = 50;

    final public static String PHONE_NUMBER="\\d{9,10}";


    public static final int EXCHANGE_RATE_INTEGER = 7;
    public static final int EXCHANGE_RATE_FRACTION_DIGITS = 2;
}
