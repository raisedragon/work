//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2014.01.11 时间 11:39:53 AM CST 
//


package com.winit.label.manager.impl.us.ups.model.confirm.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ups.xolt.codesamples.response.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MinimumRetrySeconds_QNAME = new QName("", "MinimumRetrySeconds");
    private final static QName _ShipmentIdentificationNumber_QNAME = new QName("", "ShipmentIdentificationNumber");
    private final static QName _SurCharges_QNAME = new QName("", "SurCharges");
    private final static QName _TransportationCharges_QNAME = new QName("", "TransportationCharges");
    private final static QName _Description_QNAME = new QName("", "Description");
    private final static QName _TotalCharges_QNAME = new QName("", "TotalCharges");
    private final static QName _ErrorLocationAttributeName_QNAME = new QName("", "ErrorLocationAttributeName");
    private final static QName _AccessorialCharges_QNAME = new QName("", "AccessorialCharges");
    private final static QName _ServiceOptionsCharges_QNAME = new QName("", "ServiceOptionsCharges");
    private final static QName _Code_QNAME = new QName("", "Code");
    private final static QName _CurrencyCode_QNAME = new QName("", "CurrencyCode");
    private final static QName _UnitOfMeasurement_QNAME = new QName("", "UnitOfMeasurement");
    private final static QName _ErrorDigest_QNAME = new QName("", "ErrorDigest");
    private final static QName _MonetaryValue_QNAME = new QName("", "MonetaryValue");
    private final static QName _ErrorSeverity_QNAME = new QName("", "ErrorSeverity");
    private final static QName _ResponseStatusCode_QNAME = new QName("", "ResponseStatusCode");
    private final static QName _ErrorDescription_QNAME = new QName("", "ErrorDescription");
    private final static QName _ResponseStatusDescription_QNAME = new QName("", "ResponseStatusDescription");
    private final static QName _ErrorLocationElementReference_QNAME = new QName("", "ErrorLocationElementReference");
    private final static QName _ErrorCode_QNAME = new QName("", "ErrorCode");
    private final static QName _ShipmentDigest_QNAME = new QName("", "ShipmentDigest");
    private final static QName _ErrorLocationElementName_QNAME = new QName("", "ErrorLocationElementName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ups.xolt.codesamples.response.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MonetaryType }
     * 
     */
    public MonetaryType createMonetaryType() {
        return new MonetaryType();
    }

    /**
     * Create an instance of {@link NegotiatedRates }
     * 
     */
    public NegotiatedRates createNegotiatedRates() {
        return new NegotiatedRates();
    }

    /**
     * Create an instance of {@link NetSummaryCharges }
     * 
     */
    public NetSummaryCharges createNetSummaryCharges() {
        return new NetSummaryCharges();
    }

    /**
     * Create an instance of {@link GrandTotal }
     * 
     */
    public GrandTotal createGrandTotal() {
        return new GrandTotal();
    }

    /**
     * Create an instance of {@link BillingWeight }
     * 
     */
    public BillingWeight createBillingWeight() {
        return new BillingWeight();
    }

    /**
     * Create an instance of {@link CodeType }
     * 
     */
    public CodeType createCodeType() {
        return new CodeType();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link TransactionReference }
     * 
     */
    public TransactionReference createTransactionReference() {
        return new TransactionReference();
    }

    /**
     * Create an instance of {@link Error }
     * 
     */
    public Error createError() {
        return new Error();
    }

    /**
     * Create an instance of {@link ErrorLocation }
     * 
     */
    public ErrorLocation createErrorLocation() {
        return new ErrorLocation();
    }

    /**
     * Create an instance of {@link ShipmentCharges }
     * 
     */
    public ShipmentCharges createShipmentCharges() {
        return new ShipmentCharges();
    }

    /**
     * Create an instance of {@link ShipmentConfirmResponse }
     * 
     */
    public ShipmentConfirmResponse createShipmentConfirmResponse() {
        return new ShipmentConfirmResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "MinimumRetrySeconds")
    public JAXBElement<String> createMinimumRetrySeconds(String value) {
        return new JAXBElement<String>(_MinimumRetrySeconds_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ShipmentIdentificationNumber")
    public JAXBElement<String> createShipmentIdentificationNumber(String value) {
        return new JAXBElement<String>(_ShipmentIdentificationNumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MonetaryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "SurCharges")
    public JAXBElement<MonetaryType> createSurCharges(MonetaryType value) {
        return new JAXBElement<MonetaryType>(_SurCharges_QNAME, MonetaryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MonetaryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "TransportationCharges")
    public JAXBElement<MonetaryType> createTransportationCharges(MonetaryType value) {
        return new JAXBElement<MonetaryType>(_TransportationCharges_QNAME, MonetaryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Description")
    public JAXBElement<String> createDescription(String value) {
        return new JAXBElement<String>(_Description_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MonetaryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "TotalCharges")
    public JAXBElement<MonetaryType> createTotalCharges(MonetaryType value) {
        return new JAXBElement<MonetaryType>(_TotalCharges_QNAME, MonetaryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ErrorLocationAttributeName")
    public JAXBElement<String> createErrorLocationAttributeName(String value) {
        return new JAXBElement<String>(_ErrorLocationAttributeName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MonetaryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "AccessorialCharges")
    public JAXBElement<MonetaryType> createAccessorialCharges(MonetaryType value) {
        return new JAXBElement<MonetaryType>(_AccessorialCharges_QNAME, MonetaryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MonetaryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ServiceOptionsCharges")
    public JAXBElement<MonetaryType> createServiceOptionsCharges(MonetaryType value) {
        return new JAXBElement<MonetaryType>(_ServiceOptionsCharges_QNAME, MonetaryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Code")
    public JAXBElement<String> createCode(String value) {
        return new JAXBElement<String>(_Code_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "CurrencyCode")
    public JAXBElement<String> createCurrencyCode(String value) {
        return new JAXBElement<String>(_CurrencyCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "UnitOfMeasurement")
    public JAXBElement<CodeType> createUnitOfMeasurement(CodeType value) {
        return new JAXBElement<CodeType>(_UnitOfMeasurement_QNAME, CodeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ErrorDigest")
    public JAXBElement<String> createErrorDigest(String value) {
        return new JAXBElement<String>(_ErrorDigest_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "MonetaryValue")
    public JAXBElement<String> createMonetaryValue(String value) {
        return new JAXBElement<String>(_MonetaryValue_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ErrorSeverity")
    public JAXBElement<String> createErrorSeverity(String value) {
        return new JAXBElement<String>(_ErrorSeverity_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ResponseStatusCode")
    public JAXBElement<String> createResponseStatusCode(String value) {
        return new JAXBElement<String>(_ResponseStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ErrorDescription")
    public JAXBElement<String> createErrorDescription(String value) {
        return new JAXBElement<String>(_ErrorDescription_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ResponseStatusDescription")
    public JAXBElement<String> createResponseStatusDescription(String value) {
        return new JAXBElement<String>(_ResponseStatusDescription_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ErrorLocationElementReference")
    public JAXBElement<String> createErrorLocationElementReference(String value) {
        return new JAXBElement<String>(_ErrorLocationElementReference_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ErrorCode")
    public JAXBElement<String> createErrorCode(String value) {
        return new JAXBElement<String>(_ErrorCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ShipmentDigest")
    public JAXBElement<String> createShipmentDigest(String value) {
        return new JAXBElement<String>(_ShipmentDigest_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ErrorLocationElementName")
    public JAXBElement<String> createErrorLocationElementName(String value) {
        return new JAXBElement<String>(_ErrorLocationElementName_QNAME, String.class, null, value);
    }

}
