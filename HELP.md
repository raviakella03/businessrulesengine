# Getting Started
Problem Statement 2: Business Rules Engine
Imagine you’re writing an order processing application for a large company. In the past, this company used a fairly random mixture of manual and ad-hoc automated business practices to handle orders; they now want to put all these various ways of hanadling orders together into one whole: your application. After a full day of workshops you have gathered the following set of rules which need to be managed by the new system.
- If the payment is for a physical product, generate a packing slip for shipping.
- If the payment is for a book, create a duplicate packing slip for the royalty department.
- If the payment is for a membership, activate that membership.
- If the payment is an upgrade to a membership, apply the upgrade.
- If the payment is for a membership or upgrade, e-mail the owner and inform them of the activation/upgrade.
- If the payment is for the video “Learning to Ski,” add a free “First Aid” video to the packing slip (the result of a court decision in 1997).
- If the payment is for a physical product or a book, generate a commission payment to the agent.

Design a new system which can handle these rules and yet open to extension to new rules

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.2/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.2/reference/htmlsingle/#using-boot-devtools)
* [JDBC API](https://docs.spring.io/spring-boot/docs/2.6.2/reference/htmlsingle/#boot-features-sql)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.6.2/reference/htmlsingle/#production-ready)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
* [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

