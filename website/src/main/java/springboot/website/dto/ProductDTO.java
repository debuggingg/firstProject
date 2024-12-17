package springboot.website.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*
CREATE TABLE PRODUCT (
   PROD_NO NUMBER CONSTRAINT PROD_NO_PK PRIMARY KEY,
   PROD_TYPE NUMBER,
   PROD_NAME VARCHAR2(50),
   PROD_PRICE NUMBER,
   PROD_AMOUNT NUMBER,
   PROD_IMAGE1 VARCHAR2(1000),
   PROD_IMAGE2 VARCHAR2(1000),
   PROD_IMAGE3 VARCHAR2(1000),
   PROD_IMAGE4 VARCHAR2(1000),
   PROD_INFO VARCHAR2(2000),
   PROD_IN_DATE DATE DEFAULT SYSDATE
);

CREATE SEQUENCE PRODUCT_SEQ;

DESC product;

SELECT * FROM product;

COMMIT;

*/
@Entity
public class ProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prodNo;
    private int prodType;
    private String prodName;
    private int prodPrice;
    private int prodAmount;
    private String prodImage1;
    private String prodImage2;
    private String prodImage3;
    private String prodImage4;
    private String prodInfo;
    private String prodInDate;


    public ProductDTO() {
        // TODO Auto-generated constructor stub
    }


    public int getProdNo() {
        return prodNo;
    }


    public void setProdNo(int prodNo) {
        this.prodNo = prodNo;
    }


    public int getProdType() {
        return prodType;
    }


    public void setProdType(int prodType) {
        this.prodType = prodType;
    }


    public String getProdName() {
        return prodName;
    }


    public void setProdName(String prodName) {
        this.prodName = prodName;
    }


    public int getProdPrice() {
        return prodPrice;
    }


    public void setProdPrice(int prodPrice) {
        this.prodPrice = prodPrice;
    }


    public int getProdAmount() {
        return prodAmount;
    }


    public void setProdAmount(int prodAmount) {
        this.prodAmount = prodAmount;
    }


    public String getProdImage1() {
        return prodImage1;
    }


    public void setProdImage1(String prodImage1) {
        this.prodImage1 = prodImage1;
    }


    public String getProdImage2() {
        return prodImage2;
    }


    public void setProdImage2(String prodImage2) {
        this.prodImage2 = prodImage2;
    }


    public String getProdImage3() {
        return prodImage3;
    }


    public void setProdImage3(String prodImage3) {
        this.prodImage3 = prodImage3;
    }


    public String getProdImage4() {
        return prodImage4;
    }


    public void setProdImage4(String prodImage4) {
        this.prodImage4 = prodImage4;
    }


    public String getProdInfo() {
        return prodInfo;
    }


    public void setProdInfo(String prodInfo) {
        this.prodInfo = prodInfo;
    }


    public String getProdInDate() {
        return prodInDate;
    }


    public void setProdInDate(String prodInDate) {
        this.prodInDate = prodInDate;
    }

}
