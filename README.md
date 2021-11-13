# Simple Example of SpringBoot + SOAP Web Service

1. generate the code using jaxb2 Run: ` mvn clean jaxb2:generate`
   This will generate the code from the WSDL structure into the folder: `target/generated-srouces/xjc/<package>`

NOTE: the value for <package> comes from the pom config that goes like:

```xml

<generatePackage>co.socobox.ws.codegen</generatePackage>
```

2. Need to add the @XmlRootElement annotations to hint the ObjectFactory

```java

@XmlRootElement(name = "SayHelloResponse")
public class SayHelloResponse {
  // ... other code
}
```

```java

@XmlRootElement(name = "SayHello")
public class SayHello {
  // ... other code
}
```

Q: How do you know where to put the annotation? A: Let's check
the [WSDL](https://www.learnwebservices.com/services/hello?WSDL)

```xml

<wsdl:definitions xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                  xmlns:tns="http://learnwebservices.com/services/hello"
                  xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:ns1="http://schemas.xmlsoap.org/soap/http"
                  name="HelloEndpointService" targetNamespace="http://learnwebservices.com/services/hello">
    <wsdl:types>
        <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:tns="http://learnwebservices.com/services/hello"
                   elementFormDefault="qualified" targetNamespace="http://learnwebservices.com/services/hello"
                   version="1.0">
            <xs:element name="SayHello" type="tns:SayHello"/>
            <xs:element name="SayHelloResponse" type="tns:SayHelloResponse"/>
            <xs:complexType name="SayHello">
                <xs:sequence>
                    <xs:element name="HelloRequest" type="tns:helloRequest"/>
                </xs:sequence>
            </xs:complexType>
            <xs:complexType name="helloRequest">
                <xs:sequence>
                    <xs:element name="Name" type="xs:string"/>
                </xs:sequence>
            </xs:complexType>
            <xs:complexType name="SayHelloResponse">
                <xs:sequence>
                    <xs:element name="HelloResponse" type="tns:helloResponse"/>
                </xs:sequence>
            </xs:complexType>
            <xs:complexType name="helloResponse">
                <xs:sequence>
                    <xs:element name="Message" type="xs:string"/>
                </xs:sequence>
            </xs:complexType>
        </xs:schema>
    </wsdl:types>
    <wsdl:message name="SayHelloResponse">
        <wsdl:part element="tns:SayHelloResponse" name="parameters"></wsdl:part>
    </wsdl:message>
    <wsdl:message name="SayHello">
        <wsdl:part element="tns:SayHello" name="parameters"></wsdl:part>
    </wsdl:message>
    <wsdl:portType name="HelloEndpoint">
        <wsdl:operation name="SayHello">
            <wsdl:input message="tns:SayHello" name="SayHello"></wsdl:input>
            <wsdl:output message="tns:SayHelloResponse" name="SayHelloResponse"></wsdl:output>
        </wsdl:operation>
    </wsdl:portType>
    <wsdl:binding name="HelloEndpointServiceSoapBinding" type="tns:HelloEndpoint">
        <soap:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
        <wsdl:operation name="SayHello">
            <soap:operation soapAction="" style="document"/>
            <wsdl:input name="SayHello">
                <soap:body use="literal"/>
            </wsdl:input>
            <wsdl:output name="SayHelloResponse">
                <soap:body use="literal"/>
            </wsdl:output>
        </wsdl:operation>
    </wsdl:binding>
    <wsdl:service name="HelloEndpointService">
        <wsdl:port binding="tns:HelloEndpointServiceSoapBinding" name="HelloEndpointPort">
            <soap:address location="https://www.learnwebservices.com/services/hello"/>
        </wsdl:port>
    </wsdl:service>
</wsdl:definitions>

```

Check the messages:
`<wsdl:message name="SayHelloResponse">`
`<wsdl:message name="SayHello">`

They are both referenced for the operation we want to use:
```xml
<wsdl:operation name="SayHello">
    <wsdl:input message="tns:SayHello" name="SayHello"> </wsdl:input>
    <wsdl:output message="tns:SayHelloResponse" name="SayHelloResponse"> </wsdl:output>
</wsdl:operation>
```


3. You should be able to run the project now