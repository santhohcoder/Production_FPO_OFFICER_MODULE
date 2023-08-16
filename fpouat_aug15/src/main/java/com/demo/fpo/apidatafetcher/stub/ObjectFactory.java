
package com.demo.fpo.apidatafetcher.stub;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sample.datafetcher.stub package. 
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

    private final static QName _AnyType_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyType");
    private final static QName _AnyURI_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyURI");
    private final static QName _Base64Binary_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "base64Binary");
    private final static QName _Boolean_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "boolean");
    private final static QName _Byte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "byte");
    private final static QName _DateTime_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "dateTime");
    private final static QName _Decimal_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "decimal");
    private final static QName _Double_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "double");
    private final static QName _Float_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "float");
    private final static QName _Int_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "int");
    private final static QName _Long_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "long");
    private final static QName _QName_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "QName");
    private final static QName _Short_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "short");
    private final static QName _String_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "string");
    private final static QName _UnsignedByte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedByte");
    private final static QName _UnsignedInt_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedInt");
    private final static QName _UnsignedLong_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedLong");
    private final static QName _UnsignedShort_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedShort");
    private final static QName _Char_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "char");
    private final static QName _Duration_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "duration");
    private final static QName _Guid_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
    private final static QName _ArrayOfstring_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfstring");
    private final static QName _ArrayOfCDSView_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "ArrayOfCDSView");
    private final static QName _CDSView_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "CDSView");
    private final static QName _Response_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Response");
    private final static QName _CDSObject_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "CDSObject");
    private final static QName _ResponseResponseData_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Response.ResponseData");
    private final static QName _ArrayOfResponseResponseDataTax_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "ArrayOfResponse.ResponseData.Tax");
    private final static QName _ResponseResponseDataTax_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Response.ResponseData.Tax");
    private final static QName _Declaration_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Declaration");
    private final static QName _DeclarationDeclarationData_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Declaration.DeclarationData");
    private final static QName _ArrayOfDeclarationDeclarationDataContentPiece_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "ArrayOfDeclaration.DeclarationData.ContentPiece");
    private final static QName _DeclarationDeclarationDataContentPiece_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Declaration.DeclarationData.ContentPiece");
    private final static QName _ArrayOfDeclarationDeclarationDataDocument_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "ArrayOfDeclaration.DeclarationData.Document");
    private final static QName _DeclarationDeclarationDataDocument_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Declaration.DeclarationData.Document");
    private final static QName _MailObject_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "MailObject");
    private final static QName _ArrayOfHSLookupInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "ArrayOfHSLookupInfo");
    private final static QName _HSLookupInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "HSLookupInfo");
    private final static QName _WatchdogInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "WatchdogInfo");
    private final static QName _WatchdogInfoOperationType_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "WatchdogInfo.OperationType");
    private final static QName _Entity_QNAME = new QName("http://schemas.datacontract.org/2004/07/PTC.BusinessLayer.Core", "Entity");
    private final static QName _ArrayOfRestrictionProhibition_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", "ArrayOfRestrictionProhibition");
    private final static QName _RestrictionProhibition_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", "RestrictionProhibition");
    private final static QName _ArrayOfPostalService_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "ArrayOfPostalService");
    private final static QName _PostalService_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "PostalService");
    private final static QName _PostalServiceServiceDetails_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "PostalService.ServiceDetails");
    private final static QName _PostalServiceServiceDetailsServiceOptions_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "PostalService.ServiceDetails.ServiceOptions");
    private final static QName _PostalServiceServiceDetailsServicePhysicalLimits_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "PostalService.ServiceDetails.ServicePhysicalLimits");
    private final static QName _ArrayOfPostalServiceServiceDetailsServiceRate_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "ArrayOfPostalService.ServiceDetails.ServiceRate");
    private final static QName _PostalServiceServiceDetailsServiceRate_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "PostalService.ServiceDetails.ServiceRate");
    private final static QName _ArrayOfPostalServiceServiceDetailsServiceStandards_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "ArrayOfPostalService.ServiceDetails.ServiceStandards");
    private final static QName _PostalServiceServiceDetailsServiceStandards_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "PostalService.ServiceDetails.ServiceStandards");
    private final static QName _ArrayOfMarketedGood_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "ArrayOfMarketedGood");
    private final static QName _MarketedGood_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "MarketedGood");
    private final static QName _MarketedGoodMarketedGoodDetails_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "MarketedGood.MarketedGoodDetails");
    private final static QName _Value_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Base", "Value");
    private final static QName _ConvertCurrencyOrganizationCd_QNAME = new QName("http://tempuri.org/", "organizationCd");
    private final static QName _ConvertCurrencyFromCurrencyCd_QNAME = new QName("http://tempuri.org/", "fromCurrencyCd");
    private final static QName _ConvertCurrencyToCurrencyCd_QNAME = new QName("http://tempuri.org/", "toCurrencyCd");
    private final static QName _GetDecisionNameAndCategoryFromCodeDecisionCode_QNAME = new QName("http://tempuri.org/", "decisionCode");
    private final static QName _GetDecisionNameAndCategoryFromCodeResponseGetDecisionNameAndCategoryFromCodeResult_QNAME = new QName("http://tempuri.org/", "GetDecisionNameAndCategoryFromCodeResult");
    private final static QName _LoadPostalOrgCd_QNAME = new QName("http://tempuri.org/", "postalOrgCd");
    private final static QName _LoadCustomsOrgCd_QNAME = new QName("http://tempuri.org/", "customsOrgCd");
    private final static QName _LoadOrganizationTypeCd_QNAME = new QName("http://tempuri.org/", "organizationTypeCd");
    private final static QName _LoadId_QNAME = new QName("http://tempuri.org/", "id");
    private final static QName _LoadFlow_QNAME = new QName("http://tempuri.org/", "flow");
    private final static QName _LoadPartnerPostalOrgCd_QNAME = new QName("http://tempuri.org/", "partnerPostalOrgCd");
    private final static QName _LoadPartCountryCd_QNAME = new QName("http://tempuri.org/", "partCountryCd");
    private final static QName _LoadDtFrom_QNAME = new QName("http://tempuri.org/", "dtFrom");
    private final static QName _LoadDtTo_QNAME = new QName("http://tempuri.org/", "dtTo");
    private final static QName _LoadZipFrom_QNAME = new QName("http://tempuri.org/", "zipFrom");
    private final static QName _LoadZipTo_QNAME = new QName("http://tempuri.org/", "zipTo");
    private final static QName _LoadMailClassCd_QNAME = new QName("http://tempuri.org/", "mailClassCd");
    private final static QName _LoadRecordsLimit_QNAME = new QName("http://tempuri.org/", "recordsLimit");
    private final static QName _LoadResponseLoadResult_QNAME = new QName("http://tempuri.org/", "LoadResult");
    private final static QName _LoadByMailStateMailStateCd_QNAME = new QName("http://tempuri.org/", "mailStateCd");
    private final static QName _LoadByMailStateResponseLoadByMailStateResult_QNAME = new QName("http://tempuri.org/", "LoadByMailStateResult");
    private final static QName _GetCustomsStatusUserCd_QNAME = new QName("http://tempuri.org/", "userCd");
    private final static QName _GetCustomsStatusResponseGetCustomsStatusResult_QNAME = new QName("http://tempuri.org/", "GetCustomsStatusResult");
    private final static QName _CreateNewDeclarationItemId_QNAME = new QName("http://tempuri.org/", "itemId");
    private final static QName _CreateNewDeclarationMailFlow_QNAME = new QName("http://tempuri.org/", "mailFlow");
    private final static QName _CreateNewDeclarationResponseCreateNewDeclarationResult_QNAME = new QName("http://tempuri.org/", "CreateNewDeclarationResult");
    private final static QName _CreateNewResponseResponseCreateNewResponseResult_QNAME = new QName("http://tempuri.org/", "CreateNewResponseResult");
    private final static QName _StoreDeclarationMailObject_QNAME = new QName("http://tempuri.org/", "mailObject");
    private final static QName _StoreDeclarationDecl_QNAME = new QName("http://tempuri.org/", "decl");
    private final static QName _StoreResponseResp_QNAME = new QName("http://tempuri.org/", "resp");
    private final static QName _CreateOrUpdateDeclarationsCdsViews_QNAME = new QName("http://tempuri.org/", "cdsViews");
    private final static QName _ConvertAnonymousToCDSDeclarationAnonymousDeclarationId_QNAME = new QName("http://tempuri.org/", "anonymousDeclarationId");
    private final static QName _ConvertAnonymousToCDSDeclarationInternationalItemId_QNAME = new QName("http://tempuri.org/", "internationalItemId");
    private final static QName _GetHSCodesGoodDescription_QNAME = new QName("http://tempuri.org/", "goodDescription");
    private final static QName _GetHSCodesResponseGetHSCodesResult_QNAME = new QName("http://tempuri.org/", "GetHSCodesResult");
    private final static QName _GetRestrictionProhibitionByDescriptionDestinationPostalOrgCd_QNAME = new QName("http://tempuri.org/", "destinationPostalOrgCd");
    private final static QName _GetRestrictionProhibitionByDescriptionSendingCountryCd_QNAME = new QName("http://tempuri.org/", "sendingCountryCd");
    private final static QName _GetRestrictionProhibitionByDescriptionResponseGetRestrictionProhibitionByDescriptionResult_QNAME = new QName("http://tempuri.org/", "GetRestrictionProhibitionByDescriptionResult");
    private final static QName _GetRestrictionProhibitionByHSGoodHSCode_QNAME = new QName("http://tempuri.org/", "goodHSCode");
    private final static QName _GetRestrictionProhibitionByHSResponseGetRestrictionProhibitionByHSResult_QNAME = new QName("http://tempuri.org/", "GetRestrictionProhibitionByHSResult");
    private final static QName _GetWatchdogItemInfoResponseGetWatchdogItemInfoResult_QNAME = new QName("http://tempuri.org/", "GetWatchdogItemInfoResult");
    private final static QName _SetWatchdogItemInfoMailClass_QNAME = new QName("http://tempuri.org/", "mailClass");
    private final static QName _SetWatchdogItemInfoPostingDate_QNAME = new QName("http://tempuri.org/", "postingDate");
    private final static QName _SetWatchdogItemInfoWatchdogInfo_QNAME = new QName("http://tempuri.org/", "watchdogInfo");
    private final static QName _LoadPostalServicesOrigOrganizationCd_QNAME = new QName("http://tempuri.org/", "origOrganizationCd");
    private final static QName _LoadPostalServicesDestOrganizationCd_QNAME = new QName("http://tempuri.org/", "destOrganizationCd");
    private final static QName _LoadPostalServicesResponseLoadPostalServicesResult_QNAME = new QName("http://tempuri.org/", "LoadPostalServicesResult");
    private final static QName _StorePostalServicePostalService_QNAME = new QName("http://tempuri.org/", "postalService");
    private final static QName _LoadMarketedGoodSellerOrganizationCd_QNAME = new QName("http://tempuri.org/", "sellerOrganizationCd");
    private final static QName _LoadMarketedGoodBuyerOrganizationCd_QNAME = new QName("http://tempuri.org/", "buyerOrganizationCd");
    private final static QName _LoadMarketedGoodResponseLoadMarketedGoodResult_QNAME = new QName("http://tempuri.org/", "LoadMarketedGoodResult");
    private final static QName _StoreMarketedGoodMarketedGood_QNAME = new QName("http://tempuri.org/", "marketedGood");
    private final static QName _PrintDocCdsView_QNAME = new QName("http://tempuri.org/", "cdsView");
    private final static QName _PrintDocResponsePrintDocResult_QNAME = new QName("http://tempuri.org/", "PrintDocResult");
    private final static QName _ValueAmount_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Base", "Amount");
    private final static QName _ValueCurrency_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Base", "Currency");
    private final static QName _MarketedGoodMarketedGoodDetailsBuyerOrganizations_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "BuyerOrganizations");
    private final static QName _MarketedGoodMarketedGoodDetailsGoodDetails_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "GoodDetails");
    private final static QName _MarketedGoodMarketedGoodDetailsHSCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "HSCode");
    private final static QName _MarketedGoodMarketedGoodDetailsHeightCm_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "HeightCm");
    private final static QName _MarketedGoodMarketedGoodDetailsLengthCm_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "LengthCm");
    private final static QName _MarketedGoodMarketedGoodDetailsPicture_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "Picture");
    private final static QName _MarketedGoodMarketedGoodDetailsPrice_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "Price");
    private final static QName _MarketedGoodMarketedGoodDetailsSellerAddress_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "SellerAddress");
    private final static QName _MarketedGoodMarketedGoodDetailsSellerCity_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "SellerCity");
    private final static QName _MarketedGoodMarketedGoodDetailsSellerCountry_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "SellerCountry");
    private final static QName _MarketedGoodMarketedGoodDetailsSellerName_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "SellerName");
    private final static QName _MarketedGoodMarketedGoodDetailsSellerPhone_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "SellerPhone");
    private final static QName _MarketedGoodMarketedGoodDetailsSellerRefId_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "SellerRefId");
    private final static QName _MarketedGoodMarketedGoodDetailsSellerZip_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "SellerZip");
    private final static QName _MarketedGoodMarketedGoodDetailsServiceDescription_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "ServiceDescription");
    private final static QName _MarketedGoodMarketedGoodDetailsStock_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "Stock");
    private final static QName _MarketedGoodMarketedGoodDetailsWeightKg_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "WeightKg");
    private final static QName _MarketedGoodMarketedGoodDetailsWidthCm_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "WidthCm");
    private final static QName _PostalServiceServiceDetailsServiceRateItemMaxWeight_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "ItemMaxWeight");
    private final static QName _PostalServiceServiceDetailsServiceRateRateCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "RateCode");
    private final static QName _PostalServiceServiceDetailsServiceRateRatePeKg_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "RatePeKg");
    private final static QName _PostalServiceServiceDetailsServicePhysicalLimitsMaxHeight_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "MaxHeight");
    private final static QName _PostalServiceServiceDetailsServicePhysicalLimitsMaxLenght_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "MaxLenght");
    private final static QName _PostalServiceServiceDetailsServicePhysicalLimitsMaxWeight_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "MaxWeight");
    private final static QName _PostalServiceServiceDetailsServicePhysicalLimitsMaxWidth_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "MaxWidth");
    private final static QName _PostalServiceServiceDetailsServicePhysicalLimitsMinHeight_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "MinHeight");
    private final static QName _PostalServiceServiceDetailsServicePhysicalLimitsMinLenght_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "MinLenght");
    private final static QName _PostalServiceServiceDetailsServicePhysicalLimitsMinWeight_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "MinWeight");
    private final static QName _PostalServiceServiceDetailsServicePhysicalLimitsMinWidth_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "MinWidth");
    private final static QName _PostalServiceServiceDetailsServiceOptionsHandlingClass_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "HandlingClass");
    private final static QName _PostalServiceServiceDetailsDestCountry_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "DestCountry");
    private final static QName _PostalServiceServiceDetailsDestZoneNumber_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "DestZoneNumber");
    private final static QName _PostalServiceServiceDetailsMailSubclasses_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "MailSubclasses");
    private final static QName _PostalServiceServiceDetailsOptions_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "Options");
    private final static QName _PostalServiceServiceDetailsOrigCountry_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "OrigCountry");
    private final static QName _PostalServiceServiceDetailsOrigZoneNumber_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "OrigZoneNumber");
    private final static QName _PostalServiceServiceDetailsPhysicalLimits_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "PhysicalLimits");
    private final static QName _PostalServiceServiceDetailsRates_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "Rates");
    private final static QName _PostalServiceServiceDetailsStandards_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "Standards");
    private final static QName _RestrictionProhibitionAdditionalDocFileName_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", "AdditionalDocFileName");
    private final static QName _RestrictionProhibitionDescription_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", "Description");
    private final static QName _RestrictionProhibitionFromHSCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", "FromHSCode");
    private final static QName _RestrictionProhibitionHSCodeList_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", "HSCodeList");
    private final static QName _RestrictionProhibitionLanguageCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", "LanguageCd");
    private final static QName _RestrictionProhibitionOrganizationCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", "OrganizationCd");
    private final static QName _RestrictionProhibitionPCategory_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", "PCategory");
    private final static QName _RestrictionProhibitionRestrictedCountries_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", "RestrictedCountries");
    private final static QName _RestrictionProhibitionRestrictionNote_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", "RestrictionNote");
    private final static QName _RestrictionProhibitionToHSCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", "ToHSCode");
    private final static QName _RestrictionProhibitionWebsiteInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", "WebsiteInfo");
    private final static QName _HSLookupInfoHSCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "HSCode");
    private final static QName _HSLookupInfoObservations_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Observations");
    private final static QName _DeclarationDeclarationDataDocumentDocumentId_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "DocumentId");
    private final static QName _DeclarationDeclarationDataDocumentDocumentName_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "DocumentName");
    private final static QName _DeclarationDeclarationDataDocumentDocumentType_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "DocumentType");
    private final static QName _DeclarationDeclarationDataContentPieceAmount_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Amount");
    private final static QName _DeclarationDeclarationDataContentPieceCurrencyCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "CurrencyCd");
    private final static QName _DeclarationDeclarationDataContentPieceDescription_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Description");
    private final static QName _DeclarationDeclarationDataContentPieceExtraFieldNames_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "ExtraFieldNames");
    private final static QName _DeclarationDeclarationDataContentPieceExtraFieldValues_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "ExtraFieldValues");
    private final static QName _DeclarationDeclarationDataContentPieceHS_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "HS");
    private final static QName _DeclarationDeclarationDataContentPieceImportRestrictions_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "ImportRestrictions");
    private final static QName _DeclarationDeclarationDataContentPieceImportRestrictionsNotes_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "ImportRestrictionsNotes");
    private final static QName _DeclarationDeclarationDataContentPieceIndex_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Index");
    private final static QName _DeclarationDeclarationDataContentPieceNetWeight_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "NetWeight");
    private final static QName _DeclarationDeclarationDataContentPieceNumber_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Number");
    private final static QName _DeclarationDeclarationDataContentPieceOrigCountryCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "OrigCountryCd");
    private final static QName _DeclarationDeclarationDataContentPieceRevisedDescription_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "RevisedDescription");
    private final static QName _DeclarationDeclarationDataContentPieceRevisedHS_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "RevisedHS");
    private final static QName _DeclarationDeclarationDataContentPieces_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "ContentPieces");
    private final static QName _DeclarationDeclarationDataDocuments_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Documents");
    private final static QName _DeclarationDeclarationDataGrossWeight_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "GrossWeight");
    private final static QName _DeclarationDeclarationDataHandlingClassCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "HandlingClassCd");
    private final static QName _DeclarationDeclarationDataInsuredValue_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "InsuredValue");
    private final static QName _DeclarationDeclarationDataInsuredValueCurrencyCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "InsuredValueCurrencyCd");
    private final static QName _DeclarationDeclarationDataMaxIndex_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "MaxIndex");
    private final static QName _DeclarationDeclarationDataNatureTypeCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "NatureTypeCd");
    private final static QName _DeclarationDeclarationDataPackageImageLocalPath_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "PackageImageLocalPath");
    private final static QName _DeclarationDeclarationDataPackageImageSourceInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "PackageImageSourceInfo");
    private final static QName _DeclarationDeclarationDataPostage_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Postage");
    private final static QName _DeclarationDeclarationDataPostageCurrencyCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "PostageCurrencyCd");
    private final static QName _DeclarationDeclarationDataRecipientAddressLine1_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "RecipientAddressLine1");
    private final static QName _DeclarationDeclarationDataRecipientAddressLine2_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "RecipientAddressLine2");
    private final static QName _DeclarationDeclarationDataRecipientCity_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "RecipientCity");
    private final static QName _DeclarationDeclarationDataRecipientCountryCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "RecipientCountryCd");
    private final static QName _DeclarationDeclarationDataRecipientEmail_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "RecipientEmail");
    private final static QName _DeclarationDeclarationDataRecipientFax_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "RecipientFax");
    private final static QName _DeclarationDeclarationDataRecipientFirstName_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "RecipientFirstName");
    private final static QName _DeclarationDeclarationDataRecipientIdRef_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "RecipientIdRef");
    private final static QName _DeclarationDeclarationDataRecipientLastName_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "RecipientLastName");
    private final static QName _DeclarationDeclarationDataRecipientName_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "RecipientName");
    private final static QName _DeclarationDeclarationDataRecipientState_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "RecipientState");
    private final static QName _DeclarationDeclarationDataRecipientTelephone_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "RecipientTelephone");
    private final static QName _DeclarationDeclarationDataRecipientZIP_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "RecipientZIP");
    private final static QName _DeclarationDeclarationDataSenderAddressLine1_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "SenderAddressLine1");
    private final static QName _DeclarationDeclarationDataSenderAddressLine2_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "SenderAddressLine2");
    private final static QName _DeclarationDeclarationDataSenderCity_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "SenderCity");
    private final static QName _DeclarationDeclarationDataSenderCountryCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "SenderCountryCd");
    private final static QName _DeclarationDeclarationDataSenderEmail_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "SenderEmail");
    private final static QName _DeclarationDeclarationDataSenderFirstName_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "SenderFirstName");
    private final static QName _DeclarationDeclarationDataSenderIdRef_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "SenderIdRef");
    private final static QName _DeclarationDeclarationDataSenderLastName_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "SenderLastName");
    private final static QName _DeclarationDeclarationDataSenderName_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "SenderName");
    private final static QName _DeclarationDeclarationDataSenderState_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "SenderState");
    private final static QName _DeclarationDeclarationDataSenderTelephone_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "SenderTelephone");
    private final static QName _DeclarationDeclarationDataSenderZIP_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "SenderZIP");
    private final static QName _DeclarationDeclarationDataTransportDate_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "TransportDate");
    private final static QName _DeclarationDeclarationDataTransportMode_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "TransportMode");
    private final static QName _ResponseResponseDataTaxRate_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Rate");
    private final static QName _ResponseResponseDataTaxType_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Type");
    private final static QName _ResponseResponseDataClearanceDt_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "ClearanceDt");
    private final static QName _ResponseResponseDataDecisionCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "DecisionCd");
    private final static QName _ResponseResponseDataDecisionReasonCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "DecisionReasonCd");
    private final static QName _ResponseResponseDataDecisionReasonNm_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "DecisionReasonNm");
    private final static QName _ResponseResponseDataTaxes_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Taxes");
    private final static QName _ResponseResponseDataTotalFee_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "TotalFee");
    private final static QName _ResponseResponseDataTotalFeeCurrencyCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "TotalFeeCurrencyCd");
    private final static QName _CDSObjectCustOrganizationCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "CustOrganizationCd");
    private final static QName _CDSObjectPId_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "PId");
    private final static QName _CDSObjectPostOrganizationCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "PostOrganizationCd");
    private final static QName _CDSObjectXMLData_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "XMLData");
    private final static QName _MarketedGoodDescription_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "Description");
    private final static QName _MarketedGoodDetails_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "Details");
    private final static QName _MarketedGoodReferenceId_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "ReferenceId");
    private final static QName _MarketedGoodSellerOrganizationCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "SellerOrganizationCd");
    private final static QName _MarketedGoodValidFrom_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "ValidFrom");
    private final static QName _MarketedGoodValidTo_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "ValidTo");
    private final static QName _PostalServiceDestOrganizationCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "DestOrganizationCd");
    private final static QName _PostalServiceMailCategoryCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "MailCategoryCd");
    private final static QName _PostalServiceMailClassCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "MailClassCd");
    private final static QName _PostalServiceOrigOrganizationCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "OrigOrganizationCd");
    private final static QName _PostalServiceServiceId_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "ServiceId");
    private final static QName _PostalServiceServiceNm_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", "ServiceNm");
    private final static QName _WatchdogInfoRemarks_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Remarks");
    private final static QName _MailObjectDestPostOrgCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "DestPostOrgCd");
    private final static QName _MailObjectId_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Id");
    private final static QName _MailObjectLocalId_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "LocalId");
    private final static QName _MailObjectLocalId2_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "LocalId2");
    private final static QName _MailObjectMailCategoryCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "MailCategoryCd");
    private final static QName _MailObjectMailClassCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "MailClassCd");
    private final static QName _MailObjectMailStateCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "MailStateCd");
    private final static QName _MailObjectMailStateRemarks_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "MailStateRemarks");
    private final static QName _MailObjectOrigPostOrgCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "OrigPostOrgCd");
    private final static QName _MailObjectTypeCd_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "TypeCd");
    private final static QName _ResponseData_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "Data");
    private final static QName _CDSViewDCResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", "DCResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sample.datafetcher.stub
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConvertCurrency }
     * 
     */
    public ConvertCurrency createConvertCurrency() {
        return new ConvertCurrency();
    }

    /**
     * Create an instance of {@link ConvertCurrencyResponse }
     * 
     */
    public ConvertCurrencyResponse createConvertCurrencyResponse() {
        return new ConvertCurrencyResponse();
    }

    /**
     * Create an instance of {@link GetDecisionNameAndCategoryFromCode }
     * 
     */
    public GetDecisionNameAndCategoryFromCode createGetDecisionNameAndCategoryFromCode() {
        return new GetDecisionNameAndCategoryFromCode();
    }

    /**
     * Create an instance of {@link GetDecisionNameAndCategoryFromCodeResponse }
     * 
     */
    public GetDecisionNameAndCategoryFromCodeResponse createGetDecisionNameAndCategoryFromCodeResponse() {
        return new GetDecisionNameAndCategoryFromCodeResponse();
    }

    /**
     * Create an instance of {@link ArrayOfstring }
     * 
     */
    public ArrayOfstring createArrayOfstring() {
        return new ArrayOfstring();
    }

    /**
     * Create an instance of {@link Load }
     * 
     */
    public Load createLoad() {
        return new Load();
    }

    /**
     * Create an instance of {@link LoadResponse }
     * 
     */
    public LoadResponse createLoadResponse() {
        return new LoadResponse();
    }

    /**
     * Create an instance of {@link ArrayOfCDSView }
     * 
     */
    public ArrayOfCDSView createArrayOfCDSView() {
        return new ArrayOfCDSView();
    }

    /**
     * Create an instance of {@link LoadByMailState }
     * 
     */
    public LoadByMailState createLoadByMailState() {
        return new LoadByMailState();
    }

    /**
     * Create an instance of {@link LoadByMailStateResponse }
     * 
     */
    public LoadByMailStateResponse createLoadByMailStateResponse() {
        return new LoadByMailStateResponse();
    }

    /**
     * Create an instance of {@link GetCustomsStatus }
     * 
     */
    public GetCustomsStatus createGetCustomsStatus() {
        return new GetCustomsStatus();
    }

    /**
     * Create an instance of {@link GetCustomsStatusResponse }
     * 
     */
    public GetCustomsStatusResponse createGetCustomsStatusResponse() {
        return new GetCustomsStatusResponse();
    }

    /**
     * Create an instance of {@link CDSView }
     * 
     */
    public CDSView createCDSView() {
        return new CDSView();
    }

    /**
     * Create an instance of {@link CreateNewDeclaration }
     * 
     */
    public CreateNewDeclaration createCreateNewDeclaration() {
        return new CreateNewDeclaration();
    }

    /**
     * Create an instance of {@link CreateNewDeclarationResponse }
     * 
     */
    public CreateNewDeclarationResponse createCreateNewDeclarationResponse() {
        return new CreateNewDeclarationResponse();
    }

    /**
     * Create an instance of {@link Declaration }
     * 
     */
    public Declaration createDeclaration() {
        return new Declaration();
    }

    /**
     * Create an instance of {@link CreateNewResponse }
     * 
     */
    public CreateNewResponse createCreateNewResponse() {
        return new CreateNewResponse();
    }

    /**
     * Create an instance of {@link CreateNewResponseResponse }
     * 
     */
    public CreateNewResponseResponse createCreateNewResponseResponse() {
        return new CreateNewResponseResponse();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link StoreDeclaration }
     * 
     */
    public StoreDeclaration createStoreDeclaration() {
        return new StoreDeclaration();
    }

    /**
     * Create an instance of {@link MailObject }
     * 
     */
    public MailObject createMailObject() {
        return new MailObject();
    }

    /**
     * Create an instance of {@link StoreDeclarationResponse }
     * 
     */
    public StoreDeclarationResponse createStoreDeclarationResponse() {
        return new StoreDeclarationResponse();
    }

    /**
     * Create an instance of {@link StoreResponse }
     * 
     */
    public StoreResponse createStoreResponse() {
        return new StoreResponse();
    }

    /**
     * Create an instance of {@link StoreResponseResponse }
     * 
     */
    public StoreResponseResponse createStoreResponseResponse() {
        return new StoreResponseResponse();
    }

    /**
     * Create an instance of {@link CreateOrUpdateDeclarations }
     * 
     */
    public CreateOrUpdateDeclarations createCreateOrUpdateDeclarations() {
        return new CreateOrUpdateDeclarations();
    }

    /**
     * Create an instance of {@link CreateOrUpdateDeclarationsResponse }
     * 
     */
    public CreateOrUpdateDeclarationsResponse createCreateOrUpdateDeclarationsResponse() {
        return new CreateOrUpdateDeclarationsResponse();
    }

    /**
     * Create an instance of {@link CreateOrUpdateResponses }
     * 
     */
    public CreateOrUpdateResponses createCreateOrUpdateResponses() {
        return new CreateOrUpdateResponses();
    }

    /**
     * Create an instance of {@link CreateOrUpdateResponsesResponse }
     * 
     */
    public CreateOrUpdateResponsesResponse createCreateOrUpdateResponsesResponse() {
        return new CreateOrUpdateResponsesResponse();
    }

    /**
     * Create an instance of {@link UpdateMailState }
     * 
     */
    public UpdateMailState createUpdateMailState() {
        return new UpdateMailState();
    }

    /**
     * Create an instance of {@link UpdateMailStateResponse }
     * 
     */
    public UpdateMailStateResponse createUpdateMailStateResponse() {
        return new UpdateMailStateResponse();
    }

    /**
     * Create an instance of {@link ConvertAnonymousToCDSDeclaration }
     * 
     */
    public ConvertAnonymousToCDSDeclaration createConvertAnonymousToCDSDeclaration() {
        return new ConvertAnonymousToCDSDeclaration();
    }

    /**
     * Create an instance of {@link ConvertAnonymousToCDSDeclarationResponse }
     * 
     */
    public ConvertAnonymousToCDSDeclarationResponse createConvertAnonymousToCDSDeclarationResponse() {
        return new ConvertAnonymousToCDSDeclarationResponse();
    }

    /**
     * Create an instance of {@link GetHSCodes }
     * 
     */
    public GetHSCodes createGetHSCodes() {
        return new GetHSCodes();
    }

    /**
     * Create an instance of {@link GetHSCodesResponse }
     * 
     */
    public GetHSCodesResponse createGetHSCodesResponse() {
        return new GetHSCodesResponse();
    }

    /**
     * Create an instance of {@link ArrayOfHSLookupInfo }
     * 
     */
    public ArrayOfHSLookupInfo createArrayOfHSLookupInfo() {
        return new ArrayOfHSLookupInfo();
    }

    /**
     * Create an instance of {@link GetRestrictionProhibitionByDescription }
     * 
     */
    public GetRestrictionProhibitionByDescription createGetRestrictionProhibitionByDescription() {
        return new GetRestrictionProhibitionByDescription();
    }

    /**
     * Create an instance of {@link GetRestrictionProhibitionByDescriptionResponse }
     * 
     */
    public GetRestrictionProhibitionByDescriptionResponse createGetRestrictionProhibitionByDescriptionResponse() {
        return new GetRestrictionProhibitionByDescriptionResponse();
    }

    /**
     * Create an instance of {@link ArrayOfRestrictionProhibition }
     * 
     */
    public ArrayOfRestrictionProhibition createArrayOfRestrictionProhibition() {
        return new ArrayOfRestrictionProhibition();
    }

    /**
     * Create an instance of {@link GetRestrictionProhibitionByHS }
     * 
     */
    public GetRestrictionProhibitionByHS createGetRestrictionProhibitionByHS() {
        return new GetRestrictionProhibitionByHS();
    }

    /**
     * Create an instance of {@link GetRestrictionProhibitionByHSResponse }
     * 
     */
    public GetRestrictionProhibitionByHSResponse createGetRestrictionProhibitionByHSResponse() {
        return new GetRestrictionProhibitionByHSResponse();
    }

    /**
     * Create an instance of {@link GetWatchdogItemInfo }
     * 
     */
    public GetWatchdogItemInfo createGetWatchdogItemInfo() {
        return new GetWatchdogItemInfo();
    }

    /**
     * Create an instance of {@link GetWatchdogItemInfoResponse }
     * 
     */
    public GetWatchdogItemInfoResponse createGetWatchdogItemInfoResponse() {
        return new GetWatchdogItemInfoResponse();
    }

    /**
     * Create an instance of {@link WatchdogInfo }
     * 
     */
    public WatchdogInfo createWatchdogInfo() {
        return new WatchdogInfo();
    }

    /**
     * Create an instance of {@link SetWatchdogItemInfo }
     * 
     */
    public SetWatchdogItemInfo createSetWatchdogItemInfo() {
        return new SetWatchdogItemInfo();
    }

    /**
     * Create an instance of {@link SetWatchdogItemInfoResponse }
     * 
     */
    public SetWatchdogItemInfoResponse createSetWatchdogItemInfoResponse() {
        return new SetWatchdogItemInfoResponse();
    }

    /**
     * Create an instance of {@link LoadPostalServices }
     * 
     */
    public LoadPostalServices createLoadPostalServices() {
        return new LoadPostalServices();
    }

    /**
     * Create an instance of {@link LoadPostalServicesResponse }
     * 
     */
    public LoadPostalServicesResponse createLoadPostalServicesResponse() {
        return new LoadPostalServicesResponse();
    }

    /**
     * Create an instance of {@link ArrayOfPostalService }
     * 
     */
    public ArrayOfPostalService createArrayOfPostalService() {
        return new ArrayOfPostalService();
    }

    /**
     * Create an instance of {@link StorePostalService }
     * 
     */
    public StorePostalService createStorePostalService() {
        return new StorePostalService();
    }

    /**
     * Create an instance of {@link PostalService }
     * 
     */
    public PostalService createPostalService() {
        return new PostalService();
    }

    /**
     * Create an instance of {@link StorePostalServiceResponse }
     * 
     */
    public StorePostalServiceResponse createStorePostalServiceResponse() {
        return new StorePostalServiceResponse();
    }

    /**
     * Create an instance of {@link LoadMarketedGood }
     * 
     */
    public LoadMarketedGood createLoadMarketedGood() {
        return new LoadMarketedGood();
    }

    /**
     * Create an instance of {@link LoadMarketedGoodResponse }
     * 
     */
    public LoadMarketedGoodResponse createLoadMarketedGoodResponse() {
        return new LoadMarketedGoodResponse();
    }

    /**
     * Create an instance of {@link ArrayOfMarketedGood }
     * 
     */
    public ArrayOfMarketedGood createArrayOfMarketedGood() {
        return new ArrayOfMarketedGood();
    }

    /**
     * Create an instance of {@link StoreMarketedGood }
     * 
     */
    public StoreMarketedGood createStoreMarketedGood() {
        return new StoreMarketedGood();
    }

    /**
     * Create an instance of {@link MarketedGood }
     * 
     */
    public MarketedGood createMarketedGood() {
        return new MarketedGood();
    }

    /**
     * Create an instance of {@link StoreMarketedGoodResponse }
     * 
     */
    public StoreMarketedGoodResponse createStoreMarketedGoodResponse() {
        return new StoreMarketedGoodResponse();
    }

    /**
     * Create an instance of {@link PrintDoc }
     * 
     */
    public PrintDoc createPrintDoc() {
        return new PrintDoc();
    }

    /**
     * Create an instance of {@link PrintDocResponse }
     * 
     */
    public PrintDocResponse createPrintDocResponse() {
        return new PrintDocResponse();
    }

    /**
     * Create an instance of {@link CDSObject }
     * 
     */
    public CDSObject createCDSObject() {
        return new CDSObject();
    }

    /**
     * Create an instance of {@link ResponseResponseData }
     * 
     */
    public ResponseResponseData createResponseResponseData() {
        return new ResponseResponseData();
    }

    /**
     * Create an instance of {@link ArrayOfResponseResponseDataTax }
     * 
     */
    public ArrayOfResponseResponseDataTax createArrayOfResponseResponseDataTax() {
        return new ArrayOfResponseResponseDataTax();
    }

    /**
     * Create an instance of {@link ResponseResponseDataTax }
     * 
     */
    public ResponseResponseDataTax createResponseResponseDataTax() {
        return new ResponseResponseDataTax();
    }

    /**
     * Create an instance of {@link DeclarationDeclarationData }
     * 
     */
    public DeclarationDeclarationData createDeclarationDeclarationData() {
        return new DeclarationDeclarationData();
    }

    /**
     * Create an instance of {@link ArrayOfDeclarationDeclarationDataContentPiece }
     * 
     */
    public ArrayOfDeclarationDeclarationDataContentPiece createArrayOfDeclarationDeclarationDataContentPiece() {
        return new ArrayOfDeclarationDeclarationDataContentPiece();
    }

    /**
     * Create an instance of {@link DeclarationDeclarationDataContentPiece }
     * 
     */
    public DeclarationDeclarationDataContentPiece createDeclarationDeclarationDataContentPiece() {
        return new DeclarationDeclarationDataContentPiece();
    }

    /**
     * Create an instance of {@link ArrayOfDeclarationDeclarationDataDocument }
     * 
     */
    public ArrayOfDeclarationDeclarationDataDocument createArrayOfDeclarationDeclarationDataDocument() {
        return new ArrayOfDeclarationDeclarationDataDocument();
    }

    /**
     * Create an instance of {@link DeclarationDeclarationDataDocument }
     * 
     */
    public DeclarationDeclarationDataDocument createDeclarationDeclarationDataDocument() {
        return new DeclarationDeclarationDataDocument();
    }

    /**
     * Create an instance of {@link HSLookupInfo }
     * 
     */
    public HSLookupInfo createHSLookupInfo() {
        return new HSLookupInfo();
    }

    /**
     * Create an instance of {@link Entity }
     * 
     */
    public Entity createEntity() {
        return new Entity();
    }

    /**
     * Create an instance of {@link RestrictionProhibition }
     * 
     */
    public RestrictionProhibition createRestrictionProhibition() {
        return new RestrictionProhibition();
    }

    /**
     * Create an instance of {@link PostalServiceServiceDetails }
     * 
     */
    public PostalServiceServiceDetails createPostalServiceServiceDetails() {
        return new PostalServiceServiceDetails();
    }

    /**
     * Create an instance of {@link PostalServiceServiceDetailsServiceOptions }
     * 
     */
    public PostalServiceServiceDetailsServiceOptions createPostalServiceServiceDetailsServiceOptions() {
        return new PostalServiceServiceDetailsServiceOptions();
    }

    /**
     * Create an instance of {@link PostalServiceServiceDetailsServicePhysicalLimits }
     * 
     */
    public PostalServiceServiceDetailsServicePhysicalLimits createPostalServiceServiceDetailsServicePhysicalLimits() {
        return new PostalServiceServiceDetailsServicePhysicalLimits();
    }

    /**
     * Create an instance of {@link ArrayOfPostalServiceServiceDetailsServiceRate }
     * 
     */
    public ArrayOfPostalServiceServiceDetailsServiceRate createArrayOfPostalServiceServiceDetailsServiceRate() {
        return new ArrayOfPostalServiceServiceDetailsServiceRate();
    }

    /**
     * Create an instance of {@link PostalServiceServiceDetailsServiceRate }
     * 
     */
    public PostalServiceServiceDetailsServiceRate createPostalServiceServiceDetailsServiceRate() {
        return new PostalServiceServiceDetailsServiceRate();
    }

    /**
     * Create an instance of {@link ArrayOfPostalServiceServiceDetailsServiceStandards }
     * 
     */
    public ArrayOfPostalServiceServiceDetailsServiceStandards createArrayOfPostalServiceServiceDetailsServiceStandards() {
        return new ArrayOfPostalServiceServiceDetailsServiceStandards();
    }

    /**
     * Create an instance of {@link PostalServiceServiceDetailsServiceStandards }
     * 
     */
    public PostalServiceServiceDetailsServiceStandards createPostalServiceServiceDetailsServiceStandards() {
        return new PostalServiceServiceDetailsServiceStandards();
    }

    /**
     * Create an instance of {@link MarketedGoodMarketedGoodDetails }
     * 
     */
    public MarketedGoodMarketedGoodDetails createMarketedGoodMarketedGoodDetails() {
        return new MarketedGoodMarketedGoodDetails();
    }

    /**
     * Create an instance of {@link Value }
     * 
     */
    public Value createValue() {
        return new Value();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyType")
    public JAXBElement<Object> createAnyType(Object value) {
        return new JAXBElement<Object>(_AnyType_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyURI")
    public JAXBElement<String> createAnyURI(String value) {
        return new JAXBElement<String>(_AnyURI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<byte[]>(_Base64Binary_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "byte")
    public JAXBElement<Byte> createByte(Byte value) {
        return new JAXBElement<Byte>(_Byte_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "dateTime")
    public JAXBElement<XMLGregorianCalendar> createDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "decimal")
    public JAXBElement<BigDecimal> createDecimal(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Decimal_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "double")
    public JAXBElement<Double> createDouble(Double value) {
        return new JAXBElement<Double>(_Double_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Float }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "float")
    public JAXBElement<Float> createFloat(Float value) {
        return new JAXBElement<Float>(_Float_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<Integer>(_Int_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "long")
    public JAXBElement<Long> createLong(Long value) {
        return new JAXBElement<Long>(_Long_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QName }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link QName }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "QName")
    public JAXBElement<QName> createQName(QName value) {
        return new JAXBElement<QName>(_QName_QNAME, QName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Short }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "short")
    public JAXBElement<Short> createShort(Short value) {
        return new JAXBElement<Short>(_Short_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Short }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedByte")
    public JAXBElement<Short> createUnsignedByte(Short value) {
        return new JAXBElement<Short>(_UnsignedByte_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedInt")
    public JAXBElement<Long> createUnsignedInt(Long value) {
        return new JAXBElement<Long>(_UnsignedInt_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedLong")
    public JAXBElement<BigInteger> createUnsignedLong(BigInteger value) {
        return new JAXBElement<BigInteger>(_UnsignedLong_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedShort")
    public JAXBElement<Integer> createUnsignedShort(Integer value) {
        return new JAXBElement<Integer>(_UnsignedShort_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "char")
    public JAXBElement<Integer> createChar(Integer value) {
        return new JAXBElement<Integer>(_Char_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "duration")
    public JAXBElement<Duration> createDuration(Duration value) {
        return new JAXBElement<Duration>(_Duration_QNAME, Duration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "guid")
    public JAXBElement<String> createGuid(String value) {
        return new JAXBElement<String>(_Guid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/Arrays", name = "ArrayOfstring")
    public JAXBElement<ArrayOfstring> createArrayOfstring(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_ArrayOfstring_QNAME, ArrayOfstring.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCDSView }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfCDSView }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "ArrayOfCDSView")
    public JAXBElement<ArrayOfCDSView> createArrayOfCDSView(ArrayOfCDSView value) {
        return new JAXBElement<ArrayOfCDSView>(_ArrayOfCDSView_QNAME, ArrayOfCDSView.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CDSView }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CDSView }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "CDSView")
    public JAXBElement<CDSView> createCDSView(CDSView value) {
        return new JAXBElement<CDSView>(_CDSView_QNAME, CDSView.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Response }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Response }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Response")
    public JAXBElement<Response> createResponse(Response value) {
        return new JAXBElement<Response>(_Response_QNAME, Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CDSObject }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CDSObject }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "CDSObject")
    public JAXBElement<CDSObject> createCDSObject(CDSObject value) {
        return new JAXBElement<CDSObject>(_CDSObject_QNAME, CDSObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseResponseData }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ResponseResponseData }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Response.ResponseData")
    public JAXBElement<ResponseResponseData> createResponseResponseData(ResponseResponseData value) {
        return new JAXBElement<ResponseResponseData>(_ResponseResponseData_QNAME, ResponseResponseData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfResponseResponseDataTax }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfResponseResponseDataTax }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "ArrayOfResponse.ResponseData.Tax")
    public JAXBElement<ArrayOfResponseResponseDataTax> createArrayOfResponseResponseDataTax(ArrayOfResponseResponseDataTax value) {
        return new JAXBElement<ArrayOfResponseResponseDataTax>(_ArrayOfResponseResponseDataTax_QNAME, ArrayOfResponseResponseDataTax.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseResponseDataTax }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ResponseResponseDataTax }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Response.ResponseData.Tax")
    public JAXBElement<ResponseResponseDataTax> createResponseResponseDataTax(ResponseResponseDataTax value) {
        return new JAXBElement<ResponseResponseDataTax>(_ResponseResponseDataTax_QNAME, ResponseResponseDataTax.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Declaration }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Declaration }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Declaration")
    public JAXBElement<Declaration> createDeclaration(Declaration value) {
        return new JAXBElement<Declaration>(_Declaration_QNAME, Declaration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeclarationDeclarationData }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeclarationDeclarationData }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Declaration.DeclarationData")
    public JAXBElement<DeclarationDeclarationData> createDeclarationDeclarationData(DeclarationDeclarationData value) {
        return new JAXBElement<DeclarationDeclarationData>(_DeclarationDeclarationData_QNAME, DeclarationDeclarationData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDeclarationDeclarationDataContentPiece }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfDeclarationDeclarationDataContentPiece }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "ArrayOfDeclaration.DeclarationData.ContentPiece")
    public JAXBElement<ArrayOfDeclarationDeclarationDataContentPiece> createArrayOfDeclarationDeclarationDataContentPiece(ArrayOfDeclarationDeclarationDataContentPiece value) {
        return new JAXBElement<ArrayOfDeclarationDeclarationDataContentPiece>(_ArrayOfDeclarationDeclarationDataContentPiece_QNAME, ArrayOfDeclarationDeclarationDataContentPiece.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeclarationDeclarationDataContentPiece }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeclarationDeclarationDataContentPiece }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Declaration.DeclarationData.ContentPiece")
    public JAXBElement<DeclarationDeclarationDataContentPiece> createDeclarationDeclarationDataContentPiece(DeclarationDeclarationDataContentPiece value) {
        return new JAXBElement<DeclarationDeclarationDataContentPiece>(_DeclarationDeclarationDataContentPiece_QNAME, DeclarationDeclarationDataContentPiece.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDeclarationDeclarationDataDocument }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfDeclarationDeclarationDataDocument }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "ArrayOfDeclaration.DeclarationData.Document")
    public JAXBElement<ArrayOfDeclarationDeclarationDataDocument> createArrayOfDeclarationDeclarationDataDocument(ArrayOfDeclarationDeclarationDataDocument value) {
        return new JAXBElement<ArrayOfDeclarationDeclarationDataDocument>(_ArrayOfDeclarationDeclarationDataDocument_QNAME, ArrayOfDeclarationDeclarationDataDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeclarationDeclarationDataDocument }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeclarationDeclarationDataDocument }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Declaration.DeclarationData.Document")
    public JAXBElement<DeclarationDeclarationDataDocument> createDeclarationDeclarationDataDocument(DeclarationDeclarationDataDocument value) {
        return new JAXBElement<DeclarationDeclarationDataDocument>(_DeclarationDeclarationDataDocument_QNAME, DeclarationDeclarationDataDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MailObject }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MailObject }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "MailObject")
    public JAXBElement<MailObject> createMailObject(MailObject value) {
        return new JAXBElement<MailObject>(_MailObject_QNAME, MailObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfHSLookupInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfHSLookupInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "ArrayOfHSLookupInfo")
    public JAXBElement<ArrayOfHSLookupInfo> createArrayOfHSLookupInfo(ArrayOfHSLookupInfo value) {
        return new JAXBElement<ArrayOfHSLookupInfo>(_ArrayOfHSLookupInfo_QNAME, ArrayOfHSLookupInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HSLookupInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link HSLookupInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "HSLookupInfo")
    public JAXBElement<HSLookupInfo> createHSLookupInfo(HSLookupInfo value) {
        return new JAXBElement<HSLookupInfo>(_HSLookupInfo_QNAME, HSLookupInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WatchdogInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link WatchdogInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "WatchdogInfo")
    public JAXBElement<WatchdogInfo> createWatchdogInfo(WatchdogInfo value) {
        return new JAXBElement<WatchdogInfo>(_WatchdogInfo_QNAME, WatchdogInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WatchdogInfoOperationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link WatchdogInfoOperationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "WatchdogInfo.OperationType")
    public JAXBElement<WatchdogInfoOperationType> createWatchdogInfoOperationType(WatchdogInfoOperationType value) {
        return new JAXBElement<WatchdogInfoOperationType>(_WatchdogInfoOperationType_QNAME, WatchdogInfoOperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Entity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Entity }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/PTC.BusinessLayer.Core", name = "Entity")
    public JAXBElement<Entity> createEntity(Entity value) {
        return new JAXBElement<Entity>(_Entity_QNAME, Entity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfRestrictionProhibition }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfRestrictionProhibition }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", name = "ArrayOfRestrictionProhibition")
    public JAXBElement<ArrayOfRestrictionProhibition> createArrayOfRestrictionProhibition(ArrayOfRestrictionProhibition value) {
        return new JAXBElement<ArrayOfRestrictionProhibition>(_ArrayOfRestrictionProhibition_QNAME, ArrayOfRestrictionProhibition.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RestrictionProhibition }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RestrictionProhibition }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", name = "RestrictionProhibition")
    public JAXBElement<RestrictionProhibition> createRestrictionProhibition(RestrictionProhibition value) {
        return new JAXBElement<RestrictionProhibition>(_RestrictionProhibition_QNAME, RestrictionProhibition.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPostalService }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfPostalService }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "ArrayOfPostalService")
    public JAXBElement<ArrayOfPostalService> createArrayOfPostalService(ArrayOfPostalService value) {
        return new JAXBElement<ArrayOfPostalService>(_ArrayOfPostalService_QNAME, ArrayOfPostalService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostalService }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PostalService }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "PostalService")
    public JAXBElement<PostalService> createPostalService(PostalService value) {
        return new JAXBElement<PostalService>(_PostalService_QNAME, PostalService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostalServiceServiceDetails }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PostalServiceServiceDetails }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "PostalService.ServiceDetails")
    public JAXBElement<PostalServiceServiceDetails> createPostalServiceServiceDetails(PostalServiceServiceDetails value) {
        return new JAXBElement<PostalServiceServiceDetails>(_PostalServiceServiceDetails_QNAME, PostalServiceServiceDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostalServiceServiceDetailsServiceOptions }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PostalServiceServiceDetailsServiceOptions }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "PostalService.ServiceDetails.ServiceOptions")
    public JAXBElement<PostalServiceServiceDetailsServiceOptions> createPostalServiceServiceDetailsServiceOptions(PostalServiceServiceDetailsServiceOptions value) {
        return new JAXBElement<PostalServiceServiceDetailsServiceOptions>(_PostalServiceServiceDetailsServiceOptions_QNAME, PostalServiceServiceDetailsServiceOptions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostalServiceServiceDetailsServicePhysicalLimits }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PostalServiceServiceDetailsServicePhysicalLimits }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "PostalService.ServiceDetails.ServicePhysicalLimits")
    public JAXBElement<PostalServiceServiceDetailsServicePhysicalLimits> createPostalServiceServiceDetailsServicePhysicalLimits(PostalServiceServiceDetailsServicePhysicalLimits value) {
        return new JAXBElement<PostalServiceServiceDetailsServicePhysicalLimits>(_PostalServiceServiceDetailsServicePhysicalLimits_QNAME, PostalServiceServiceDetailsServicePhysicalLimits.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPostalServiceServiceDetailsServiceRate }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfPostalServiceServiceDetailsServiceRate }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "ArrayOfPostalService.ServiceDetails.ServiceRate")
    public JAXBElement<ArrayOfPostalServiceServiceDetailsServiceRate> createArrayOfPostalServiceServiceDetailsServiceRate(ArrayOfPostalServiceServiceDetailsServiceRate value) {
        return new JAXBElement<ArrayOfPostalServiceServiceDetailsServiceRate>(_ArrayOfPostalServiceServiceDetailsServiceRate_QNAME, ArrayOfPostalServiceServiceDetailsServiceRate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostalServiceServiceDetailsServiceRate }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PostalServiceServiceDetailsServiceRate }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "PostalService.ServiceDetails.ServiceRate")
    public JAXBElement<PostalServiceServiceDetailsServiceRate> createPostalServiceServiceDetailsServiceRate(PostalServiceServiceDetailsServiceRate value) {
        return new JAXBElement<PostalServiceServiceDetailsServiceRate>(_PostalServiceServiceDetailsServiceRate_QNAME, PostalServiceServiceDetailsServiceRate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPostalServiceServiceDetailsServiceStandards }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfPostalServiceServiceDetailsServiceStandards }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "ArrayOfPostalService.ServiceDetails.ServiceStandards")
    public JAXBElement<ArrayOfPostalServiceServiceDetailsServiceStandards> createArrayOfPostalServiceServiceDetailsServiceStandards(ArrayOfPostalServiceServiceDetailsServiceStandards value) {
        return new JAXBElement<ArrayOfPostalServiceServiceDetailsServiceStandards>(_ArrayOfPostalServiceServiceDetailsServiceStandards_QNAME, ArrayOfPostalServiceServiceDetailsServiceStandards.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostalServiceServiceDetailsServiceStandards }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PostalServiceServiceDetailsServiceStandards }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "PostalService.ServiceDetails.ServiceStandards")
    public JAXBElement<PostalServiceServiceDetailsServiceStandards> createPostalServiceServiceDetailsServiceStandards(PostalServiceServiceDetailsServiceStandards value) {
        return new JAXBElement<PostalServiceServiceDetailsServiceStandards>(_PostalServiceServiceDetailsServiceStandards_QNAME, PostalServiceServiceDetailsServiceStandards.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfMarketedGood }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfMarketedGood }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "ArrayOfMarketedGood")
    public JAXBElement<ArrayOfMarketedGood> createArrayOfMarketedGood(ArrayOfMarketedGood value) {
        return new JAXBElement<ArrayOfMarketedGood>(_ArrayOfMarketedGood_QNAME, ArrayOfMarketedGood.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MarketedGood }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MarketedGood }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "MarketedGood")
    public JAXBElement<MarketedGood> createMarketedGood(MarketedGood value) {
        return new JAXBElement<MarketedGood>(_MarketedGood_QNAME, MarketedGood.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MarketedGoodMarketedGoodDetails }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MarketedGoodMarketedGoodDetails }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "MarketedGood.MarketedGoodDetails")
    public JAXBElement<MarketedGoodMarketedGoodDetails> createMarketedGoodMarketedGoodDetails(MarketedGoodMarketedGoodDetails value) {
        return new JAXBElement<MarketedGoodMarketedGoodDetails>(_MarketedGoodMarketedGoodDetails_QNAME, MarketedGoodMarketedGoodDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Value }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Value }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Base", name = "Value")
    public JAXBElement<Value> createValue(Value value) {
        return new JAXBElement<Value>(_Value_QNAME, Value.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "organizationCd", scope = ConvertCurrency.class)
    public JAXBElement<String> createConvertCurrencyOrganizationCd(String value) {
        return new JAXBElement<String>(_ConvertCurrencyOrganizationCd_QNAME, String.class, ConvertCurrency.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "fromCurrencyCd", scope = ConvertCurrency.class)
    public JAXBElement<String> createConvertCurrencyFromCurrencyCd(String value) {
        return new JAXBElement<String>(_ConvertCurrencyFromCurrencyCd_QNAME, String.class, ConvertCurrency.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "toCurrencyCd", scope = ConvertCurrency.class)
    public JAXBElement<String> createConvertCurrencyToCurrencyCd(String value) {
        return new JAXBElement<String>(_ConvertCurrencyToCurrencyCd_QNAME, String.class, ConvertCurrency.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "decisionCode", scope = GetDecisionNameAndCategoryFromCode.class)
    public JAXBElement<String> createGetDecisionNameAndCategoryFromCodeDecisionCode(String value) {
        return new JAXBElement<String>(_GetDecisionNameAndCategoryFromCodeDecisionCode_QNAME, String.class, GetDecisionNameAndCategoryFromCode.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetDecisionNameAndCategoryFromCodeResult", scope = GetDecisionNameAndCategoryFromCodeResponse.class)
    public JAXBElement<ArrayOfstring> createGetDecisionNameAndCategoryFromCodeResponseGetDecisionNameAndCategoryFromCodeResult(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_GetDecisionNameAndCategoryFromCodeResponseGetDecisionNameAndCategoryFromCodeResult_QNAME, ArrayOfstring.class, GetDecisionNameAndCategoryFromCodeResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "postalOrgCd", scope = Load.class)
    public JAXBElement<String> createLoadPostalOrgCd(String value) {
        return new JAXBElement<String>(_LoadPostalOrgCd_QNAME, String.class, Load.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "customsOrgCd", scope = Load.class)
    public JAXBElement<String> createLoadCustomsOrgCd(String value) {
        return new JAXBElement<String>(_LoadCustomsOrgCd_QNAME, String.class, Load.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "organizationTypeCd", scope = Load.class)
    public JAXBElement<String> createLoadOrganizationTypeCd(String value) {
        return new JAXBElement<String>(_LoadOrganizationTypeCd_QNAME, String.class, Load.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "id", scope = Load.class)
    public JAXBElement<String> createLoadId(String value) {
        return new JAXBElement<String>(_LoadId_QNAME, String.class, Load.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "flow", scope = Load.class)
    public JAXBElement<String> createLoadFlow(String value) {
        return new JAXBElement<String>(_LoadFlow_QNAME, String.class, Load.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "partnerPostalOrgCd", scope = Load.class)
    public JAXBElement<String> createLoadPartnerPostalOrgCd(String value) {
        return new JAXBElement<String>(_LoadPartnerPostalOrgCd_QNAME, String.class, Load.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "partCountryCd", scope = Load.class)
    public JAXBElement<String> createLoadPartCountryCd(String value) {
        return new JAXBElement<String>(_LoadPartCountryCd_QNAME, String.class, Load.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "dtFrom", scope = Load.class)
    public JAXBElement<XMLGregorianCalendar> createLoadDtFrom(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LoadDtFrom_QNAME, XMLGregorianCalendar.class, Load.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "dtTo", scope = Load.class)
    public JAXBElement<XMLGregorianCalendar> createLoadDtTo(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LoadDtTo_QNAME, XMLGregorianCalendar.class, Load.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "zipFrom", scope = Load.class)
    public JAXBElement<String> createLoadZipFrom(String value) {
        return new JAXBElement<String>(_LoadZipFrom_QNAME, String.class, Load.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "zipTo", scope = Load.class)
    public JAXBElement<String> createLoadZipTo(String value) {
        return new JAXBElement<String>(_LoadZipTo_QNAME, String.class, Load.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "mailClassCd", scope = Load.class)
    public JAXBElement<String> createLoadMailClassCd(String value) {
        return new JAXBElement<String>(_LoadMailClassCd_QNAME, String.class, Load.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "recordsLimit", scope = Load.class)
    public JAXBElement<Integer> createLoadRecordsLimit(Integer value) {
        return new JAXBElement<Integer>(_LoadRecordsLimit_QNAME, Integer.class, Load.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCDSView }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfCDSView }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "LoadResult", scope = LoadResponse.class)
    public JAXBElement<ArrayOfCDSView> createLoadResponseLoadResult(ArrayOfCDSView value) {
        return new JAXBElement<ArrayOfCDSView>(_LoadResponseLoadResult_QNAME, ArrayOfCDSView.class, LoadResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "postalOrgCd", scope = LoadByMailState.class)
    public JAXBElement<String> createLoadByMailStatePostalOrgCd(String value) {
        return new JAXBElement<String>(_LoadPostalOrgCd_QNAME, String.class, LoadByMailState.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "customsOrgCd", scope = LoadByMailState.class)
    public JAXBElement<String> createLoadByMailStateCustomsOrgCd(String value) {
        return new JAXBElement<String>(_LoadCustomsOrgCd_QNAME, String.class, LoadByMailState.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "organizationTypeCd", scope = LoadByMailState.class)
    public JAXBElement<String> createLoadByMailStateOrganizationTypeCd(String value) {
        return new JAXBElement<String>(_LoadOrganizationTypeCd_QNAME, String.class, LoadByMailState.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "flow", scope = LoadByMailState.class)
    public JAXBElement<String> createLoadByMailStateFlow(String value) {
        return new JAXBElement<String>(_LoadFlow_QNAME, String.class, LoadByMailState.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "mailStateCd", scope = LoadByMailState.class)
    public JAXBElement<String> createLoadByMailStateMailStateCd(String value) {
        return new JAXBElement<String>(_LoadByMailStateMailStateCd_QNAME, String.class, LoadByMailState.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCDSView }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfCDSView }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "LoadByMailStateResult", scope = LoadByMailStateResponse.class)
    public JAXBElement<ArrayOfCDSView> createLoadByMailStateResponseLoadByMailStateResult(ArrayOfCDSView value) {
        return new JAXBElement<ArrayOfCDSView>(_LoadByMailStateResponseLoadByMailStateResult_QNAME, ArrayOfCDSView.class, LoadByMailStateResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "postalOrgCd", scope = GetCustomsStatus.class)
    public JAXBElement<String> createGetCustomsStatusPostalOrgCd(String value) {
        return new JAXBElement<String>(_LoadPostalOrgCd_QNAME, String.class, GetCustomsStatus.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "id", scope = GetCustomsStatus.class)
    public JAXBElement<String> createGetCustomsStatusId(String value) {
        return new JAXBElement<String>(_LoadId_QNAME, String.class, GetCustomsStatus.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "flow", scope = GetCustomsStatus.class)
    public JAXBElement<String> createGetCustomsStatusFlow(String value) {
        return new JAXBElement<String>(_LoadFlow_QNAME, String.class, GetCustomsStatus.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "userCd", scope = GetCustomsStatus.class)
    public JAXBElement<String> createGetCustomsStatusUserCd(String value) {
        return new JAXBElement<String>(_GetCustomsStatusUserCd_QNAME, String.class, GetCustomsStatus.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CDSView }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CDSView }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetCustomsStatusResult", scope = GetCustomsStatusResponse.class)
    public JAXBElement<CDSView> createGetCustomsStatusResponseGetCustomsStatusResult(CDSView value) {
        return new JAXBElement<CDSView>(_GetCustomsStatusResponseGetCustomsStatusResult_QNAME, CDSView.class, GetCustomsStatusResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "postalOrgCd", scope = CreateNewDeclaration.class)
    public JAXBElement<String> createCreateNewDeclarationPostalOrgCd(String value) {
        return new JAXBElement<String>(_LoadPostalOrgCd_QNAME, String.class, CreateNewDeclaration.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "customsOrgCd", scope = CreateNewDeclaration.class)
    public JAXBElement<String> createCreateNewDeclarationCustomsOrgCd(String value) {
        return new JAXBElement<String>(_LoadCustomsOrgCd_QNAME, String.class, CreateNewDeclaration.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "itemId", scope = CreateNewDeclaration.class)
    public JAXBElement<String> createCreateNewDeclarationItemId(String value) {
        return new JAXBElement<String>(_CreateNewDeclarationItemId_QNAME, String.class, CreateNewDeclaration.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "mailFlow", scope = CreateNewDeclaration.class)
    public JAXBElement<String> createCreateNewDeclarationMailFlow(String value) {
        return new JAXBElement<String>(_CreateNewDeclarationMailFlow_QNAME, String.class, CreateNewDeclaration.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Declaration }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Declaration }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "CreateNewDeclarationResult", scope = CreateNewDeclarationResponse.class)
    public JAXBElement<Declaration> createCreateNewDeclarationResponseCreateNewDeclarationResult(Declaration value) {
        return new JAXBElement<Declaration>(_CreateNewDeclarationResponseCreateNewDeclarationResult_QNAME, Declaration.class, CreateNewDeclarationResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "postalOrgCd", scope = CreateNewResponse.class)
    public JAXBElement<String> createCreateNewResponsePostalOrgCd(String value) {
        return new JAXBElement<String>(_LoadPostalOrgCd_QNAME, String.class, CreateNewResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "customsOrgCd", scope = CreateNewResponse.class)
    public JAXBElement<String> createCreateNewResponseCustomsOrgCd(String value) {
        return new JAXBElement<String>(_LoadCustomsOrgCd_QNAME, String.class, CreateNewResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Response }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Response }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "CreateNewResponseResult", scope = CreateNewResponseResponse.class)
    public JAXBElement<Response> createCreateNewResponseResponseCreateNewResponseResult(Response value) {
        return new JAXBElement<Response>(_CreateNewResponseResponseCreateNewResponseResult_QNAME, Response.class, CreateNewResponseResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MailObject }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MailObject }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "mailObject", scope = StoreDeclaration.class)
    public JAXBElement<MailObject> createStoreDeclarationMailObject(MailObject value) {
        return new JAXBElement<MailObject>(_StoreDeclarationMailObject_QNAME, MailObject.class, StoreDeclaration.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Declaration }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Declaration }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "decl", scope = StoreDeclaration.class)
    public JAXBElement<Declaration> createStoreDeclarationDecl(Declaration value) {
        return new JAXBElement<Declaration>(_StoreDeclarationDecl_QNAME, Declaration.class, StoreDeclaration.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "userCd", scope = StoreDeclaration.class)
    public JAXBElement<String> createStoreDeclarationUserCd(String value) {
        return new JAXBElement<String>(_GetCustomsStatusUserCd_QNAME, String.class, StoreDeclaration.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MailObject }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MailObject }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "mailObject", scope = StoreResponse.class)
    public JAXBElement<MailObject> createStoreResponseMailObject(MailObject value) {
        return new JAXBElement<MailObject>(_StoreDeclarationMailObject_QNAME, MailObject.class, StoreResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Response }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Response }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "resp", scope = StoreResponse.class)
    public JAXBElement<Response> createStoreResponseResp(Response value) {
        return new JAXBElement<Response>(_StoreResponseResp_QNAME, Response.class, StoreResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "userCd", scope = StoreResponse.class)
    public JAXBElement<String> createStoreResponseUserCd(String value) {
        return new JAXBElement<String>(_GetCustomsStatusUserCd_QNAME, String.class, StoreResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCDSView }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfCDSView }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "cdsViews", scope = CreateOrUpdateDeclarations.class)
    public JAXBElement<ArrayOfCDSView> createCreateOrUpdateDeclarationsCdsViews(ArrayOfCDSView value) {
        return new JAXBElement<ArrayOfCDSView>(_CreateOrUpdateDeclarationsCdsViews_QNAME, ArrayOfCDSView.class, CreateOrUpdateDeclarations.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "userCd", scope = CreateOrUpdateDeclarations.class)
    public JAXBElement<String> createCreateOrUpdateDeclarationsUserCd(String value) {
        return new JAXBElement<String>(_GetCustomsStatusUserCd_QNAME, String.class, CreateOrUpdateDeclarations.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCDSView }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfCDSView }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "cdsViews", scope = CreateOrUpdateResponses.class)
    public JAXBElement<ArrayOfCDSView> createCreateOrUpdateResponsesCdsViews(ArrayOfCDSView value) {
        return new JAXBElement<ArrayOfCDSView>(_CreateOrUpdateDeclarationsCdsViews_QNAME, ArrayOfCDSView.class, CreateOrUpdateResponses.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "userCd", scope = CreateOrUpdateResponses.class)
    public JAXBElement<String> createCreateOrUpdateResponsesUserCd(String value) {
        return new JAXBElement<String>(_GetCustomsStatusUserCd_QNAME, String.class, CreateOrUpdateResponses.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "id", scope = UpdateMailState.class)
    public JAXBElement<String> createUpdateMailStateId(String value) {
        return new JAXBElement<String>(_LoadId_QNAME, String.class, UpdateMailState.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "postalOrgCd", scope = UpdateMailState.class)
    public JAXBElement<String> createUpdateMailStatePostalOrgCd(String value) {
        return new JAXBElement<String>(_LoadPostalOrgCd_QNAME, String.class, UpdateMailState.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "partnerPostalOrgCd", scope = UpdateMailState.class)
    public JAXBElement<String> createUpdateMailStatePartnerPostalOrgCd(String value) {
        return new JAXBElement<String>(_LoadPartnerPostalOrgCd_QNAME, String.class, UpdateMailState.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "flow", scope = UpdateMailState.class)
    public JAXBElement<String> createUpdateMailStateFlow(String value) {
        return new JAXBElement<String>(_LoadFlow_QNAME, String.class, UpdateMailState.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "userCd", scope = UpdateMailState.class)
    public JAXBElement<String> createUpdateMailStateUserCd(String value) {
        return new JAXBElement<String>(_GetCustomsStatusUserCd_QNAME, String.class, UpdateMailState.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "anonymousDeclarationId", scope = ConvertAnonymousToCDSDeclaration.class)
    public JAXBElement<String> createConvertAnonymousToCDSDeclarationAnonymousDeclarationId(String value) {
        return new JAXBElement<String>(_ConvertAnonymousToCDSDeclarationAnonymousDeclarationId_QNAME, String.class, ConvertAnonymousToCDSDeclaration.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "internationalItemId", scope = ConvertAnonymousToCDSDeclaration.class)
    public JAXBElement<String> createConvertAnonymousToCDSDeclarationInternationalItemId(String value) {
        return new JAXBElement<String>(_ConvertAnonymousToCDSDeclarationInternationalItemId_QNAME, String.class, ConvertAnonymousToCDSDeclaration.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "userCd", scope = ConvertAnonymousToCDSDeclaration.class)
    public JAXBElement<String> createConvertAnonymousToCDSDeclarationUserCd(String value) {
        return new JAXBElement<String>(_GetCustomsStatusUserCd_QNAME, String.class, ConvertAnonymousToCDSDeclaration.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "goodDescription", scope = GetHSCodes.class)
    public JAXBElement<String> createGetHSCodesGoodDescription(String value) {
        return new JAXBElement<String>(_GetHSCodesGoodDescription_QNAME, String.class, GetHSCodes.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfHSLookupInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfHSLookupInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetHSCodesResult", scope = GetHSCodesResponse.class)
    public JAXBElement<ArrayOfHSLookupInfo> createGetHSCodesResponseGetHSCodesResult(ArrayOfHSLookupInfo value) {
        return new JAXBElement<ArrayOfHSLookupInfo>(_GetHSCodesResponseGetHSCodesResult_QNAME, ArrayOfHSLookupInfo.class, GetHSCodesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "destinationPostalOrgCd", scope = GetRestrictionProhibitionByDescription.class)
    public JAXBElement<String> createGetRestrictionProhibitionByDescriptionDestinationPostalOrgCd(String value) {
        return new JAXBElement<String>(_GetRestrictionProhibitionByDescriptionDestinationPostalOrgCd_QNAME, String.class, GetRestrictionProhibitionByDescription.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "sendingCountryCd", scope = GetRestrictionProhibitionByDescription.class)
    public JAXBElement<String> createGetRestrictionProhibitionByDescriptionSendingCountryCd(String value) {
        return new JAXBElement<String>(_GetRestrictionProhibitionByDescriptionSendingCountryCd_QNAME, String.class, GetRestrictionProhibitionByDescription.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "goodDescription", scope = GetRestrictionProhibitionByDescription.class)
    public JAXBElement<String> createGetRestrictionProhibitionByDescriptionGoodDescription(String value) {
        return new JAXBElement<String>(_GetHSCodesGoodDescription_QNAME, String.class, GetRestrictionProhibitionByDescription.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfRestrictionProhibition }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfRestrictionProhibition }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetRestrictionProhibitionByDescriptionResult", scope = GetRestrictionProhibitionByDescriptionResponse.class)
    public JAXBElement<ArrayOfRestrictionProhibition> createGetRestrictionProhibitionByDescriptionResponseGetRestrictionProhibitionByDescriptionResult(ArrayOfRestrictionProhibition value) {
        return new JAXBElement<ArrayOfRestrictionProhibition>(_GetRestrictionProhibitionByDescriptionResponseGetRestrictionProhibitionByDescriptionResult_QNAME, ArrayOfRestrictionProhibition.class, GetRestrictionProhibitionByDescriptionResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "destinationPostalOrgCd", scope = GetRestrictionProhibitionByHS.class)
    public JAXBElement<String> createGetRestrictionProhibitionByHSDestinationPostalOrgCd(String value) {
        return new JAXBElement<String>(_GetRestrictionProhibitionByDescriptionDestinationPostalOrgCd_QNAME, String.class, GetRestrictionProhibitionByHS.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "sendingCountryCd", scope = GetRestrictionProhibitionByHS.class)
    public JAXBElement<String> createGetRestrictionProhibitionByHSSendingCountryCd(String value) {
        return new JAXBElement<String>(_GetRestrictionProhibitionByDescriptionSendingCountryCd_QNAME, String.class, GetRestrictionProhibitionByHS.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "goodHSCode", scope = GetRestrictionProhibitionByHS.class)
    public JAXBElement<String> createGetRestrictionProhibitionByHSGoodHSCode(String value) {
        return new JAXBElement<String>(_GetRestrictionProhibitionByHSGoodHSCode_QNAME, String.class, GetRestrictionProhibitionByHS.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfRestrictionProhibition }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfRestrictionProhibition }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetRestrictionProhibitionByHSResult", scope = GetRestrictionProhibitionByHSResponse.class)
    public JAXBElement<ArrayOfRestrictionProhibition> createGetRestrictionProhibitionByHSResponseGetRestrictionProhibitionByHSResult(ArrayOfRestrictionProhibition value) {
        return new JAXBElement<ArrayOfRestrictionProhibition>(_GetRestrictionProhibitionByHSResponseGetRestrictionProhibitionByHSResult_QNAME, ArrayOfRestrictionProhibition.class, GetRestrictionProhibitionByHSResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "itemId", scope = GetWatchdogItemInfo.class)
    public JAXBElement<String> createGetWatchdogItemInfoItemId(String value) {
        return new JAXBElement<String>(_CreateNewDeclarationItemId_QNAME, String.class, GetWatchdogItemInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WatchdogInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link WatchdogInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetWatchdogItemInfoResult", scope = GetWatchdogItemInfoResponse.class)
    public JAXBElement<WatchdogInfo> createGetWatchdogItemInfoResponseGetWatchdogItemInfoResult(WatchdogInfo value) {
        return new JAXBElement<WatchdogInfo>(_GetWatchdogItemInfoResponseGetWatchdogItemInfoResult_QNAME, WatchdogInfo.class, GetWatchdogItemInfoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "itemId", scope = SetWatchdogItemInfo.class)
    public JAXBElement<String> createSetWatchdogItemInfoItemId(String value) {
        return new JAXBElement<String>(_CreateNewDeclarationItemId_QNAME, String.class, SetWatchdogItemInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "destinationPostalOrgCd", scope = SetWatchdogItemInfo.class)
    public JAXBElement<String> createSetWatchdogItemInfoDestinationPostalOrgCd(String value) {
        return new JAXBElement<String>(_GetRestrictionProhibitionByDescriptionDestinationPostalOrgCd_QNAME, String.class, SetWatchdogItemInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "mailClass", scope = SetWatchdogItemInfo.class)
    public JAXBElement<String> createSetWatchdogItemInfoMailClass(String value) {
        return new JAXBElement<String>(_SetWatchdogItemInfoMailClass_QNAME, String.class, SetWatchdogItemInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "postingDate", scope = SetWatchdogItemInfo.class)
    public JAXBElement<XMLGregorianCalendar> createSetWatchdogItemInfoPostingDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SetWatchdogItemInfoPostingDate_QNAME, XMLGregorianCalendar.class, SetWatchdogItemInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WatchdogInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link WatchdogInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "watchdogInfo", scope = SetWatchdogItemInfo.class)
    public JAXBElement<WatchdogInfo> createSetWatchdogItemInfoWatchdogInfo(WatchdogInfo value) {
        return new JAXBElement<WatchdogInfo>(_SetWatchdogItemInfoWatchdogInfo_QNAME, WatchdogInfo.class, SetWatchdogItemInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "origOrganizationCd", scope = LoadPostalServices.class)
    public JAXBElement<String> createLoadPostalServicesOrigOrganizationCd(String value) {
        return new JAXBElement<String>(_LoadPostalServicesOrigOrganizationCd_QNAME, String.class, LoadPostalServices.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "destOrganizationCd", scope = LoadPostalServices.class)
    public JAXBElement<String> createLoadPostalServicesDestOrganizationCd(String value) {
        return new JAXBElement<String>(_LoadPostalServicesDestOrganizationCd_QNAME, String.class, LoadPostalServices.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPostalService }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfPostalService }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "LoadPostalServicesResult", scope = LoadPostalServicesResponse.class)
    public JAXBElement<ArrayOfPostalService> createLoadPostalServicesResponseLoadPostalServicesResult(ArrayOfPostalService value) {
        return new JAXBElement<ArrayOfPostalService>(_LoadPostalServicesResponseLoadPostalServicesResult_QNAME, ArrayOfPostalService.class, LoadPostalServicesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostalService }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PostalService }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "postalService", scope = StorePostalService.class)
    public JAXBElement<PostalService> createStorePostalServicePostalService(PostalService value) {
        return new JAXBElement<PostalService>(_StorePostalServicePostalService_QNAME, PostalService.class, StorePostalService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "sellerOrganizationCd", scope = LoadMarketedGood.class)
    public JAXBElement<String> createLoadMarketedGoodSellerOrganizationCd(String value) {
        return new JAXBElement<String>(_LoadMarketedGoodSellerOrganizationCd_QNAME, String.class, LoadMarketedGood.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "buyerOrganizationCd", scope = LoadMarketedGood.class)
    public JAXBElement<String> createLoadMarketedGoodBuyerOrganizationCd(String value) {
        return new JAXBElement<String>(_LoadMarketedGoodBuyerOrganizationCd_QNAME, String.class, LoadMarketedGood.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfMarketedGood }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfMarketedGood }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "LoadMarketedGoodResult", scope = LoadMarketedGoodResponse.class)
    public JAXBElement<ArrayOfMarketedGood> createLoadMarketedGoodResponseLoadMarketedGoodResult(ArrayOfMarketedGood value) {
        return new JAXBElement<ArrayOfMarketedGood>(_LoadMarketedGoodResponseLoadMarketedGoodResult_QNAME, ArrayOfMarketedGood.class, LoadMarketedGoodResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MarketedGood }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MarketedGood }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "marketedGood", scope = StoreMarketedGood.class)
    public JAXBElement<MarketedGood> createStoreMarketedGoodMarketedGood(MarketedGood value) {
        return new JAXBElement<MarketedGood>(_StoreMarketedGoodMarketedGood_QNAME, MarketedGood.class, StoreMarketedGood.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CDSView }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CDSView }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "cdsView", scope = PrintDoc.class)
    public JAXBElement<CDSView> createPrintDocCdsView(CDSView value) {
        return new JAXBElement<CDSView>(_PrintDocCdsView_QNAME, CDSView.class, PrintDoc.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "userCd", scope = PrintDoc.class)
    public JAXBElement<String> createPrintDocUserCd(String value) {
        return new JAXBElement<String>(_GetCustomsStatusUserCd_QNAME, String.class, PrintDoc.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "PrintDocResult", scope = PrintDocResponse.class)
    public JAXBElement<String> createPrintDocResponsePrintDocResult(String value) {
        return new JAXBElement<String>(_PrintDocResponsePrintDocResult_QNAME, String.class, PrintDocResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Base", name = "Amount", scope = Value.class)
    public JAXBElement<BigDecimal> createValueAmount(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ValueAmount_QNAME, BigDecimal.class, Value.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Base", name = "Currency", scope = Value.class)
    public JAXBElement<String> createValueCurrency(String value) {
        return new JAXBElement<String>(_ValueCurrency_QNAME, String.class, Value.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "BuyerOrganizations", scope = MarketedGoodMarketedGoodDetails.class)
    public JAXBElement<ArrayOfstring> createMarketedGoodMarketedGoodDetailsBuyerOrganizations(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_MarketedGoodMarketedGoodDetailsBuyerOrganizations_QNAME, ArrayOfstring.class, MarketedGoodMarketedGoodDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "GoodDetails", scope = MarketedGoodMarketedGoodDetails.class)
    public JAXBElement<String> createMarketedGoodMarketedGoodDetailsGoodDetails(String value) {
        return new JAXBElement<String>(_MarketedGoodMarketedGoodDetailsGoodDetails_QNAME, String.class, MarketedGoodMarketedGoodDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "HSCode", scope = MarketedGoodMarketedGoodDetails.class)
    public JAXBElement<String> createMarketedGoodMarketedGoodDetailsHSCode(String value) {
        return new JAXBElement<String>(_MarketedGoodMarketedGoodDetailsHSCode_QNAME, String.class, MarketedGoodMarketedGoodDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "HeightCm", scope = MarketedGoodMarketedGoodDetails.class)
    public JAXBElement<BigDecimal> createMarketedGoodMarketedGoodDetailsHeightCm(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_MarketedGoodMarketedGoodDetailsHeightCm_QNAME, BigDecimal.class, MarketedGoodMarketedGoodDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "LengthCm", scope = MarketedGoodMarketedGoodDetails.class)
    public JAXBElement<BigDecimal> createMarketedGoodMarketedGoodDetailsLengthCm(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_MarketedGoodMarketedGoodDetailsLengthCm_QNAME, BigDecimal.class, MarketedGoodMarketedGoodDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "Picture", scope = MarketedGoodMarketedGoodDetails.class)
    public JAXBElement<String> createMarketedGoodMarketedGoodDetailsPicture(String value) {
        return new JAXBElement<String>(_MarketedGoodMarketedGoodDetailsPicture_QNAME, String.class, MarketedGoodMarketedGoodDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Value }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Value }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "Price", scope = MarketedGoodMarketedGoodDetails.class)
    public JAXBElement<Value> createMarketedGoodMarketedGoodDetailsPrice(Value value) {
        return new JAXBElement<Value>(_MarketedGoodMarketedGoodDetailsPrice_QNAME, Value.class, MarketedGoodMarketedGoodDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "SellerAddress", scope = MarketedGoodMarketedGoodDetails.class)
    public JAXBElement<String> createMarketedGoodMarketedGoodDetailsSellerAddress(String value) {
        return new JAXBElement<String>(_MarketedGoodMarketedGoodDetailsSellerAddress_QNAME, String.class, MarketedGoodMarketedGoodDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "SellerCity", scope = MarketedGoodMarketedGoodDetails.class)
    public JAXBElement<String> createMarketedGoodMarketedGoodDetailsSellerCity(String value) {
        return new JAXBElement<String>(_MarketedGoodMarketedGoodDetailsSellerCity_QNAME, String.class, MarketedGoodMarketedGoodDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "SellerCountry", scope = MarketedGoodMarketedGoodDetails.class)
    public JAXBElement<String> createMarketedGoodMarketedGoodDetailsSellerCountry(String value) {
        return new JAXBElement<String>(_MarketedGoodMarketedGoodDetailsSellerCountry_QNAME, String.class, MarketedGoodMarketedGoodDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "SellerName", scope = MarketedGoodMarketedGoodDetails.class)
    public JAXBElement<String> createMarketedGoodMarketedGoodDetailsSellerName(String value) {
        return new JAXBElement<String>(_MarketedGoodMarketedGoodDetailsSellerName_QNAME, String.class, MarketedGoodMarketedGoodDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "SellerPhone", scope = MarketedGoodMarketedGoodDetails.class)
    public JAXBElement<String> createMarketedGoodMarketedGoodDetailsSellerPhone(String value) {
        return new JAXBElement<String>(_MarketedGoodMarketedGoodDetailsSellerPhone_QNAME, String.class, MarketedGoodMarketedGoodDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "SellerRefId", scope = MarketedGoodMarketedGoodDetails.class)
    public JAXBElement<String> createMarketedGoodMarketedGoodDetailsSellerRefId(String value) {
        return new JAXBElement<String>(_MarketedGoodMarketedGoodDetailsSellerRefId_QNAME, String.class, MarketedGoodMarketedGoodDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "SellerZip", scope = MarketedGoodMarketedGoodDetails.class)
    public JAXBElement<String> createMarketedGoodMarketedGoodDetailsSellerZip(String value) {
        return new JAXBElement<String>(_MarketedGoodMarketedGoodDetailsSellerZip_QNAME, String.class, MarketedGoodMarketedGoodDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "ServiceDescription", scope = MarketedGoodMarketedGoodDetails.class)
    public JAXBElement<String> createMarketedGoodMarketedGoodDetailsServiceDescription(String value) {
        return new JAXBElement<String>(_MarketedGoodMarketedGoodDetailsServiceDescription_QNAME, String.class, MarketedGoodMarketedGoodDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "Stock", scope = MarketedGoodMarketedGoodDetails.class)
    public JAXBElement<Integer> createMarketedGoodMarketedGoodDetailsStock(Integer value) {
        return new JAXBElement<Integer>(_MarketedGoodMarketedGoodDetailsStock_QNAME, Integer.class, MarketedGoodMarketedGoodDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "WeightKg", scope = MarketedGoodMarketedGoodDetails.class)
    public JAXBElement<BigDecimal> createMarketedGoodMarketedGoodDetailsWeightKg(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_MarketedGoodMarketedGoodDetailsWeightKg_QNAME, BigDecimal.class, MarketedGoodMarketedGoodDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "WidthCm", scope = MarketedGoodMarketedGoodDetails.class)
    public JAXBElement<BigDecimal> createMarketedGoodMarketedGoodDetailsWidthCm(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_MarketedGoodMarketedGoodDetailsWidthCm_QNAME, BigDecimal.class, MarketedGoodMarketedGoodDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "ItemMaxWeight", scope = PostalServiceServiceDetailsServiceRate.class)
    public JAXBElement<BigDecimal> createPostalServiceServiceDetailsServiceRateItemMaxWeight(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PostalServiceServiceDetailsServiceRateItemMaxWeight_QNAME, BigDecimal.class, PostalServiceServiceDetailsServiceRate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "RateCode", scope = PostalServiceServiceDetailsServiceRate.class)
    public JAXBElement<String> createPostalServiceServiceDetailsServiceRateRateCode(String value) {
        return new JAXBElement<String>(_PostalServiceServiceDetailsServiceRateRateCode_QNAME, String.class, PostalServiceServiceDetailsServiceRate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Value }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Value }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "RatePeKg", scope = PostalServiceServiceDetailsServiceRate.class)
    public JAXBElement<Value> createPostalServiceServiceDetailsServiceRateRatePeKg(Value value) {
        return new JAXBElement<Value>(_PostalServiceServiceDetailsServiceRateRatePeKg_QNAME, Value.class, PostalServiceServiceDetailsServiceRate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "MaxHeight", scope = PostalServiceServiceDetailsServicePhysicalLimits.class)
    public JAXBElement<BigDecimal> createPostalServiceServiceDetailsServicePhysicalLimitsMaxHeight(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PostalServiceServiceDetailsServicePhysicalLimitsMaxHeight_QNAME, BigDecimal.class, PostalServiceServiceDetailsServicePhysicalLimits.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "MaxLenght", scope = PostalServiceServiceDetailsServicePhysicalLimits.class)
    public JAXBElement<BigDecimal> createPostalServiceServiceDetailsServicePhysicalLimitsMaxLenght(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PostalServiceServiceDetailsServicePhysicalLimitsMaxLenght_QNAME, BigDecimal.class, PostalServiceServiceDetailsServicePhysicalLimits.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "MaxWeight", scope = PostalServiceServiceDetailsServicePhysicalLimits.class)
    public JAXBElement<BigDecimal> createPostalServiceServiceDetailsServicePhysicalLimitsMaxWeight(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PostalServiceServiceDetailsServicePhysicalLimitsMaxWeight_QNAME, BigDecimal.class, PostalServiceServiceDetailsServicePhysicalLimits.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "MaxWidth", scope = PostalServiceServiceDetailsServicePhysicalLimits.class)
    public JAXBElement<BigDecimal> createPostalServiceServiceDetailsServicePhysicalLimitsMaxWidth(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PostalServiceServiceDetailsServicePhysicalLimitsMaxWidth_QNAME, BigDecimal.class, PostalServiceServiceDetailsServicePhysicalLimits.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "MinHeight", scope = PostalServiceServiceDetailsServicePhysicalLimits.class)
    public JAXBElement<BigDecimal> createPostalServiceServiceDetailsServicePhysicalLimitsMinHeight(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PostalServiceServiceDetailsServicePhysicalLimitsMinHeight_QNAME, BigDecimal.class, PostalServiceServiceDetailsServicePhysicalLimits.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "MinLenght", scope = PostalServiceServiceDetailsServicePhysicalLimits.class)
    public JAXBElement<BigDecimal> createPostalServiceServiceDetailsServicePhysicalLimitsMinLenght(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PostalServiceServiceDetailsServicePhysicalLimitsMinLenght_QNAME, BigDecimal.class, PostalServiceServiceDetailsServicePhysicalLimits.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "MinWeight", scope = PostalServiceServiceDetailsServicePhysicalLimits.class)
    public JAXBElement<BigDecimal> createPostalServiceServiceDetailsServicePhysicalLimitsMinWeight(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PostalServiceServiceDetailsServicePhysicalLimitsMinWeight_QNAME, BigDecimal.class, PostalServiceServiceDetailsServicePhysicalLimits.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "MinWidth", scope = PostalServiceServiceDetailsServicePhysicalLimits.class)
    public JAXBElement<BigDecimal> createPostalServiceServiceDetailsServicePhysicalLimitsMinWidth(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PostalServiceServiceDetailsServicePhysicalLimitsMinWidth_QNAME, BigDecimal.class, PostalServiceServiceDetailsServicePhysicalLimits.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "HandlingClass", scope = PostalServiceServiceDetailsServiceOptions.class)
    public JAXBElement<String> createPostalServiceServiceDetailsServiceOptionsHandlingClass(String value) {
        return new JAXBElement<String>(_PostalServiceServiceDetailsServiceOptionsHandlingClass_QNAME, String.class, PostalServiceServiceDetailsServiceOptions.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "DestCountry", scope = PostalServiceServiceDetails.class)
    public JAXBElement<String> createPostalServiceServiceDetailsDestCountry(String value) {
        return new JAXBElement<String>(_PostalServiceServiceDetailsDestCountry_QNAME, String.class, PostalServiceServiceDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "DestZoneNumber", scope = PostalServiceServiceDetails.class)
    public JAXBElement<String> createPostalServiceServiceDetailsDestZoneNumber(String value) {
        return new JAXBElement<String>(_PostalServiceServiceDetailsDestZoneNumber_QNAME, String.class, PostalServiceServiceDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "MailSubclasses", scope = PostalServiceServiceDetails.class)
    public JAXBElement<ArrayOfstring> createPostalServiceServiceDetailsMailSubclasses(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_PostalServiceServiceDetailsMailSubclasses_QNAME, ArrayOfstring.class, PostalServiceServiceDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostalServiceServiceDetailsServiceOptions }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PostalServiceServiceDetailsServiceOptions }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "Options", scope = PostalServiceServiceDetails.class)
    public JAXBElement<PostalServiceServiceDetailsServiceOptions> createPostalServiceServiceDetailsOptions(PostalServiceServiceDetailsServiceOptions value) {
        return new JAXBElement<PostalServiceServiceDetailsServiceOptions>(_PostalServiceServiceDetailsOptions_QNAME, PostalServiceServiceDetailsServiceOptions.class, PostalServiceServiceDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "OrigCountry", scope = PostalServiceServiceDetails.class)
    public JAXBElement<String> createPostalServiceServiceDetailsOrigCountry(String value) {
        return new JAXBElement<String>(_PostalServiceServiceDetailsOrigCountry_QNAME, String.class, PostalServiceServiceDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "OrigZoneNumber", scope = PostalServiceServiceDetails.class)
    public JAXBElement<String> createPostalServiceServiceDetailsOrigZoneNumber(String value) {
        return new JAXBElement<String>(_PostalServiceServiceDetailsOrigZoneNumber_QNAME, String.class, PostalServiceServiceDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostalServiceServiceDetailsServicePhysicalLimits }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PostalServiceServiceDetailsServicePhysicalLimits }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "PhysicalLimits", scope = PostalServiceServiceDetails.class)
    public JAXBElement<PostalServiceServiceDetailsServicePhysicalLimits> createPostalServiceServiceDetailsPhysicalLimits(PostalServiceServiceDetailsServicePhysicalLimits value) {
        return new JAXBElement<PostalServiceServiceDetailsServicePhysicalLimits>(_PostalServiceServiceDetailsPhysicalLimits_QNAME, PostalServiceServiceDetailsServicePhysicalLimits.class, PostalServiceServiceDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPostalServiceServiceDetailsServiceRate }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfPostalServiceServiceDetailsServiceRate }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "Rates", scope = PostalServiceServiceDetails.class)
    public JAXBElement<ArrayOfPostalServiceServiceDetailsServiceRate> createPostalServiceServiceDetailsRates(ArrayOfPostalServiceServiceDetailsServiceRate value) {
        return new JAXBElement<ArrayOfPostalServiceServiceDetailsServiceRate>(_PostalServiceServiceDetailsRates_QNAME, ArrayOfPostalServiceServiceDetailsServiceRate.class, PostalServiceServiceDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPostalServiceServiceDetailsServiceStandards }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfPostalServiceServiceDetailsServiceStandards }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "Standards", scope = PostalServiceServiceDetails.class)
    public JAXBElement<ArrayOfPostalServiceServiceDetailsServiceStandards> createPostalServiceServiceDetailsStandards(ArrayOfPostalServiceServiceDetailsServiceStandards value) {
        return new JAXBElement<ArrayOfPostalServiceServiceDetailsServiceStandards>(_PostalServiceServiceDetailsStandards_QNAME, ArrayOfPostalServiceServiceDetailsServiceStandards.class, PostalServiceServiceDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", name = "AdditionalDocFileName", scope = RestrictionProhibition.class)
    public JAXBElement<String> createRestrictionProhibitionAdditionalDocFileName(String value) {
        return new JAXBElement<String>(_RestrictionProhibitionAdditionalDocFileName_QNAME, String.class, RestrictionProhibition.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", name = "Description", scope = RestrictionProhibition.class)
    public JAXBElement<String> createRestrictionProhibitionDescription(String value) {
        return new JAXBElement<String>(_RestrictionProhibitionDescription_QNAME, String.class, RestrictionProhibition.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", name = "FromHSCode", scope = RestrictionProhibition.class)
    public JAXBElement<String> createRestrictionProhibitionFromHSCode(String value) {
        return new JAXBElement<String>(_RestrictionProhibitionFromHSCode_QNAME, String.class, RestrictionProhibition.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", name = "HSCodeList", scope = RestrictionProhibition.class)
    public JAXBElement<String> createRestrictionProhibitionHSCodeList(String value) {
        return new JAXBElement<String>(_RestrictionProhibitionHSCodeList_QNAME, String.class, RestrictionProhibition.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", name = "LanguageCd", scope = RestrictionProhibition.class)
    public JAXBElement<String> createRestrictionProhibitionLanguageCd(String value) {
        return new JAXBElement<String>(_RestrictionProhibitionLanguageCd_QNAME, String.class, RestrictionProhibition.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", name = "OrganizationCd", scope = RestrictionProhibition.class)
    public JAXBElement<String> createRestrictionProhibitionOrganizationCd(String value) {
        return new JAXBElement<String>(_RestrictionProhibitionOrganizationCd_QNAME, String.class, RestrictionProhibition.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", name = "PCategory", scope = RestrictionProhibition.class)
    public JAXBElement<String> createRestrictionProhibitionPCategory(String value) {
        return new JAXBElement<String>(_RestrictionProhibitionPCategory_QNAME, String.class, RestrictionProhibition.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", name = "RestrictedCountries", scope = RestrictionProhibition.class)
    public JAXBElement<String> createRestrictionProhibitionRestrictedCountries(String value) {
        return new JAXBElement<String>(_RestrictionProhibitionRestrictedCountries_QNAME, String.class, RestrictionProhibition.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", name = "RestrictionNote", scope = RestrictionProhibition.class)
    public JAXBElement<String> createRestrictionProhibitionRestrictionNote(String value) {
        return new JAXBElement<String>(_RestrictionProhibitionRestrictionNote_QNAME, String.class, RestrictionProhibition.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", name = "ToHSCode", scope = RestrictionProhibition.class)
    public JAXBElement<String> createRestrictionProhibitionToHSCode(String value) {
        return new JAXBElement<String>(_RestrictionProhibitionToHSCode_QNAME, String.class, RestrictionProhibition.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", name = "WebsiteInfo", scope = RestrictionProhibition.class)
    public JAXBElement<String> createRestrictionProhibitionWebsiteInfo(String value) {
        return new JAXBElement<String>(_RestrictionProhibitionWebsiteInfo_QNAME, String.class, RestrictionProhibition.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "HSCode", scope = HSLookupInfo.class)
    public JAXBElement<String> createHSLookupInfoHSCode(String value) {
        return new JAXBElement<String>(_HSLookupInfoHSCode_QNAME, String.class, HSLookupInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Observations", scope = HSLookupInfo.class)
    public JAXBElement<String> createHSLookupInfoObservations(String value) {
        return new JAXBElement<String>(_HSLookupInfoObservations_QNAME, String.class, HSLookupInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "DocumentId", scope = DeclarationDeclarationDataDocument.class)
    public JAXBElement<String> createDeclarationDeclarationDataDocumentDocumentId(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataDocumentDocumentId_QNAME, String.class, DeclarationDeclarationDataDocument.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "DocumentName", scope = DeclarationDeclarationDataDocument.class)
    public JAXBElement<String> createDeclarationDeclarationDataDocumentDocumentName(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataDocumentDocumentName_QNAME, String.class, DeclarationDeclarationDataDocument.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "DocumentType", scope = DeclarationDeclarationDataDocument.class)
    public JAXBElement<String> createDeclarationDeclarationDataDocumentDocumentType(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataDocumentDocumentType_QNAME, String.class, DeclarationDeclarationDataDocument.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Amount", scope = DeclarationDeclarationDataContentPiece.class)
    public JAXBElement<BigDecimal> createDeclarationDeclarationDataContentPieceAmount(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_DeclarationDeclarationDataContentPieceAmount_QNAME, BigDecimal.class, DeclarationDeclarationDataContentPiece.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "CurrencyCd", scope = DeclarationDeclarationDataContentPiece.class)
    public JAXBElement<String> createDeclarationDeclarationDataContentPieceCurrencyCd(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataContentPieceCurrencyCd_QNAME, String.class, DeclarationDeclarationDataContentPiece.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Description", scope = DeclarationDeclarationDataContentPiece.class)
    public JAXBElement<String> createDeclarationDeclarationDataContentPieceDescription(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataContentPieceDescription_QNAME, String.class, DeclarationDeclarationDataContentPiece.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "ExtraFieldNames", scope = DeclarationDeclarationDataContentPiece.class)
    public JAXBElement<ArrayOfstring> createDeclarationDeclarationDataContentPieceExtraFieldNames(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_DeclarationDeclarationDataContentPieceExtraFieldNames_QNAME, ArrayOfstring.class, DeclarationDeclarationDataContentPiece.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "ExtraFieldValues", scope = DeclarationDeclarationDataContentPiece.class)
    public JAXBElement<ArrayOfstring> createDeclarationDeclarationDataContentPieceExtraFieldValues(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_DeclarationDeclarationDataContentPieceExtraFieldValues_QNAME, ArrayOfstring.class, DeclarationDeclarationDataContentPiece.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "HS", scope = DeclarationDeclarationDataContentPiece.class)
    public JAXBElement<String> createDeclarationDeclarationDataContentPieceHS(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataContentPieceHS_QNAME, String.class, DeclarationDeclarationDataContentPiece.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "ImportRestrictions", scope = DeclarationDeclarationDataContentPiece.class)
    public JAXBElement<String> createDeclarationDeclarationDataContentPieceImportRestrictions(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataContentPieceImportRestrictions_QNAME, String.class, DeclarationDeclarationDataContentPiece.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "ImportRestrictionsNotes", scope = DeclarationDeclarationDataContentPiece.class)
    public JAXBElement<String> createDeclarationDeclarationDataContentPieceImportRestrictionsNotes(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataContentPieceImportRestrictionsNotes_QNAME, String.class, DeclarationDeclarationDataContentPiece.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Index", scope = DeclarationDeclarationDataContentPiece.class)
    public JAXBElement<Integer> createDeclarationDeclarationDataContentPieceIndex(Integer value) {
        return new JAXBElement<Integer>(_DeclarationDeclarationDataContentPieceIndex_QNAME, Integer.class, DeclarationDeclarationDataContentPiece.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "NetWeight", scope = DeclarationDeclarationDataContentPiece.class)
    public JAXBElement<BigDecimal> createDeclarationDeclarationDataContentPieceNetWeight(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_DeclarationDeclarationDataContentPieceNetWeight_QNAME, BigDecimal.class, DeclarationDeclarationDataContentPiece.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Number", scope = DeclarationDeclarationDataContentPiece.class)
    public JAXBElement<Integer> createDeclarationDeclarationDataContentPieceNumber(Integer value) {
        return new JAXBElement<Integer>(_DeclarationDeclarationDataContentPieceNumber_QNAME, Integer.class, DeclarationDeclarationDataContentPiece.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "OrigCountryCd", scope = DeclarationDeclarationDataContentPiece.class)
    public JAXBElement<String> createDeclarationDeclarationDataContentPieceOrigCountryCd(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataContentPieceOrigCountryCd_QNAME, String.class, DeclarationDeclarationDataContentPiece.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "RevisedDescription", scope = DeclarationDeclarationDataContentPiece.class)
    public JAXBElement<String> createDeclarationDeclarationDataContentPieceRevisedDescription(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataContentPieceRevisedDescription_QNAME, String.class, DeclarationDeclarationDataContentPiece.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "RevisedHS", scope = DeclarationDeclarationDataContentPiece.class)
    public JAXBElement<String> createDeclarationDeclarationDataContentPieceRevisedHS(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataContentPieceRevisedHS_QNAME, String.class, DeclarationDeclarationDataContentPiece.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDeclarationDeclarationDataContentPiece }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfDeclarationDeclarationDataContentPiece }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "ContentPieces", scope = DeclarationDeclarationData.class)
    public JAXBElement<ArrayOfDeclarationDeclarationDataContentPiece> createDeclarationDeclarationDataContentPieces(ArrayOfDeclarationDeclarationDataContentPiece value) {
        return new JAXBElement<ArrayOfDeclarationDeclarationDataContentPiece>(_DeclarationDeclarationDataContentPieces_QNAME, ArrayOfDeclarationDeclarationDataContentPiece.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDeclarationDeclarationDataDocument }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfDeclarationDeclarationDataDocument }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Documents", scope = DeclarationDeclarationData.class)
    public JAXBElement<ArrayOfDeclarationDeclarationDataDocument> createDeclarationDeclarationDataDocuments(ArrayOfDeclarationDeclarationDataDocument value) {
        return new JAXBElement<ArrayOfDeclarationDeclarationDataDocument>(_DeclarationDeclarationDataDocuments_QNAME, ArrayOfDeclarationDeclarationDataDocument.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "ExtraFieldNames", scope = DeclarationDeclarationData.class)
    public JAXBElement<ArrayOfstring> createDeclarationDeclarationDataExtraFieldNames(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_DeclarationDeclarationDataContentPieceExtraFieldNames_QNAME, ArrayOfstring.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "ExtraFieldValues", scope = DeclarationDeclarationData.class)
    public JAXBElement<ArrayOfstring> createDeclarationDeclarationDataExtraFieldValues(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_DeclarationDeclarationDataContentPieceExtraFieldValues_QNAME, ArrayOfstring.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "GrossWeight", scope = DeclarationDeclarationData.class)
    public JAXBElement<BigDecimal> createDeclarationDeclarationDataGrossWeight(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_DeclarationDeclarationDataGrossWeight_QNAME, BigDecimal.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "HandlingClassCd", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataHandlingClassCd(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataHandlingClassCd_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "InsuredValue", scope = DeclarationDeclarationData.class)
    public JAXBElement<BigDecimal> createDeclarationDeclarationDataInsuredValue(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_DeclarationDeclarationDataInsuredValue_QNAME, BigDecimal.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "InsuredValueCurrencyCd", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataInsuredValueCurrencyCd(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataInsuredValueCurrencyCd_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "MaxIndex", scope = DeclarationDeclarationData.class)
    public JAXBElement<Integer> createDeclarationDeclarationDataMaxIndex(Integer value) {
        return new JAXBElement<Integer>(_DeclarationDeclarationDataMaxIndex_QNAME, Integer.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "NatureTypeCd", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataNatureTypeCd(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataNatureTypeCd_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "PackageImageLocalPath", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataPackageImageLocalPath(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataPackageImageLocalPath_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "PackageImageSourceInfo", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataPackageImageSourceInfo(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataPackageImageSourceInfo_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Postage", scope = DeclarationDeclarationData.class)
    public JAXBElement<BigDecimal> createDeclarationDeclarationDataPostage(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_DeclarationDeclarationDataPostage_QNAME, BigDecimal.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "PostageCurrencyCd", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataPostageCurrencyCd(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataPostageCurrencyCd_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "RecipientAddressLine1", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataRecipientAddressLine1(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataRecipientAddressLine1_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "RecipientAddressLine2", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataRecipientAddressLine2(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataRecipientAddressLine2_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "RecipientCity", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataRecipientCity(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataRecipientCity_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "RecipientCountryCd", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataRecipientCountryCd(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataRecipientCountryCd_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "RecipientEmail", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataRecipientEmail(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataRecipientEmail_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "RecipientFax", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataRecipientFax(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataRecipientFax_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "RecipientFirstName", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataRecipientFirstName(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataRecipientFirstName_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "RecipientIdRef", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataRecipientIdRef(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataRecipientIdRef_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "RecipientLastName", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataRecipientLastName(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataRecipientLastName_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "RecipientName", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataRecipientName(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataRecipientName_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "RecipientState", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataRecipientState(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataRecipientState_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "RecipientTelephone", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataRecipientTelephone(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataRecipientTelephone_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "RecipientZIP", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataRecipientZIP(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataRecipientZIP_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "SenderAddressLine1", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataSenderAddressLine1(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataSenderAddressLine1_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "SenderAddressLine2", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataSenderAddressLine2(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataSenderAddressLine2_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "SenderCity", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataSenderCity(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataSenderCity_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "SenderCountryCd", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataSenderCountryCd(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataSenderCountryCd_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "SenderEmail", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataSenderEmail(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataSenderEmail_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "SenderFirstName", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataSenderFirstName(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataSenderFirstName_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "SenderIdRef", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataSenderIdRef(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataSenderIdRef_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "SenderLastName", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataSenderLastName(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataSenderLastName_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "SenderName", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataSenderName(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataSenderName_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "SenderState", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataSenderState(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataSenderState_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "SenderTelephone", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataSenderTelephone(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataSenderTelephone_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "SenderZIP", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataSenderZIP(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataSenderZIP_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "TransportDate", scope = DeclarationDeclarationData.class)
    public JAXBElement<XMLGregorianCalendar> createDeclarationDeclarationDataTransportDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DeclarationDeclarationDataTransportDate_QNAME, XMLGregorianCalendar.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "TransportMode", scope = DeclarationDeclarationData.class)
    public JAXBElement<String> createDeclarationDeclarationDataTransportMode(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataTransportMode_QNAME, String.class, DeclarationDeclarationData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Amount", scope = ResponseResponseDataTax.class)
    public JAXBElement<BigDecimal> createResponseResponseDataTaxAmount(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_DeclarationDeclarationDataContentPieceAmount_QNAME, BigDecimal.class, ResponseResponseDataTax.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "CurrencyCd", scope = ResponseResponseDataTax.class)
    public JAXBElement<String> createResponseResponseDataTaxCurrencyCd(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataContentPieceCurrencyCd_QNAME, String.class, ResponseResponseDataTax.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Description", scope = ResponseResponseDataTax.class)
    public JAXBElement<String> createResponseResponseDataTaxDescription(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataContentPieceDescription_QNAME, String.class, ResponseResponseDataTax.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "ExtraFieldNames", scope = ResponseResponseDataTax.class)
    public JAXBElement<ArrayOfstring> createResponseResponseDataTaxExtraFieldNames(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_DeclarationDeclarationDataContentPieceExtraFieldNames_QNAME, ArrayOfstring.class, ResponseResponseDataTax.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "ExtraFieldValues", scope = ResponseResponseDataTax.class)
    public JAXBElement<ArrayOfstring> createResponseResponseDataTaxExtraFieldValues(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_DeclarationDeclarationDataContentPieceExtraFieldValues_QNAME, ArrayOfstring.class, ResponseResponseDataTax.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "HS", scope = ResponseResponseDataTax.class)
    public JAXBElement<String> createResponseResponseDataTaxHS(String value) {
        return new JAXBElement<String>(_DeclarationDeclarationDataContentPieceHS_QNAME, String.class, ResponseResponseDataTax.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "NetWeight", scope = ResponseResponseDataTax.class)
    public JAXBElement<BigDecimal> createResponseResponseDataTaxNetWeight(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_DeclarationDeclarationDataContentPieceNetWeight_QNAME, BigDecimal.class, ResponseResponseDataTax.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Number", scope = ResponseResponseDataTax.class)
    public JAXBElement<Integer> createResponseResponseDataTaxNumber(Integer value) {
        return new JAXBElement<Integer>(_DeclarationDeclarationDataContentPieceNumber_QNAME, Integer.class, ResponseResponseDataTax.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Rate", scope = ResponseResponseDataTax.class)
    public JAXBElement<BigDecimal> createResponseResponseDataTaxRate(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ResponseResponseDataTaxRate_QNAME, BigDecimal.class, ResponseResponseDataTax.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Type", scope = ResponseResponseDataTax.class)
    public JAXBElement<String> createResponseResponseDataTaxType(String value) {
        return new JAXBElement<String>(_ResponseResponseDataTaxType_QNAME, String.class, ResponseResponseDataTax.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "ClearanceDt", scope = ResponseResponseData.class)
    public JAXBElement<XMLGregorianCalendar> createResponseResponseDataClearanceDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ResponseResponseDataClearanceDt_QNAME, XMLGregorianCalendar.class, ResponseResponseData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "DecisionCd", scope = ResponseResponseData.class)
    public JAXBElement<String> createResponseResponseDataDecisionCd(String value) {
        return new JAXBElement<String>(_ResponseResponseDataDecisionCd_QNAME, String.class, ResponseResponseData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "DecisionReasonCd", scope = ResponseResponseData.class)
    public JAXBElement<String> createResponseResponseDataDecisionReasonCd(String value) {
        return new JAXBElement<String>(_ResponseResponseDataDecisionReasonCd_QNAME, String.class, ResponseResponseData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "DecisionReasonNm", scope = ResponseResponseData.class)
    public JAXBElement<String> createResponseResponseDataDecisionReasonNm(String value) {
        return new JAXBElement<String>(_ResponseResponseDataDecisionReasonNm_QNAME, String.class, ResponseResponseData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "ExtraFieldNames", scope = ResponseResponseData.class)
    public JAXBElement<ArrayOfstring> createResponseResponseDataExtraFieldNames(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_DeclarationDeclarationDataContentPieceExtraFieldNames_QNAME, ArrayOfstring.class, ResponseResponseData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "ExtraFieldValues", scope = ResponseResponseData.class)
    public JAXBElement<ArrayOfstring> createResponseResponseDataExtraFieldValues(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_DeclarationDeclarationDataContentPieceExtraFieldValues_QNAME, ArrayOfstring.class, ResponseResponseData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfResponseResponseDataTax }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfResponseResponseDataTax }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Taxes", scope = ResponseResponseData.class)
    public JAXBElement<ArrayOfResponseResponseDataTax> createResponseResponseDataTaxes(ArrayOfResponseResponseDataTax value) {
        return new JAXBElement<ArrayOfResponseResponseDataTax>(_ResponseResponseDataTaxes_QNAME, ArrayOfResponseResponseDataTax.class, ResponseResponseData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "TotalFee", scope = ResponseResponseData.class)
    public JAXBElement<BigDecimal> createResponseResponseDataTotalFee(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ResponseResponseDataTotalFee_QNAME, BigDecimal.class, ResponseResponseData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "TotalFeeCurrencyCd", scope = ResponseResponseData.class)
    public JAXBElement<String> createResponseResponseDataTotalFeeCurrencyCd(String value) {
        return new JAXBElement<String>(_ResponseResponseDataTotalFeeCurrencyCd_QNAME, String.class, ResponseResponseData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "CustOrganizationCd", scope = CDSObject.class)
    public JAXBElement<String> createCDSObjectCustOrganizationCd(String value) {
        return new JAXBElement<String>(_CDSObjectCustOrganizationCd_QNAME, String.class, CDSObject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "PId", scope = CDSObject.class)
    public JAXBElement<String> createCDSObjectPId(String value) {
        return new JAXBElement<String>(_CDSObjectPId_QNAME, String.class, CDSObject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "PostOrganizationCd", scope = CDSObject.class)
    public JAXBElement<String> createCDSObjectPostOrganizationCd(String value) {
        return new JAXBElement<String>(_CDSObjectPostOrganizationCd_QNAME, String.class, CDSObject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "XMLData", scope = CDSObject.class)
    public JAXBElement<String> createCDSObjectXMLData(String value) {
        return new JAXBElement<String>(_CDSObjectXMLData_QNAME, String.class, CDSObject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "Description", scope = MarketedGood.class)
    public JAXBElement<String> createMarketedGoodDescription(String value) {
        return new JAXBElement<String>(_MarketedGoodDescription_QNAME, String.class, MarketedGood.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MarketedGoodMarketedGoodDetails }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MarketedGoodMarketedGoodDetails }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "Details", scope = MarketedGood.class)
    public JAXBElement<MarketedGoodMarketedGoodDetails> createMarketedGoodDetails(MarketedGoodMarketedGoodDetails value) {
        return new JAXBElement<MarketedGoodMarketedGoodDetails>(_MarketedGoodDetails_QNAME, MarketedGoodMarketedGoodDetails.class, MarketedGood.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "ReferenceId", scope = MarketedGood.class)
    public JAXBElement<Integer> createMarketedGoodReferenceId(Integer value) {
        return new JAXBElement<Integer>(_MarketedGoodReferenceId_QNAME, Integer.class, MarketedGood.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "SellerOrganizationCd", scope = MarketedGood.class)
    public JAXBElement<String> createMarketedGoodSellerOrganizationCd(String value) {
        return new JAXBElement<String>(_MarketedGoodSellerOrganizationCd_QNAME, String.class, MarketedGood.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "ValidFrom", scope = MarketedGood.class)
    public JAXBElement<XMLGregorianCalendar> createMarketedGoodValidFrom(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_MarketedGoodValidFrom_QNAME, XMLGregorianCalendar.class, MarketedGood.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "ValidTo", scope = MarketedGood.class)
    public JAXBElement<XMLGregorianCalendar> createMarketedGoodValidTo(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_MarketedGoodValidTo_QNAME, XMLGregorianCalendar.class, MarketedGood.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "DestOrganizationCd", scope = PostalService.class)
    public JAXBElement<String> createPostalServiceDestOrganizationCd(String value) {
        return new JAXBElement<String>(_PostalServiceDestOrganizationCd_QNAME, String.class, PostalService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostalServiceServiceDetails }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PostalServiceServiceDetails }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "Details", scope = PostalService.class)
    public JAXBElement<PostalServiceServiceDetails> createPostalServiceDetails(PostalServiceServiceDetails value) {
        return new JAXBElement<PostalServiceServiceDetails>(_MarketedGoodDetails_QNAME, PostalServiceServiceDetails.class, PostalService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "MailCategoryCd", scope = PostalService.class)
    public JAXBElement<String> createPostalServiceMailCategoryCd(String value) {
        return new JAXBElement<String>(_PostalServiceMailCategoryCd_QNAME, String.class, PostalService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "MailClassCd", scope = PostalService.class)
    public JAXBElement<String> createPostalServiceMailClassCd(String value) {
        return new JAXBElement<String>(_PostalServiceMailClassCd_QNAME, String.class, PostalService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "OrigOrganizationCd", scope = PostalService.class)
    public JAXBElement<String> createPostalServiceOrigOrganizationCd(String value) {
        return new JAXBElement<String>(_PostalServiceOrigOrganizationCd_QNAME, String.class, PostalService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "ServiceId", scope = PostalService.class)
    public JAXBElement<String> createPostalServiceServiceId(String value) {
        return new JAXBElement<String>(_PostalServiceServiceId_QNAME, String.class, PostalService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "ServiceNm", scope = PostalService.class)
    public JAXBElement<String> createPostalServiceServiceNm(String value) {
        return new JAXBElement<String>(_PostalServiceServiceNm_QNAME, String.class, PostalService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "ValidFrom", scope = PostalService.class)
    public JAXBElement<XMLGregorianCalendar> createPostalServiceValidFrom(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_MarketedGoodValidFrom_QNAME, XMLGregorianCalendar.class, PostalService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", name = "ValidTo", scope = PostalService.class)
    public JAXBElement<XMLGregorianCalendar> createPostalServiceValidTo(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_MarketedGoodValidTo_QNAME, XMLGregorianCalendar.class, PostalService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Remarks", scope = WatchdogInfo.class)
    public JAXBElement<String> createWatchdogInfoRemarks(String value) {
        return new JAXBElement<String>(_WatchdogInfoRemarks_QNAME, String.class, WatchdogInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "DestPostOrgCd", scope = MailObject.class)
    public JAXBElement<String> createMailObjectDestPostOrgCd(String value) {
        return new JAXBElement<String>(_MailObjectDestPostOrgCd_QNAME, String.class, MailObject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Id", scope = MailObject.class)
    public JAXBElement<String> createMailObjectId(String value) {
        return new JAXBElement<String>(_MailObjectId_QNAME, String.class, MailObject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "LocalId", scope = MailObject.class)
    public JAXBElement<String> createMailObjectLocalId(String value) {
        return new JAXBElement<String>(_MailObjectLocalId_QNAME, String.class, MailObject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "LocalId2", scope = MailObject.class)
    public JAXBElement<String> createMailObjectLocalId2(String value) {
        return new JAXBElement<String>(_MailObjectLocalId2_QNAME, String.class, MailObject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "MailCategoryCd", scope = MailObject.class)
    public JAXBElement<String> createMailObjectMailCategoryCd(String value) {
        return new JAXBElement<String>(_MailObjectMailCategoryCd_QNAME, String.class, MailObject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "MailClassCd", scope = MailObject.class)
    public JAXBElement<String> createMailObjectMailClassCd(String value) {
        return new JAXBElement<String>(_MailObjectMailClassCd_QNAME, String.class, MailObject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "MailStateCd", scope = MailObject.class)
    public JAXBElement<Integer> createMailObjectMailStateCd(Integer value) {
        return new JAXBElement<Integer>(_MailObjectMailStateCd_QNAME, Integer.class, MailObject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "MailStateRemarks", scope = MailObject.class)
    public JAXBElement<String> createMailObjectMailStateRemarks(String value) {
        return new JAXBElement<String>(_MailObjectMailStateRemarks_QNAME, String.class, MailObject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "OrigPostOrgCd", scope = MailObject.class)
    public JAXBElement<String> createMailObjectOrigPostOrgCd(String value) {
        return new JAXBElement<String>(_MailObjectOrigPostOrgCd_QNAME, String.class, MailObject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "PId", scope = MailObject.class)
    public JAXBElement<String> createMailObjectPId(String value) {
        return new JAXBElement<String>(_CDSObjectPId_QNAME, String.class, MailObject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "TypeCd", scope = MailObject.class)
    public JAXBElement<String> createMailObjectTypeCd(String value) {
        return new JAXBElement<String>(_MailObjectTypeCd_QNAME, String.class, MailObject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseResponseData }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ResponseResponseData }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Data", scope = Response.class)
    public JAXBElement<ResponseResponseData> createResponseData(ResponseResponseData value) {
        return new JAXBElement<ResponseResponseData>(_ResponseData_QNAME, ResponseResponseData.class, Response.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeclarationDeclarationData }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeclarationDeclarationData }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Data", scope = Declaration.class)
    public JAXBElement<DeclarationDeclarationData> createDeclarationData(DeclarationDeclarationData value) {
        return new JAXBElement<DeclarationDeclarationData>(_ResponseData_QNAME, DeclarationDeclarationData.class, Declaration.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Response }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Response }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "DCResponse", scope = CDSView.class)
    public JAXBElement<Response> createCDSViewDCResponse(Response value) {
        return new JAXBElement<Response>(_CDSViewDCResponse_QNAME, Response.class, CDSView.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Declaration }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Declaration }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Declaration", scope = CDSView.class)
    public JAXBElement<Declaration> createCDSViewDeclaration(Declaration value) {
        return new JAXBElement<Declaration>(_Declaration_QNAME, Declaration.class, CDSView.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MailObject }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MailObject }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "MailObject", scope = CDSView.class)
    public JAXBElement<MailObject> createCDSViewMailObject(MailObject value) {
        return new JAXBElement<MailObject>(_MailObject_QNAME, MailObject.class, CDSView.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Response }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Response }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", name = "Response", scope = CDSView.class)
    public JAXBElement<Response> createCDSViewResponse(Response value) {
        return new JAXBElement<Response>(_Response_QNAME, Response.class, CDSView.class, value);
    }

}
