package com.personal.connector.application.servers;

public class SoapMockArithmeticServer {

    public static String HOST = "localhost:8597";
    public static String WSDL_SEGMENT = "/soap?wsdl";
    public static String ACTION = "http://localhost:8597/soap/action";

    public static String getWsdl() {
        return  "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<wsdl:definitions xmlns:soap=\"http://schemas.xmlsoap.org/wsdl/soap/\" xmlns:tm=\"http://microsoft.com/wsdl/mime/textMatching/\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:mime=\"http://schemas.xmlsoap.org/wsdl/mime/\" xmlns:tns=\"http://tempuri.org/\" xmlns:s=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://schemas.xmlsoap.org/wsdl/soap12/\" xmlns:http=\"http://schemas.xmlsoap.org/wsdl/http/\" targetNamespace=\"http://tempuri.org/\" xmlns:wsdl=\"http://schemas.xmlsoap.org/wsdl/\">\n" +
                "  <wsdl:types>\n" +
                "    <s:schema elementFormDefault=\"qualified\" targetNamespace=\"http://tempuri.org/\">\n" +
                "      <s:element name=\"Add\">\n" +
                "        <s:complexType>\n" +
                "          <s:sequence>\n" +
                "            <s:element minOccurs=\"1\" maxOccurs=\"1\" name=\"intA\" type=\"s:int\" />\n" +
                "            <s:element minOccurs=\"1\" maxOccurs=\"1\" name=\"intB\" type=\"s:int\" />\n" +
                "          </s:sequence>\n" +
                "        </s:complexType>\n" +
                "      </s:element>\n" +
                "      <s:element name=\"AddResponse\">\n" +
                "        <s:complexType>\n" +
                "          <s:sequence>\n" +
                "            <s:element minOccurs=\"1\" maxOccurs=\"1\" name=\"AddResult\" type=\"s:int\" />\n" +
                "          </s:sequence>\n" +
                "        </s:complexType>\n" +
                "      </s:element>\n" +
                "      <s:element name=\"Subtract\">\n" +
                "        <s:complexType>\n" +
                "          <s:sequence>\n" +
                "            <s:element minOccurs=\"1\" maxOccurs=\"1\" name=\"intA\" type=\"s:int\" />\n" +
                "            <s:element minOccurs=\"1\" maxOccurs=\"1\" name=\"intB\" type=\"s:int\" />\n" +
                "          </s:sequence>\n" +
                "        </s:complexType>\n" +
                "      </s:element>\n" +
                "      <s:element name=\"SubtractResponse\">\n" +
                "        <s:complexType>\n" +
                "          <s:sequence>\n" +
                "            <s:element minOccurs=\"1\" maxOccurs=\"1\" name=\"SubtractResult\" type=\"s:int\" />\n" +
                "          </s:sequence>\n" +
                "        </s:complexType>\n" +
                "      </s:element>\n" +
                "      <s:element name=\"Multiply\">\n" +
                "        <s:complexType>\n" +
                "          <s:sequence>\n" +
                "            <s:element minOccurs=\"1\" maxOccurs=\"1\" name=\"intA\" type=\"s:int\" />\n" +
                "            <s:element minOccurs=\"1\" maxOccurs=\"1\" name=\"intB\" type=\"s:int\" />\n" +
                "          </s:sequence>\n" +
                "        </s:complexType>\n" +
                "      </s:element>\n" +
                "      <s:element name=\"MultiplyResponse\">\n" +
                "        <s:complexType>\n" +
                "          <s:sequence>\n" +
                "            <s:element minOccurs=\"1\" maxOccurs=\"1\" name=\"MultiplyResult\" type=\"s:int\" />\n" +
                "          </s:sequence>\n" +
                "        </s:complexType>\n" +
                "      </s:element>\n" +
                "      <s:element name=\"Divide\">\n" +
                "        <s:complexType>\n" +
                "          <s:sequence>\n" +
                "            <s:element minOccurs=\"1\" maxOccurs=\"1\" name=\"intA\" type=\"s:int\" />\n" +
                "            <s:element minOccurs=\"1\" maxOccurs=\"1\" name=\"intB\" type=\"s:int\" />\n" +
                "          </s:sequence>\n" +
                "        </s:complexType>\n" +
                "      </s:element>\n" +
                "      <s:element name=\"DivideResponse\">\n" +
                "        <s:complexType>\n" +
                "          <s:sequence>\n" +
                "            <s:element minOccurs=\"1\" maxOccurs=\"1\" name=\"DivideResult\" type=\"s:int\" />\n" +
                "          </s:sequence>\n" +
                "        </s:complexType>\n" +
                "      </s:element>\n" +
                "    </s:schema>\n" +
                "  </wsdl:types>\n" +
                "  <wsdl:message name=\"AddSoapIn\">\n" +
                "    <wsdl:part name=\"parameters\" element=\"tns:Add\" />\n" +
                "  </wsdl:message>\n" +
                "  <wsdl:message name=\"AddSoapOut\">\n" +
                "    <wsdl:part name=\"parameters\" element=\"tns:AddResponse\" />\n" +
                "  </wsdl:message>\n" +
                "  <wsdl:message name=\"SubtractSoapIn\">\n" +
                "    <wsdl:part name=\"parameters\" element=\"tns:Subtract\" />\n" +
                "  </wsdl:message>\n" +
                "  <wsdl:message name=\"SubtractSoapOut\">\n" +
                "    <wsdl:part name=\"parameters\" element=\"tns:SubtractResponse\" />\n" +
                "  </wsdl:message>\n" +
                "  <wsdl:message name=\"MultiplySoapIn\">\n" +
                "    <wsdl:part name=\"parameters\" element=\"tns:Multiply\" />\n" +
                "  </wsdl:message>\n" +
                "  <wsdl:message name=\"MultiplySoapOut\">\n" +
                "    <wsdl:part name=\"parameters\" element=\"tns:MultiplyResponse\" />\n" +
                "  </wsdl:message>\n" +
                "  <wsdl:message name=\"DivideSoapIn\">\n" +
                "    <wsdl:part name=\"parameters\" element=\"tns:Divide\" />\n" +
                "  </wsdl:message>\n" +
                "  <wsdl:message name=\"DivideSoapOut\">\n" +
                "    <wsdl:part name=\"parameters\" element=\"tns:DivideResponse\" />\n" +
                "  </wsdl:message>\n" +
                "  <wsdl:portType name=\"CalculatorSoap\">\n" +
                "    <wsdl:operation name=\"Add\">\n" +
                "      <wsdl:documentation xmlns:wsdl=\"http://schemas.xmlsoap.org/wsdl/\">Adds two integers. This is a test WebService. ©DNE Online</wsdl:documentation>\n" +
                "      <wsdl:input message=\"tns:AddSoapIn\" />\n" +
                "      <wsdl:output message=\"tns:AddSoapOut\" />\n" +
                "    </wsdl:operation>\n" +
                "    <wsdl:operation name=\"Subtract\">\n" +
                "      <wsdl:input message=\"tns:SubtractSoapIn\" />\n" +
                "      <wsdl:output message=\"tns:SubtractSoapOut\" />\n" +
                "    </wsdl:operation>\n" +
                "    <wsdl:operation name=\"Multiply\">\n" +
                "      <wsdl:input message=\"tns:MultiplySoapIn\" />\n" +
                "      <wsdl:output message=\"tns:MultiplySoapOut\" />\n" +
                "    </wsdl:operation>\n" +
                "    <wsdl:operation name=\"Divide\">\n" +
                "      <wsdl:input message=\"tns:DivideSoapIn\" />\n" +
                "      <wsdl:output message=\"tns:DivideSoapOut\" />\n" +
                "    </wsdl:operation>\n" +
                "  </wsdl:portType>\n" +
                "  <wsdl:binding name=\"CalculatorSoap\" type=\"tns:CalculatorSoap\">\n" +
                "    <soap:binding transport=\"http://schemas.xmlsoap.org/soap/http\" />\n" +
                "    <wsdl:operation name=\"Add\">\n" +
                "      <soap:operation soapAction=\"http://tempuri.org/Add\" style=\"document\" />\n" +
                "      <wsdl:input>\n" +
                "        <soap:body use=\"literal\" />\n" +
                "      </wsdl:input>\n" +
                "      <wsdl:output>\n" +
                "        <soap:body use=\"literal\" />\n" +
                "      </wsdl:output>\n" +
                "    </wsdl:operation>\n" +
                "    <wsdl:operation name=\"Subtract\">\n" +
                "      <soap:operation soapAction=\"http://tempuri.org/Subtract\" style=\"document\" />\n" +
                "      <wsdl:input>\n" +
                "        <soap:body use=\"literal\" />\n" +
                "      </wsdl:input>\n" +
                "      <wsdl:output>\n" +
                "        <soap:body use=\"literal\" />\n" +
                "      </wsdl:output>\n" +
                "    </wsdl:operation>\n" +
                "    <wsdl:operation name=\"Multiply\">\n" +
                "      <soap:operation soapAction=\"http://tempuri.org/Multiply\" style=\"document\" />\n" +
                "      <wsdl:input>\n" +
                "        <soap:body use=\"literal\" />\n" +
                "      </wsdl:input>\n" +
                "      <wsdl:output>\n" +
                "        <soap:body use=\"literal\" />\n" +
                "      </wsdl:output>\n" +
                "    </wsdl:operation>\n" +
                "    <wsdl:operation name=\"Divide\">\n" +
                "      <soap:operation soapAction=\"http://tempuri.org/Divide\" style=\"document\" />\n" +
                "      <wsdl:input>\n" +
                "        <soap:body use=\"literal\" />\n" +
                "      </wsdl:input>\n" +
                "      <wsdl:output>\n" +
                "        <soap:body use=\"literal\" />\n" +
                "      </wsdl:output>\n" +
                "    </wsdl:operation>\n" +
                "  </wsdl:binding>\n" +
                "  <wsdl:binding name=\"CalculatorSoap12\" type=\"tns:CalculatorSoap\">\n" +
                "    <soap12:binding transport=\"http://schemas.xmlsoap.org/soap/http\" />\n" +
                "    <wsdl:operation name=\"Add\">\n" +
                "      <soap12:operation soapAction=\"http://tempuri.org/Add\" style=\"document\" />\n" +
                "      <wsdl:input>\n" +
                "        <soap12:body use=\"literal\" />\n" +
                "      </wsdl:input>\n" +
                "      <wsdl:output>\n" +
                "        <soap12:body use=\"literal\" />\n" +
                "      </wsdl:output>\n" +
                "    </wsdl:operation>\n" +
                "    <wsdl:operation name=\"Subtract\">\n" +
                "      <soap12:operation soapAction=\"http://tempuri.org/Subtract\" style=\"document\" />\n" +
                "      <wsdl:input>\n" +
                "        <soap12:body use=\"literal\" />\n" +
                "      </wsdl:input>\n" +
                "      <wsdl:output>\n" +
                "        <soap12:body use=\"literal\" />\n" +
                "      </wsdl:output>\n" +
                "    </wsdl:operation>\n" +
                "    <wsdl:operation name=\"Multiply\">\n" +
                "      <soap12:operation soapAction=\"http://tempuri.org/Multiply\" style=\"document\" />\n" +
                "      <wsdl:input>\n" +
                "        <soap12:body use=\"literal\" />\n" +
                "      </wsdl:input>\n" +
                "      <wsdl:output>\n" +
                "        <soap12:body use=\"literal\" />\n" +
                "      </wsdl:output>\n" +
                "    </wsdl:operation>\n" +
                "    <wsdl:operation name=\"Divide\">\n" +
                "      <soap12:operation soapAction=\"http://tempuri.org/Divide\" style=\"document\" />\n" +
                "      <wsdl:input>\n" +
                "        <soap12:body use=\"literal\" />\n" +
                "      </wsdl:input>\n" +
                "      <wsdl:output>\n" +
                "        <soap12:body use=\"literal\" />\n" +
                "      </wsdl:output>\n" +
                "    </wsdl:operation>\n" +
                "  </wsdl:binding>\n" +
                "  <wsdl:service name=\"Calculator\">\n" +
                "    <wsdl:port name=\"CalculatorSoap\" binding=\"tns:CalculatorSoap\">\n" +
                "      <soap:address location=\"" + ACTION + "\" />\n" +
                "    </wsdl:port>\n" +
                "    <wsdl:port name=\"CalculatorSoap12\" binding=\"tns:CalculatorSoap12\">\n" +
                "      <soap12:address location=\"" + ACTION + "\" />\n" +
                "    </wsdl:port>\n" +
                "  </wsdl:service>\n" +
                "</wsdl:definitions>";
    }

    public static String addRequest() {
        return  "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:Add>\n" +
                "         <tem:intA>1</tem:intA>\n" +
                "         <tem:intB>2</tem:intB>\n" +
                "      </tem:Add>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }

    public static String addResponse() {
        return  "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
                "   <soap:Body>\n" +
                "      <AddResponse xmlns=\"http://tempuri.org/\">\n" +
                "         <AddResult>3</AddResult>\n" +
                "      </AddResponse>\n" +
                "   </soap:Body>\n" +
                "</soap:Envelope>";
    }

    public static String substractRequest() {
        return  "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:Subtract>\n" +
                "         <tem:intA>8</tem:intA>\n" +
                "         <tem:intB>5</tem:intB>\n" +
                "      </tem:Subtract>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }

    public static String substractResponse() {
        return  "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
                "   <soap:Body>\n" +
                "      <SubtractResponse xmlns=\"http://tempuri.org/\">\n" +
                "         <SubtractResult>3</SubtractResult>\n" +
                "      </SubtractResponse>\n" +
                "   </soap:Body>\n" +
                "</soap:Envelope>";
    }

    public static String multiplyRequest() {
        return  "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:Multiply>\n" +
                "         <tem:intA>2</tem:intA>\n" +
                "         <tem:intB>5</tem:intB>\n" +
                "      </tem:Multiply>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }

    public static String multiplyResponse() {
        return  "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
                "   <soap:Body>\n" +
                "      <MultiplyResponse xmlns=\"http://tempuri.org/\">\n" +
                "         <MultiplyResult>10</MultiplyResult>\n" +
                "      </MultiplyResponse>\n" +
                "   </soap:Body>\n" +
                "</soap:Envelope>";
    }

    public static String divideRequest() {
        return  "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:Divide>\n" +
                "         <tem:intA>9</tem:intA>\n" +
                "         <tem:intB>3</tem:intB>\n" +
                "      </tem:Divide>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }

    public static String divideResponse() {
        return  "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
                "   <soap:Body>\n" +
                "      <DivideResponse xmlns=\"http://tempuri.org/\">\n" +
                "         <DivideResult>3</DivideResult>\n" +
                "      </DivideResponse>\n" +
                "   </soap:Body>\n" +
                "</soap:Envelope>";
    }
}
