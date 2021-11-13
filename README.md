# Simple Example of SpringBoot + SOAP Web Service

1. generate the code using jaxb2
Run: ` mvn clean jaxb2:generate`
This will generate the code from the WSDL structure into the folder: `target/generated-srouces/xjc/<package>`

2. NOTE: the value for <package> comes from the pom config that goes like:

```xml
<generatePackage>co.socobox.ws.codegen</generatePackage>
```


2.Need to add the @XmlRootElement annotations to hint the ObjectFactory

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


3. You should be able to run the project now