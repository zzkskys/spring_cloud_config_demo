
#Spring Cloud Config 的个人示例


## Spring Cloud Config 的简单示例

Spring Cloud Config 用来集中管理微服务实例的配置信息.
它分为两个部分 : spring-cloud-config-server 与 spring-cloud-starter-config

spring-cloud-config-service 是用来集中维护所有微服务的配置,可以理解为配置中心

spring-cloud-starter-config 是用来从 spring-cloud-config-service 所在的微服务的配置信息

